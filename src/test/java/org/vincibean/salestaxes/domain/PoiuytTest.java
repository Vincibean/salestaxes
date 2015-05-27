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
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.domain.Poiuyt
 * 
 * @author Vincibean
 *
 */
public class PoiuytTest {

	/**
	 * Data objects that will be used in test methods.
	 */

	private Poiuyt poiuyt;

	/**
	 * Set up all data objects that will be used in test methods.
	 */
	@Before
	public void setUp(){
		poiuyt = new Poiuyt();
	}

	/**
	 * Test that setId()/getId() will behave consistently. 
	 */
	@Test
	public void testId(){
		long id = Long.MAX_VALUE;
		poiuyt.setId(id);
		Assert.assertEquals(id, poiuyt.getId().longValue());
	}

	/**
	 * Test that setName()/getName() will behave consistently. 
	 */
	@Test
	public void testName(){
		String name = "JUnit";
		poiuyt.setName(name);
		Assert.assertEquals(name, poiuyt.getName());
	}

	/**
	 * Test that setDescription()/getDescription() will behave consistently. 
	 */
	@Test
	public void testDescription(){
		String description = "JUnit Test Description";
		poiuyt.setDescription(description);
		Assert.assertEquals(description, poiuyt.getDescription());
	}

	/**
	 * Test that setCategorySet()/getCategorySet() will behave consistently. 
	 */
	@Test
	public void testCategory(){
		Category mockFee = Mockito.mock(Category.class);
		poiuyt.setCategorySet(new HashSet<Category>(Arrays.asList(mockFee)));
		Assert.assertTrue(poiuyt.getCategorySet().contains(mockFee));
	}

}
