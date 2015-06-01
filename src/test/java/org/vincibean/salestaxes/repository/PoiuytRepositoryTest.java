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

package org.vincibean.salestaxes.repository;

import java.util.Arrays;
import java.util.HashSet;

import javax.validation.ConstraintViolationException;

import org.junit.Assert;
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

import com.google.common.collect.FluentIterable;

/**
 * JUnit class covering methods of class implementing org.vincibean.salestaxes.repository.PoiuytRepository
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class PoiuytRepositoryTest {

	/**
	 * Object data that will be used in test methods.
	 */

	@Autowired
	private PoiuytRepository poiuytRepository;

	/**
	 * Test that a valid {@link Poiuyt} object will be saved without any error (Exception) occurring.
	 */
	@Test
	public void testSaveValidPoiuyt(){
		Category validCategory1 = createMockCategory();
		Category validCategory2 = createMockCategory();
		validCategory2.setName("Another JUnit valid Category");
		validCategory2.setDescription("Another mock Category object for JUnit tests");

		Poiuyt validPoiuyt = createMockPoiuyt();
		validPoiuyt.setCategorySet(new HashSet<Category>(Arrays.asList(validCategory1, validCategory2)));
		Poiuyt savedcategory = poiuytRepository.save(validPoiuyt);

		Assert.assertNotNull(validPoiuyt);
		Assert.assertNotNull(validPoiuyt.getId());
		Assert.assertFalse(savedcategory.getCategorySet().isEmpty());
		Assert.assertEquals(2, savedcategory.getCategorySet().size());		
		Assert.assertNotNull(FluentIterable.from(savedcategory.getCategorySet()).get(0).getId());
		Assert.assertNotNull(FluentIterable.from(savedcategory.getCategorySet()).get(1).getId());
	}

	/**
	 * Test that saving a {@link Poiuyt} containing an invalid {@link Fee} object (too high a value) 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSaveInvalidPoiuytFeeHigh(){
		Fee invalidFee = createMockFee();
		invalidFee.setValue(134567892.7f);

		Category validCategory = createMockCategory();
		validCategory.setFee(invalidFee);

		Poiuyt validPoiuyt = createMockPoiuyt();
		validPoiuyt.setCategorySet(new HashSet<Category>(Arrays.asList(validCategory)));

		poiuytRepository.save(validPoiuyt);
	}

	/**
	 * Test that saving a {@link Poiuyt} containing an invalid {@link Fee} object (too low a value) 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSaveInvalidPoiuytFeeLow(){
		Fee invalidFee = createMockFee();
		invalidFee.setValue(-134567892.7f);

		Category validCategory = createMockCategory();
		validCategory.setFee(invalidFee);

		Poiuyt validPoiuyt = createMockPoiuyt();
		validPoiuyt.setCategorySet(new HashSet<Category>(Arrays.asList(validCategory)));

		poiuytRepository.save(validPoiuyt);
	}

	/**
	 * Test that saving a {@link Poiuyt} containing {@link Category} with a null name 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSavePoiuytInvalidCategoryNullName(){
		Category invalidCategory = createMockCategory();
		invalidCategory.setName(null);

		Poiuyt validPoiuyt = createMockPoiuyt();
		validPoiuyt.setCategorySet(new HashSet<Category>(Arrays.asList(invalidCategory)));

		poiuytRepository.save(validPoiuyt);
	}

	/**
	 * Test that saving a {@link Poiuyt} containing a null name 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSavePoiuytNullName(){
		Poiuyt validPoiuyt = createMockPoiuyt();
		validPoiuyt.setName(null);

		poiuytRepository.save(validPoiuyt);
	}

	/**
	 * Factory method. Create a mock, valid {@link Fee}.
	 * @return a mock, valid {@link Fee}.
	 */
	private Fee createMockFee(){
		Fee validFee = new Fee();
		validFee.setName("JUnit valid Fee");
		validFee.setDescription("A mock Fee object for JUnit tests");
		validFee.setValue(12.7f);
		return validFee;
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
	 * Factory method. Create a mock, valid {@link Poiuyt}.
	 * @return a mock, valid {@link Poiuyt}.
	 */
	private Poiuyt createMockPoiuyt(){
		Poiuyt validPoiuyt = new Poiuyt();
		validPoiuyt.setName("JUnit valid Poiuyt");
		validPoiuyt.setDescription("A mock Poiuyt object for JUnit tests");
		validPoiuyt.setCategorySet(new HashSet<Category>(Arrays.asList(createMockCategory())));
		return validPoiuyt;
	}

}
