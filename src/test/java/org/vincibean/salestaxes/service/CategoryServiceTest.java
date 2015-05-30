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

import java.util.Arrays;

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

import com.google.common.collect.FluentIterable;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.service.CategoryService.
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class CategoryServiceTest {

	/**
	 * Data objects that will be used in test methods.
	 */

	@Autowired
	private CategoryService categoryService;

	/**
	 * Test that method save() will not throw any Exception, that it will return a not null Category
	 * and that the saved Category Id (which is null, at first) is not null (i.e. it was populated by the service method.
	 */
	@Test
	public void testSaveCategory(){
		Category category = createMockCategory();
		Category savedCategory = categoryService.save(category);
		Assert.assertNotNull(savedCategory);
		Assert.assertNotNull(savedCategory.getId());
	}

	/**
	 * Test that, once a valid Category is saved, method findAllCategories() will return a not null list of Categories.
	 */
	@Test
	public void testFindAllCategories(){
		Category category = createMockCategory();
		categoryService.save(category);
		Iterable<Category> categories = categoryService.findAllCategories();
		Assert.assertNotNull(categories);
		Assert.assertFalse(FluentIterable.from(categories).isEmpty());
	}

	/**
	 * Test that, once a valid Category is saved, method findCategoryById() will return 
	 * the same Category(test by ID).
	 */
	@Test
	public void testFindCategoryById(){
		Category category = categoryService.save(createMockCategory());
		Category foundCategory = categoryService.findCategoryById(category.getId());
		Assert.assertNotNull(foundCategory);
		Assert.assertEquals(category.getId(), foundCategory.getId());
	}

	/**
	 * Test that method findCategoryById() will return a null object if a not existent ID 
	 * (here an artificially high ID) is given.
	 */
	@Test
	public void testFindCategoryByWrongId(){
		Assert.assertNull(categoryService.findCategoryById(Long.MAX_VALUE));
	}

	/**
	 * Factory method. Create a mock, valid {@link Category}.
	 * @return a mock, valid {@link Category}.
	 */
	private Category createMockCategory(){
		Category validCategory = new Category();
		validCategory.setName("JUnit valid Category");
		validCategory.setDescription("A mock Category object for JUnit tests");
		validCategory.setFeeList(Arrays.asList(createMockFee()));
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
