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
import java.util.HashSet;

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
import org.vincibean.salestaxes.repository.PoiuytRepository;

import com.google.common.base.Optional;

/**
 * JUnit class covering methods of classes implementing org.vincibean.salestaxes.service.PoiuytService.
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class PoiuytServiceTest {

	/**
	 * Data objects that will be used in test methods.
	 */

	@Autowired
	private PoiuytService poiuytService;

	@Autowired
	private PoiuytRepository poiuytRepository;

	/**
	 * Test that, once a valid Poiuyt is saved, method findAllPoiuyts() will return a 
	 * not null and not empty list of Poiuyts.
	 */
	@Test
	public void testFindAllPoiuyts(){
		poiuytRepository.save(createMockPoiuyt());
		Iterable<Poiuyt> poiuyts = poiuytService.findAllPoiuyts();
		Assert.assertNotNull(poiuyts);
		Assert.assertTrue(poiuyts.iterator().hasNext());
	}

	/**
	 * Test that, if no Poiuyt is saved, method findAllPoiuyts() will return a 
	 * not null and empty list of Poiuyts.
	 */
	@Test
	public void testFindAllPoiuytsNoPoiuyt(){
		poiuytRepository.deleteAll();
		Iterable<Poiuyt> poiuyts = poiuytService.findAllPoiuyts();
		Assert.assertNotNull(poiuyts);
		Assert.assertFalse(poiuyts.iterator().hasNext());
	}
	
	/**
	 * Test that, if a Poiuyt is saved, given the corresponding ID, method 
	 * findPoiuytById() will return that Poiuyt.
	 */
	@Test
	public void testFindPoiuytById(){
		Poiuyt savedPoiuyt = poiuytRepository.save(createMockPoiuyt());
		Optional<Poiuyt> optionalPoiuyt = poiuytService.findPoiuytById(savedPoiuyt.getId());
		Assert.assertTrue(optionalPoiuyt.isPresent());
		Assert.assertEquals(savedPoiuyt.getId(), optionalPoiuyt.get().getId());
	}
	
	/**
	 * Test that, given a not existing ID, method findPoiuytById() will not return any Poiuyt.
	 */
	@Test
	public void testFindPoiuytByNotExistingId(){
		Optional<Poiuyt> optionalPoiuyt = poiuytService.findPoiuytById(Long.MAX_VALUE);
		Assert.assertFalse(optionalPoiuyt.isPresent());
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
