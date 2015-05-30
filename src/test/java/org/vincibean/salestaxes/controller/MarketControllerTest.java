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

package org.vincibean.salestaxes.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.vincibean.salestaxes.configuration.SalesTaxesApplication;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.controller.CategoryController
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class MarketControllerTest {
	
	/**
	 * Object data that will be used in test methods.
	 */

	@Autowired
	private MarketController marketController;
	
	private MockMvc mockMvc;

	/**
	 * Set up all object data that will be used in test methods.
	 */
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(marketController).build();
	}

	/**
	 * Test that requesting the categories page will behave as intended (no Exception will be thrown).
	 * Get request.
	 * Requesting with "/market/categories".
	 */
	@Test
	public void getCategoriesTest() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/market/categories"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("market/categories_list"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test that requesting the categories page will behave as intended (no Exception will be thrown).
	 * Post request.
	 * Requesting with "/market/categories".
	 */
	@Test
	public void postCategoriesTest() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/market/categories"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("market/categories_list"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test that requesting the categories page will behave as intended (no Exception will be thrown).
	 * Delete request.
	 * Requesting with "/market/categories".
	 */
	@Test
	public void deleteCategoriesTest() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.delete("/market/categories"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("categories"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("market/categories_list"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

}