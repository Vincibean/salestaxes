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

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import com.google.common.base.Optional;

/**
 * Factory class used for creating objects described in the JAXB protocol. 
 */
public final class JaxbFactory {

	private static final Logger logger = LoggerFactory.getLogger(JaxbFactory.class);

	/**
	 * Default constructor, set to private for avoiding instantiation.
	 */
	private JaxbFactory(){
		// Nothing to do here...
	}

	/**
	 * Factory method, creates a {@link Marshaller} from the context given in the constructor; moreover, ensure that
	 * the marshalled XML data is formatted with linefeeds and indentation.  
	 * @return an {@link Optional} object which may or may not contain a {@link Marshaller}
	 */
	public static Optional<Marshaller> createMarshaller(final Class<?> context){
		try{   
			Marshaller marshaller = JAXBContext.newInstance(context).createMarshaller();           
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setSchema(SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
					.newSchema(new ClassPathResource("receipt/Poiuyt.xsd").getFile()));
			marshaller.setEventHandler(new FoobarValidationEventHandler());
			return Optional.fromNullable(marshaller);
		} catch (JAXBException | SAXException | IOException e) {         
			logger.warn("Exception on jaxb factory creation: " ,e);
			return Optional.absent();
		}
	}

}
