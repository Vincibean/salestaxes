/*
 * Copyright (C) 2015  Vincibean <Andre Bessi>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.vincibean.salestaxes.service;

import static org.junit.Assert.*;

import org.apache.commons.math.util.MathUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vincibean.salestaxes.configuration.SalesTaxesApplication;
import org.vincibean.salestaxes.domain.Category;
import org.vincibean.salestaxes.domain.Fee;
import org.vincibean.salestaxes.domain.Poiuyt;
import org.vincibean.salestaxes.generated.Receipt;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * JUnit class covering methods of classes implementing org.vincibean.salestaxes.service.ReceiptService.
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class ReceiptServiceTest {

	/**
	 * Data objects that will be used in test methods.
	 */

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private PoiuytService poiuytService;

	/**
	 * Test that, given a List of valid Poiuyts, method generateReceipt() will return a 
	 * not null and fully populated Receipt.
	 */
	@Test
	public void testGenerateReceipt(){
		Iterable<Poiuyt> poiuyts = poiuytService.findAllPoiuyts();
		Receipt receipt = receiptService.generateReceipt(Lists.newArrayList(poiuyts));
		assertNotNull(receipt);
		assertEquals(receipt.getHeader().getTimeStamp().getDay(), new DateTime().getDayOfMonth());
		assertEquals(receipt.getHeader().getIssuer(), "FooBar Market");
		assertFalse(receipt.getPoiuyts().isEmpty());
		assertTrue(receipt.getTotalBasePrices() > 0);
		assertTrue(receipt.getTotalTaxes() > 0);
		assertTrue(receipt.getGrandTotal() > 0);
		assertEquals(receipt.getTotalBasePrices() +  receipt.getTotalTaxes(), receipt.getGrandTotal(), 0.001);
	}

	/**
	 * Test that, given Input:
	 *  - 1 book at 12.49, 
	 *  - 1 music CD at 14.99, 
	 *  - 1 chocolate bar at 0.85
	 *  the Output will be:
	 *  - 1 book at 12.49, 
	 *  - 1 music CD at 16.49, 
	 *  - 1 chocolate bar at 0.85 
	 *  Sales Taxes: 1.50
	 *  Total: 29.83 
	 */
	@Test
	public void testGenerateReceipt1(){
		// Names list
		final String BOOK_NAME = "Book";
		final String MUSIC_CD_NAME = "Music CD";
		final String CHOCOLATE_BAR_NAME = "Chocolate Bar";

		// Prices list
		final double BOOK_PRICE = 12.49;
		final double MUSIC_CD_PRICE = 14.99;
		final double CHOCOLATE_BAR_PRICE = 0.85;

		// Create a book Poiuyt.
		Poiuyt book = new Poiuyt();
		book.setName(BOOK_NAME);
		book.setDescription(BOOK_NAME);
		book.setPrice(BOOK_PRICE);
		book.setCategorySet(Sets.newHashSet(createNoTaxesCategory()));

		// Create a music CD Poiuyt.
		Poiuyt musicCd = new Poiuyt();
		musicCd.setName(MUSIC_CD_NAME);
		musicCd.setDescription(MUSIC_CD_NAME);
		musicCd.setPrice(MUSIC_CD_PRICE);
		musicCd.setCategorySet(Sets.newHashSet(createSalesTaxesCategory()));

		// Create a chocolate bar Poiuyt.
		Poiuyt chocolateBar = new Poiuyt();
		chocolateBar.setName(CHOCOLATE_BAR_NAME);
		chocolateBar.setDescription(CHOCOLATE_BAR_NAME);
		chocolateBar.setPrice(CHOCOLATE_BAR_PRICE);
		chocolateBar.setCategorySet(Sets.newHashSet(createNoTaxesCategory()));

		// Generate a Receipt with the above Poiuyts.
		Receipt receipt = receiptService.generateReceipt(Lists.newArrayList(book, musicCd, chocolateBar));
		boolean priceTestResult = true;
		for(org.vincibean.salestaxes.generated.Poiuyt poiuyt : receipt.getPoiuyts()){
			// Every Poiuyt belong to just 1 category
			switch(poiuyt.getName()) {
			case BOOK_NAME:
				priceTestResult = priceTestResult && new Double(BOOK_PRICE).equals(MathUtils.round(poiuyt.getFinalPrice(), 2));
				break;
			case MUSIC_CD_NAME:
				priceTestResult = priceTestResult && new Double(16.49).equals(poiuyt.getFinalPrice());
				break;
			case CHOCOLATE_BAR_NAME:
				priceTestResult = priceTestResult && new Double(CHOCOLATE_BAR_PRICE).equals(poiuyt.getFinalPrice());
				break;
			default:
				fail("Unexpected Poiuyt found");
				break;
			}
		}
		assertTrue(priceTestResult);
		assertEquals(1.50, receipt.getTotalTaxes(), 0.01);
		assertEquals(29.83, receipt.getGrandTotal(), 0.01);
	}

	/**
	 * Test that, given Input:
	 *  - 1 imported box chocolate at 10.00,
	 *  - 1 imported bottle of perfume at 47.50
	 *  the Output will be:
	 *  - 1 imported box chocolate at 10.50,
	 *  - 1 imported bottle of perfume at 54.65 
	 *  Sales taxes: 7.65
	 *  Total: 65.15
	 */
	@Test
	public void testGenerateReceipt2(){
		// Names list
		final String IMPORTED_BOX_CHOCOLATES_NAME = "Imported Box of Chocolates";
		final String IMPORTED_BOTTLE_PERFUME_NAME = "Imported Bottle of Perfume";

		// Prices list
		final double IMPORTED_BOX_CHOCOLATES_PRICE = 10;
		final double IMPORTED_BOTTLE_PERFUME_PRICE = 47.50;

		// Create a imported box of chocolates Poiuyt.
		Poiuyt chocolatesBox = new Poiuyt();
		chocolatesBox.setName(IMPORTED_BOX_CHOCOLATES_NAME);
		chocolatesBox.setDescription(IMPORTED_BOX_CHOCOLATES_NAME);
		chocolatesBox.setPrice(IMPORTED_BOX_CHOCOLATES_PRICE);
		chocolatesBox.setCategorySet(Sets.newHashSet(createImportTaxesCategory()));

		// Create a imported bottle of perfume Poiuyt.
		Poiuyt perfumeBottle = new Poiuyt();
		perfumeBottle.setName(IMPORTED_BOTTLE_PERFUME_NAME);
		perfumeBottle.setDescription(IMPORTED_BOTTLE_PERFUME_NAME);
		perfumeBottle.setPrice(IMPORTED_BOTTLE_PERFUME_PRICE);
		perfumeBottle.setCategorySet(Sets.newHashSet(createSalesTaxesCategory(), createImportTaxesCategory()));

		// Generate a Receipt with the above Poiuyts.
		Receipt receipt = receiptService.generateReceipt(Lists.newArrayList(chocolatesBox, perfumeBottle));
		boolean priceTestResult = true;
		for(org.vincibean.salestaxes.generated.Poiuyt poiuyt : receipt.getPoiuyts()){
			// Every Poiuyt belong to just 1 category
			switch(poiuyt.getName()) {
			case IMPORTED_BOX_CHOCOLATES_NAME:
				priceTestResult = priceTestResult && new Double(10.50).equals(poiuyt.getFinalPrice());
				break;
			case IMPORTED_BOTTLE_PERFUME_NAME:
				priceTestResult = priceTestResult && new Double(54.65).equals(poiuyt.getFinalPrice());
				break;
			default:
				fail("Unexpected Poiuyt found");
				break;
			}
		}
		assertTrue(priceTestResult);
		assertEquals(7.65, receipt.getTotalTaxes(), 0.01);
		assertEquals(65.15, receipt.getGrandTotal(), 0.01);
	}

	/**
	 * Test that, given Input:
	 *  - 1 imported bottle of perfume at 27.99,
	 *  - 1 bottle of perfume at 18.99,
	 *  - 1 packet of headache pills at 9.75,
	 *  - 1 imported box chocolate at 11.25,
	 *   
	 *  the Output will be:
	 *  - 1 imported bottle of perfume at 32.19,
	 *  - 1 bottle of perfume at 20.89,
	 *  - 1 packet of headache pills at 9.75,
	 *  - 1 imported box chocolate at 11.85 (actually 11.80),
	 *  Sales taxes: 6.70 (actually 6.65)
	 *  Total: 74.68 (actually, 74.63)
	 */
	@Test
	public void testGenerateReceipt3(){
		// Names list
		final String IMPORTED_BOTTLE_PERFUME_NAME = "Imported Bottle of Perfume";
		final String BOTTLE_PERFUME_NAME = "Bottle of Perfume";
		final String PACKET_HEADACHE_PILLS_NAME = "Packet of Headache Pills";
		final String IMPORTED_BOX_CHOCOLATES_NAME = "Imported Box of Chocolates";

		// Prices list
		final double IMPORTED_BOTTLE_PERFUME_PRICE = 27.99;
		final double BOTTLE_PERFUME_PRICE = 18.99;
		final double PACKET_HEADACHE_PILLS_PRICE = 9.75;
		final double IMPORTED_BOX_CHOCOLATES_PRICE = 11.25;

		// Create a imported bottle of perfume Poiuyt.
		Poiuyt importedPerfumeBottle = new Poiuyt();
		importedPerfumeBottle.setName(IMPORTED_BOTTLE_PERFUME_NAME);
		importedPerfumeBottle.setDescription(IMPORTED_BOTTLE_PERFUME_NAME);
		importedPerfumeBottle.setPrice(IMPORTED_BOTTLE_PERFUME_PRICE);
		importedPerfumeBottle.setCategorySet(Sets.newHashSet(createSalesTaxesCategory(), createImportTaxesCategory()));

		// Create a bottle of perfume Poiuyt.
		Poiuyt perfumeBottle = new Poiuyt();
		perfumeBottle.setName(BOTTLE_PERFUME_NAME);
		perfumeBottle.setDescription(BOTTLE_PERFUME_NAME);
		perfumeBottle.setPrice(BOTTLE_PERFUME_PRICE);
		perfumeBottle.setCategorySet(Sets.newHashSet(createSalesTaxesCategory()));

		// Create a packet of headache pills Poiuyt.
		Poiuyt headachePills = new Poiuyt();
		headachePills.setName(PACKET_HEADACHE_PILLS_NAME);
		headachePills.setDescription(PACKET_HEADACHE_PILLS_NAME);
		headachePills.setPrice(PACKET_HEADACHE_PILLS_PRICE);
		headachePills.setCategorySet(Sets.newHashSet(createNoTaxesCategory()));

		// Create a imported box of chocolates Poiuyt.
		Poiuyt importedChocolatesBox = new Poiuyt();
		importedChocolatesBox.setName(IMPORTED_BOX_CHOCOLATES_NAME);
		importedChocolatesBox.setDescription(IMPORTED_BOX_CHOCOLATES_NAME);
		importedChocolatesBox.setPrice(IMPORTED_BOX_CHOCOLATES_PRICE);
		importedChocolatesBox.setCategorySet(Sets.newHashSet(createImportTaxesCategory()));

		// Generate a Receipt with the above Poiuyts.
		Receipt receipt = receiptService.generateReceipt(Lists.newArrayList(importedPerfumeBottle, perfumeBottle, headachePills, importedChocolatesBox));
		boolean priceTestResult = true;
		for(org.vincibean.salestaxes.generated.Poiuyt poiuyt : receipt.getPoiuyts()){
			// Every Poiuyt belong to just 1 category
			switch(poiuyt.getName()) {
			case IMPORTED_BOTTLE_PERFUME_NAME:
				priceTestResult = priceTestResult && new Double(32.19).equals(poiuyt.getFinalPrice());
				break;
			case BOTTLE_PERFUME_NAME:
				priceTestResult = priceTestResult && new Double(20.89).equals(poiuyt.getFinalPrice());
				break;
			case PACKET_HEADACHE_PILLS_NAME:
				priceTestResult = priceTestResult && new Double(9.75).equals(poiuyt.getFinalPrice());
				break;
			case IMPORTED_BOX_CHOCOLATES_NAME:
				priceTestResult = priceTestResult && new Double(11.80).equals(poiuyt.getFinalPrice());
				break;		
			default:
				fail("Unexpected Poiuyt found");
				break;
			}
		}
		assertTrue(priceTestResult);
		assertEquals(6.65, receipt.getTotalTaxes(), 0.01);
		assertEquals(74.63, receipt.getGrandTotal(), 0.01);
	}

	/**
	 * Factory method, create a Category with import taxes applied.
	 * @return a Category with import taxes applied.
	 */
	private Category createImportTaxesCategory(){
		Category category = new Category();
		category.setName("Import Taxes");
		category.setDescription("Import Taxes");
		category.setFee(createImportTaxesFee());
		return category;
	}

	/**
	 * Factory method, create a Fee with import taxes.
	 * @return a Fee with import taxes.
	 */
	private Fee createImportTaxesFee() {
		Fee fee = new Fee();
		fee.setName("Import Taxes");
		fee.setDescription("Import Taxes");
		fee.setValue(5);
		return fee;
	}

	/**
	 * Factory method, create a Category with sales taxes applied.
	 * @return a Category with sales taxes applied.
	 */
	private Category createSalesTaxesCategory(){
		Category category = new Category();
		category.setName(Category.SALES_TAXES_CATEGORY_NAME);
		category.setDescription(Category.SALES_TAXES_CATEGORY_NAME);
		category.setFee(createSalesTaxesFee());
		return category;
	}

	/**
	 * Factory method, create a Fee with sales taxes.
	 * @return a Fee with sales taxes.
	 */
	private Fee createSalesTaxesFee() {
		Fee fee = new Fee();
		fee.setName("Sales Taxes");
		fee.setDescription("Sales Taxes");
		fee.setValue(10);
		return fee;
	}

	/**
	 * Factory method, create a Category with no taxes applied.
	 * @return a Category with no taxes applied.
	 */
	private Category createNoTaxesCategory(){
		Category category = new Category();
		category.setName("No Taxes");
		category.setDescription("No Taxes");
		category.setFee(createNoTaxesFee());
		return category;
	}

	/**
	 * Factory method, create a Fee with no taxes.
	 * @return a Fee with no taxes.
	 */
	private Fee createNoTaxesFee(){
		Fee fee = new Fee();
		fee.setName("No Taxes");
		fee.setDescription("No Taxes");
		fee.setValue(0);
		return fee;
	}

}
