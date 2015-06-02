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

	/**
	 * Test that method calculatePoiuytPrice() will return the exact percentage in a simple case
	 * (1 Poiuyt, 1 category, positive fee vale).
	 */
	@Test
	public void testCalculatePoiuytPrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		// Only 1 Category.
		double feeValue = mockPoiuyt.getCategorySet().iterator().next().getFee().getValue();
		Assert.assertEquals(mockPoiuyt.getPrice() + (mockPoiuyt.getPrice() * feeValue / 100), PoiuytPriceCalculator.calculatePoiuytPrice(createMockPoiuyt()), 0.001);
	}

	/**
	 * Test that method calculatePoiuytPrice() will return the exact percentage in case there is
	 * 1 Poiuyt, 1 category, positive fee vale, and the Poiuyt price is 0.
	 */
	@Test
	public void testCalculatePoiuytNoPrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(0);
		Assert.assertEquals(0, PoiuytPriceCalculator.calculatePoiuytPrice(mockPoiuyt), 0.001);
	}

	/**
	 * Test that method calculatePoiuytPrice() will return the exact percentage in case there is
	 * 1 Poiuyt, 1 category, and the fee value is 0.
	 */
	@Test
	public void testCalculatePoiuytNoPriceInCategory(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(0);
		}
		Assert.assertEquals(mockPoiuyt.getPrice(), PoiuytPriceCalculator.calculatePoiuytPrice(mockPoiuyt), 0.001);
	}

	/**
	 * Test that method calculatePoiuytPrice() will return the exact percentage in case there is
	 * 1 Poiuyt, 1 category, positive fee value, and the Poiuyt price is negative.
	 */
	@Test
	public void testCalculatePoiuytNegativePrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(-100);
		Assert.assertEquals(-112.7, PoiuytPriceCalculator.calculatePoiuytPrice(mockPoiuyt), 0.001);
	}

	/**
	 * Test that method calculatePoiuytPrice() will return the exact percentage in case there is
	 * 1 Poiuyt, 1 category, and negative fee value.
	 */
	@Test
	public void testCalculatePoiuytDiscount(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(-10);
		}
		// Only one Category.
		Assert.assertEquals(mockPoiuyt.getPrice() * 90 / 100, PoiuytPriceCalculator.calculatePoiuytPrice(mockPoiuyt), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketPrice() will return the exact percentage in a simple case
	 * (2 Poiuyt, each one having 1 category, positive fee vale).
	 */
	@Test
	public void testCalculatePoiuytBasketPrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		Assert.assertEquals(
				// Only one Category.
				2 * (mockPoiuyt.getPrice() + (mockPoiuyt.getPrice() * mockPoiuyt.getCategorySet().iterator().next().getFee().getValue() / 100)), 
				PoiuytPriceCalculator.calculatePoiuytBasketPrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
		
	}
	
	/**
	 * Test that method calculatePoiuytBasketPrice() will return the exact percentage in case there are
	 * 2 Poiuyt, each one having 1 category, positive fee vale, and the Poiuyt prices are 0.
	 */
	@Test
	public void testCalculatePoiuytBasketNoPrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(0);
		// Only one Category.
		Assert.assertEquals(0, PoiuytPriceCalculator.calculatePoiuytBasketPrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
		
	}
	
	/**
	 * Test that method calculatePoiuytBasketPrice() will return the exact percentage in case there are
	 * 2 Poiuyts, each one having 1 category, and the fee vale is 0.
	 */
	@Test
	public void testCalculatePoiuytBasketNoPriceInCategory(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(0);
		}
		Assert.assertEquals(mockPoiuyt.getPrice() * 2, 
				PoiuytPriceCalculator.calculatePoiuytBasketPrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketPrice() will return the exact percentage in case there are
	 * 2 Poiuyts, each one having 1 category, positive fee value, and the Poiuyt price is negative.
	 */
	@Test
	public void testCalculatePoiuytBasketNegativePrice(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		mockPoiuyt.setPrice(-100);
		// Only one Category.
		Assert.assertEquals(2 * (mockPoiuyt.getPrice() + (mockPoiuyt.getPrice() * mockPoiuyt.getCategorySet().iterator().next().getFee().getValue() / 100)), 
				PoiuytPriceCalculator.calculatePoiuytBasketPrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}
	
	/**
	 * Test that method calculatePoiuytBasketPrice() will return the exact percentage in case there are
	 * 2 Poiuyt, each one having 1 category, negative fee vale.
	 */
	@Test
	public void testCalculatePoiuytBasketPriceDiscount(){
		Poiuyt mockPoiuyt = createMockPoiuyt();
		for(Category category : mockPoiuyt.getCategorySet()){
			category.getFee().setValue(-10);
		}
		// Only one Category.
		Assert.assertEquals(2 * (mockPoiuyt.getPrice() + (mockPoiuyt.getPrice() * mockPoiuyt.getCategorySet().iterator().next().getFee().getValue() / 100)), 
				PoiuytPriceCalculator.calculatePoiuytBasketPrice(Arrays.asList(mockPoiuyt, mockPoiuyt)), 0.001);
	}

	/**
	 * Factory method. Create a mock, valid {@link Poiuyt}.
	 * @return a mock, valid {@link Poiuyt}.
	 */
	private Poiuyt createMockPoiuyt(){
		Poiuyt validPoiuyt = new Poiuyt();
		validPoiuyt.setName("JUnit valid Poiuyt");
		validPoiuyt.setDescription("A mock Poiuyt object for JUnit tests");
		validPoiuyt.setPrice(200L);
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
		mockFee.setValue(12.7f);
		return mockFee;
	}

}
