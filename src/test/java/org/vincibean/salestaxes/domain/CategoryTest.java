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

package org.vincibean.salestaxes.domain;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.domain.Category
 * 
 * @author Vincibean
 *
 */
public class CategoryTest {

	/**
	 * Data objects that will be used in test methods.
	 */

	private Category category;

	/**
	 * Set up all data objects that will be used in test methods.
	 */
	@Before
	public void setUp(){
		category = new Category();
	}

	/**
	 * Test that setId()/getId() will behave consistently. 
	 */
	@Test
	public void testId(){
		long id = Long.MAX_VALUE;
		category.setId(id);
		Assert.assertEquals(id, category.getId().longValue());
	}

	/**
	 * Test that setName()/getName() will behave consistently. 
	 */
	@Test
	public void testName(){
		String name = "JUnit";
		category.setName(name);
		Assert.assertEquals(name, category.getName());
	}

	/**
	 * Test that setDescription()/getDescription() will behave consistently. 
	 */
	@Test
	public void testDescription(){
		String description = "JUnit Test Description";
		category.setDescription(description);
		Assert.assertEquals(description, category.getDescription());
	}

	/**
	 * Test that setFeeList()/getFeeList() will behave consistently. 
	 */
	@Test
	public void testFee(){
		Fee mockFee = Mockito.mock(Fee.class);
		category.setFeeList(Arrays.asList(mockFee));
		Assert.assertEquals(mockFee, category.getFeeList().get(0));
	}

}
