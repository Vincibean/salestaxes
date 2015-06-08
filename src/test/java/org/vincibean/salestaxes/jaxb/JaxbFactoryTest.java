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

package org.vincibean.salestaxes.jaxb;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;
import org.vincibean.salestaxes.generated.Receipt;
import org.vincibean.salestaxes.jaxb.JaxbFactory;

import com.google.common.base.Optional;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.jaxb.JaxbFactory.
 * 
 * @author Vincibean
 *
 */
public class JaxbFactoryTest {

	/**
	 * Test that factory method createMarshaller() will return a Marshaller:
	 * <br>- whose Property JAXB_FORMATTED_OUTPUT is true;
	 * <br>- with a not <code>null</code> schema;
	 * <br>- with a not <code>null</code> event handler. 
	 */
	@Test
	public void testCreateMarshaller(){		
		try {
			Optional<Marshaller> marshaller = JaxbFactory.createMarshaller(Receipt.class);
			assertTrue(marshaller.isPresent());
			assertTrue((boolean)marshaller.get().getProperty(Marshaller.JAXB_FORMATTED_OUTPUT));
			assertNotNull(marshaller.get().getSchema());
			assertNotNull(marshaller.get().getEventHandler());
		} catch (JAXBException e) {
			fail();
		}
	}

}