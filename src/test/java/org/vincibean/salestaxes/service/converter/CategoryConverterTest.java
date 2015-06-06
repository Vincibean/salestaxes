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

package org.vincibean.salestaxes.service.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vincibean.salestaxes.configuration.SalesTaxesApplication;
import org.vincibean.salestaxes.domain.Fee;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.service.converter.CategoryConverter.
 * 
 * @author Vincibean
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SalesTaxesApplication.class)
@WebAppConfiguration
public class CategoryConverterTest {

	@Autowired
	private Converter<org.vincibean.salestaxes.domain.Category, org.vincibean.salestaxes.generated.Category> categoryConverter;

	/**
	 * Test that method convert() will return a not null object of a type different from the source one,
	 * but with the same values.
	 */
	@Test
	public void testConvert(){
		org.vincibean.salestaxes.domain.Category source = Mockito.mock(org.vincibean.salestaxes.domain.Category.class);
		Mockito.when(source.getFee()).thenReturn(Mockito.mock(Fee.class));
		org.vincibean.salestaxes.generated.Category target = categoryConverter.convert(source);
		assertNotNull(target);
		assertNotEquals(source.getClass(), target.getClass());
		assertEquals(source.getName(), target.getName());
		assertEquals(source.getDescription(), target.getDescription());
		assertEquals(source.getFee().getName(), target.getFee().getName());
		assertEquals(source.getFee().getDescription(), target.getFee().getDescription());
		assertEquals(source.getFee().getValue(), target.getFee().getValue(), 0.001);
	}

	/**
	 * Test that method convert() will return a {@link NullPointerException} if <code>null</code> is given:
	 * checks for validity should be performed by other classes.
	 */
	@Test(expected=NullPointerException.class)
	public void testConvertNullInput(){
		categoryConverter.convert(null);
	}

}
