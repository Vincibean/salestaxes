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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.domain.Fee
 * 
 * @author Vincibean
 *
 */
public class FeeTest {
	/**
	 * Data objects that will be used in test methods.
	 */
	
	private Fee fee;
	
	/**
	 * Set up all data objects that will be used in test methods.
	 */
	@Before
	public void setUp(){
		fee = new Fee();
	}

	/**
	 * Test that setId()/getId() will behave consistently. 
	 */
	@Test
	public void testId(){
		int id = Integer.MAX_VALUE;
		fee.setId(id);
		Assert.assertEquals(id, fee.getId());
	}
	
	/**
	 * Test that setName()/getName() will behave consistently. 
	 */
	@Test
	public void testName(){
		String name = "JUnit";
		fee.setName(name);
		Assert.assertEquals(name, fee.getName());
	}
	
	/**
	 * Test that setDescription()/getDescription() will behave consistently. 
	 */
	@Test
	public void testDescription(){
		String description = "JUnit Test Description";
		fee.setDescription(description);
		Assert.assertEquals(description, fee.getDescription());
	}
	
	/**
	 * Test that setValue()/getValue() will behave consistently. 
	 */
	@Test
	public void testValue(){
		int value = 45;
		fee.setValue(value);
		Assert.assertEquals(value, fee.getValue());
	}
	
}
