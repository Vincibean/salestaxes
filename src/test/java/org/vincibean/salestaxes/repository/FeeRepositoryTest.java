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
import org.vincibean.salestaxes.domain.Fee;

/**
 * JUnit class covering methods of class implementing org.vincibean.salestaxes.repositry.FeeRepository
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class FeeRepositoryTest {

	/**
	 * Object data that will be used in test methods.
	 */

	@Autowired
	private FeeRepository feeRepository;

	/**
	 * Test that a valid {@link Fee} object will be saved without any error (Exception) occurring.
	 */
	@Test
	public void testSaveValidFee(){
		Fee validFee = new Fee();
		validFee.setName("JUnit valid Fee");
		validFee.setDescription("A mock Fee object for JUnit tests");
		validFee.setValue(12.7f);
		Assert.assertNotNull(feeRepository.save(validFee).getId());
	}

	/**
	 * Test that saving an invalid {@link Fee} object (too high a value) will 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSaveInvalidFeeHigh(){
		Fee validFee = new Fee();
		validFee.setName("JUnit invalid Fee");
		validFee.setDescription("A mock Fee object for JUnit tests");
		validFee.setValue(134567892.7f);
		Assert.assertNotNull(feeRepository.save(validFee).getId());
	}
	
	/**
	 * Test that saving an invalid {@link Fee} object (too low a value) will 
	 * will throw a {@link ConstraintViolationException}.
	 */
	@Test(expected=ConstraintViolationException.class)
	public void testSaveInvalidFeeLow(){
		Fee validFee = new Fee();
		validFee.setName("JUnit invalid Fee");
		validFee.setDescription("A mock Fee object for JUnit tests");
		validFee.setValue(-134567892.7f);
		Assert.assertNotNull(feeRepository.save(validFee).getId());
	}

}
