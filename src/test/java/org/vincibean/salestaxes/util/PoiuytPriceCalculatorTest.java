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

package org.vincibean.salestaxes.util;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.vincibean.salestaxes.domain.Category;
import org.vincibean.salestaxes.domain.Fee;
import org.vincibean.salestaxes.domain.Poiuyt;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.util.PoiuytPriceCalculator.
 * 
 * @author Vincibean
 *
 */
public class PoiuytPriceCalculatorTest {
	
	private static final double POIUYT_BASE_PRICE = 200L;
	
	private static final double FEE_BASE_TAX = 12.7;
	
	/**
	 * Test that method calculatePoiuytBasketTotalBasePrice() will return the exact price in a simple case
	 * (2 Poiuyt, each one having 1 category, positive fee vale).
	 */
	@Test
	public void testCalculatePoiuytBasketTotalBasePrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		Assert.assertEquals(
				2 * (mockPoiuyt.getPrice()), 
				PoiuytPriceCalculator.calculatePoiuytBasketTotalBasePrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}

	/**
	 * Test that method calculatePoiuytBasketTotalBasePrice() will return the exact price in case there are
	 * 2 Poiuyt, each one having 1 category, positive fee vale, and the Poiuyt prices is 0.
	 */
	@Test
	public void testCalculatePoiuytBaskeTotalBasePriceNoPrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(0);
		Assert.assertEquals(0, PoiuytPriceCalculator.calculatePoiuytBasketTotalBasePrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}

	/**
	 * Test that method calculatePoiuytBasketTotalBasePrice() will return the exact price in case there are
	 * 2 Poiuyts, each one having 1 category, and the fee vale is 0.
	 */
	@Test
	public void testCalculatePoiuytBasketTotalBasePriceNoPriceInCategory(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(0);
		}
		Assert.assertEquals(mockPoiuyt.getPrice() * 2, 
				PoiuytPriceCalculator.calculatePoiuytBasketTotalBasePrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}

	/**
	 * Test that method calculatePoiuytBasketTotalBasePrice() will return the exact price in case there are
	 * 2 Poiuyts, each one having 1 category, positive fee value, and the Poiuyt price is negative.
	 */
	@Test
	public void testCalculatePoiuytBasketTotalBasePriceNegativePrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(-100);
		Assert.assertEquals(2 * (mockPoiuyt.getPrice()), 
				PoiuytPriceCalculator.calculatePoiuytBasketTotalBasePrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}

	/**
	 * Test that method calculatePoiuytBasketTotalBasePrice() will return the exact price in case there are
	 * 2 Poiuyt, each one having 1 category, negative fee vale.
	 */
	@Test
	public void testCalculatePoiuytBasketTotalBasePriceDiscount(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(-10);
		}
		Assert.assertEquals(2 * (mockPoiuyt.getPrice()), 
				PoiuytPriceCalculator.calculatePoiuytBasketTotalBasePrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketTotalTaxes() will return the exact tax amount in a simple case
	 * (2 Poiuyt, each one having 1 category, positive fee vale).
	 */
	@Test
	public void testCalculatePoiuytBasketTotalTaxes(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		Assert.assertEquals(
				2 * (mockPoiuyt.getPrice() * (FEE_BASE_TAX) / 100.0), 
				PoiuytPriceCalculator.calculatePoiuytBasketTotalTaxes(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketTotalTaxes() will return the exact tax amount in case there are
	 * 2 Poiuyt, each one having 1 category, positive fee vale, and the Poiuyt prices are 0.
	 */
	@Test
	public void testCalculatePoiuytBaskeTotalTaxesNoPrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(0);
		Assert.assertEquals(0, PoiuytPriceCalculator.calculatePoiuytBasketTotalTaxes(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketTotalTaxes() will return the exact tax amount in case there are
	 * 2 Poiuyts, each one having 1 category, and the fee vale is 0.
	 */
	@Test
	public void testCalculatePoiuytBasketTotalTaxesNoPriceInCategory(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(0);
		}
		Assert.assertEquals(0, PoiuytPriceCalculator.calculatePoiuytBasketTotalTaxes(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketTotalTaxes() will return the exact tax amount in case there are
	 * 2 Poiuyts, each one having 1 category, positive fee value, and the Poiuyt price is negative.
	 */
	@Test
	public void testCalculatePoiuytBasketTotalTaxesNegativePrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(-100);
		Assert.assertEquals(2 * (mockPoiuyt.getPrice() * FEE_BASE_TAX / 100), 
				PoiuytPriceCalculator.calculatePoiuytBasketTotalTaxes(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytTotalTaxes() will return the exact percentage in case there are
	 * 2 Poiuyt, each one having 1 category, negative fee vale.
	 */
	@Test
	public void testCalculatePoiuytBasketTotalTaxesDiscount(){
		final double newFee = -10.0;
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(newFee);
		}
		Assert.assertEquals(2 * (mockPoiuyt.getPrice() * newFee / 100), 
				PoiuytPriceCalculator.calculatePoiuytBasketTotalTaxes(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketTotalTaxesPerPoiuyt() will return the total amount of the taxes of the given Poiuyt
	 * in a simple case (1 Poiuyt having 1 category, positive fee vale).
	 */
	@Test
	public void testCalculateTotalTaxesPerPoiuyt(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		Assert.assertEquals(
				mockPoiuyt.getPrice() * FEE_BASE_TAX / 100, 
				PoiuytPriceCalculator.calculateTotalTaxesPerPoiuyt(mockPoiuyt), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketTotalTaxesPerPoiuyt() will return the exact tax amount in case there is
	 * 1 Poiuyt having 1 category, positive fee vale, and the Poiuyt price is 0.
	 */
	@Test
	public void testCalculateTotalTaxesPerPoiuytNoPrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(0);
		Assert.assertEquals(0, PoiuytPriceCalculator.calculateTotalTaxesPerPoiuyt(mockPoiuyt), 0.001);
	}
	
	/**
	 * Test that method calculateTotalTaxesPerPoiuyt() will return the exact tax amount in case there is
	 * 1 Poiuyts having 1 category, and the fee vale is 0.
	 */
	@Test
	public void testCalculateTotalTaxesPerPoiuytNoPriceInCategory(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(0);
		}
		Assert.assertEquals(0, PoiuytPriceCalculator.calculateTotalTaxesPerPoiuyt(mockPoiuyt), 0.001);
	}
	
	/**
	 * Test that method calculateTotalTaxesPerPoiuyt() will return the exact tax amount in case there is
	 * 1 Poiuyts having 1 category, positive fee value, and the Poiuyt price is negative.
	 */
	@Test
	public void testCalculateTotalTaxesPerPoiuytNegativePrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(-100);
		Assert.assertEquals(mockPoiuyt.getPrice() * FEE_BASE_TAX / 100, 
				PoiuytPriceCalculator.calculateTotalTaxesPerPoiuyt(mockPoiuyt), 0.001);
	}
	
	/**
	 * Test that method calculateTotalTaxesPerPoiuyt() will return the exact tax amount in case there is
	 * 1 Poiuyt having 1 category, negative fee vale.
	 */
	@Test
	public void testCalculateTotalTaxesPerPoiuytDiscount(){
		final double newFee = -10.0;
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(newFee);
		}
		Assert.assertEquals(mockPoiuyt.getPrice() * newFee / 100, 
				PoiuytPriceCalculator.calculateTotalTaxesPerPoiuyt(mockPoiuyt), 0.001);
	}

	/**
	 * Factory method. Create a mock, valid {@link Poiuyt}.
	 * @return a mock, valid {@link Poiuyt}.
	 */
	private Poiuyt createMockPoiuyt(){
		Poiuyt validPoiuyt = new Poiuyt();
		validPoiuyt.setName("JUnit valid Poiuyt");
		validPoiuyt.setDescription("A mock Poiuyt object for JUnit tests");
		validPoiuyt.setPrice(POIUYT_BASE_PRICE);
		validPoiuyt.setCategorySet(new HashSet<Category>(Arrays.asList(createMockCategory())));
		return validPoiuyt;
	}

	/**
	 * Factory method. Create a mock, valid {@link Category}.
	 * @return a mock, valid {@link Category}.
	 */
	private Category createMockCategory(){
		Category validCategory = new Category();
		validCategory.setName("JUnit valid Category");
		validCategory.setDescription("A mock Category object for JUnit tests");
		validCategory.setFee(createMockFee());
		return validCategory;
	}

	/**
	 * Factory method. Create a mock, valid {@link Fee}.
	 * @return a mock, valid {@link Fee}.
	 */
	private Fee createMockFee(){
		Fee mockFee = new Fee();
		mockFee.setName("JUnit valid Fee");
		mockFee.setDescription("A mock Fee object for JUnit tests");
		mockFee.setValue(FEE_BASE_TAX);
		return mockFee;
	}

}
