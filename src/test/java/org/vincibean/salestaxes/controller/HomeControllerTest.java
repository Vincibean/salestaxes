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
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.vincibean.salestaxes.configuration.SalesTaxesApplication;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.controller.HomeController
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class HomeControllerTest {
	
	/**
	 * Object data that will be used in test methods.
	 */

	private MockMvc mockMvc;

	/**
	 * Set up all object data that will be used in test methods.
	 */
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
	}

	/**
	 * Test that requesting the index page will behave as intended (no Exception will be thrown).
	 * Get request.
	 * Requesting with "/".
	 */
	@Test
	public void getIndexSlash() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("index"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test that requesting the index page will behave as intended (no Exception will be thrown).
	 * Post request.
	 * Requesting with "/".
	 */
	@Test
	public void postIndexSlash() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("index"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * Test that requesting the index page will behave as intended (no Exception will be thrown).
	 * Delete request.
	 * Requesting with "/".
	 */
	@Test
	public void deleteIndexSlash() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.delete("/"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("index"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

}