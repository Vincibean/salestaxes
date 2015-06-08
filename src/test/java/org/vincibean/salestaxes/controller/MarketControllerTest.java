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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.vincibean.salestaxes.configuration.SalesTaxesApplication;
import org.vincibean.salestaxes.service.PoiuytService;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.controller.MarketController
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

	@Autowired
	private PoiuytService poiuytService;

	private MockMvc mockMvc;

	/**
	 * Set up all object data that will be used in test methods.
	 */
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(marketController).build();
	}

	/**
	 * Test that requesting the poiuyts page will behave as intended (no Exception will be thrown).
	 * Get request.
	 * Requesting with "/market/poiuyts".
	 */
	@Test
	public void getPoiuytsTest() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/market/poiuyts"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("poiuyts"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("market/poiuyt_list"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * Test that requesting the poiuyt detail page will behave as intended (no Exception will be thrown).
	 * Get request.
	 * Requesting with "/market/poiuyt_detail".
	 */
	@Test
	public void getPoiuytDetailTest() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/market/poiuyt_detail").param("poiuytId", poiuytService
					.findAllPoiuyts()
					.iterator()
					.next()
					.getId()
					.toString()))
					.andExpect(MockMvcResultMatchers.model().attributeExists("poiuyt"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name("market/poiuyt_detail"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * Test that requesting the poiuyt receipt xml content will behave as intended (no Exception will be thrown).
	 * Post request.
	 * Requesting with "/market/export-receipt".
	 */
	@Test
	public void testExportReceipt(){
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/market/export-receipt")
					.param("id", poiuytService
							.findAllPoiuyts()
							.iterator()
							.next()
							.getId()
							.toString())
							.param("quantity", "1"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * Test that requesting the poiuyt receipt xml content with the wrong HTTP request 
	 * will return a client error in the 4xx range.
	 * Get request.
	 * Requesting with "/market/export-receipt".
	 */
	@Test
	public void testExportReceiptWithGet(){
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/market/export-receipt")
					.param("id", poiuytService
							.findAllPoiuyts()
							.iterator()
							.next()
							.getId()
							.toString())
							.param("quantity", "1"))
							.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * Test that requesting the poiuyt receipt xml content with a not existing id 
	 * will return an empty byte array.
	 * Post request.
	 * Requesting with "/market/export-receipt".
	 */
	@Test
	public void testExportReceiptBadId(){
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/market/export-receipt")
					.param("id", "" + Long.MAX_VALUE)
					.param("quantity", "1"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().bytes(new byte[0]));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * Test that requesting the poiuyt receipt xml content with a quantity of 0 
	 * will return an empty byte array.
	 * Post request.
	 * Requesting with "/market/export-receipt".
	 */
	@Test
	public void testExportReceiptQuantity0(){
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/market/export-receipt")
					.param("id", poiuytService
							.findAllPoiuyts()
							.iterator()
							.next()
							.getId()
							.toString())
							.param("quantity", "0"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().bytes(new byte[0]));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	/**
	 * Test that requesting the poiuyt receipt xml content with a negative quantity
	 * will return an empty byte array.
	 * Post request.
	 * Requesting with "/market/export-receipt".
	 */
	@Test
	public void testExportReceiptNegativeQuantity(){
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/market/export-receipt")
					.param("id", poiuytService
							.findAllPoiuyts()
							.iterator()
							.next()
							.getId()
							.toString())
							.param("quantity", "-34"))
							.andExpect(MockMvcResultMatchers.status().isOk())
							.andExpect(MockMvcResultMatchers.content().bytes(new byte[0]));
		} catch (Exception e) {
			Assert.fail();
		}
	}

}