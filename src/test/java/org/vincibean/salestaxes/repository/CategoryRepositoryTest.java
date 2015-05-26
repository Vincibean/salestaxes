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

/**
 * JUnit class covering methods of class implementing org.vincibean.salestaxes.repositry.CategoryRepository
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class CategoryRepositoryTest {

	/**
	 * Object data that will be used in test methods.
	 */

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * Test that a valid {@link Category} object will be saved without any error (Exception) occurring.
	 */
	@Test
	public void testSaveValidCategory(){
		Fee validFee = new Fee();
		validFee.setName("JUnit valid Fee");
		validFee.setDescription("A mock Fee object for JUnit tests");
		validFee.setValue(12.7f);
		
		Category validCategory = new Category();
		validCategory.setName("JUnit valid Category");
		validCategory.setDescription("A mock Category object for JUnit tests");
		validCategory.setFee(validFee);
		
		Assert.assertNotNull(categoryRepository.save(validCategory).getId());
	}

	/**
	 * Test that saving a {@link Category} containing an invalid {@link Fee} object (too high a value) 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSaveInvalidCategoryFeeHigh(){
		Fee invalidFee = new Fee();
		invalidFee.setName("JUnit invalid Fee");
		invalidFee.setDescription("A mock Fee object for JUnit tests");
		invalidFee.setValue(134567892.7f);
		
		Category validCategory = new Category();
		validCategory.setName("JUnit valid Category");
		validCategory.setDescription("A mock Category object for JUnit tests");
		validCategory.setFee(invalidFee);
		
		categoryRepository.save(validCategory).getId();
	}
	
	/**
	 * Test that saving a {@link Category} containing an invalid {@link Fee} object (too low a value) 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSaveInvalidCategoryFeeLow(){
		Fee invalidFee = new Fee();
		invalidFee.setName("JUnit invalid Fee");
		invalidFee.setDescription("A mock Fee object for JUnit tests");
		invalidFee.setValue(-134567892.7f);

		Category validCategory = new Category();
		validCategory.setName("JUnit valid Category");
		validCategory.setDescription("A mock Category object for JUnit tests");
		validCategory.setFee(invalidFee);
		
		categoryRepository.save(validCategory).getId();
	}
	
	/**
	 * Test that saving a {@link Category} containing a null name 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSaveInvalidCategoryNullDescription(){
		Category invalidCategory = new Category();
		invalidCategory.setName(null);
		invalidCategory.setDescription("A mock Category object for JUnit tests");
		categoryRepository.save(invalidCategory).getId();
	}

}
