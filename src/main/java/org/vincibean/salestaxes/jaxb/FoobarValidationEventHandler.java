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

import java.rmi.MarshalException;
import java.rmi.UnmarshalException;

import javax.validation.ValidationException;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A custom {@link ValidationEventHandler} implementation which will simply log the error and
 * return true if the JAXB Provider should attempt to continue the current unmarshal, validate, or marshal operation 
 * after handling this warning/error, false if the provider should terminate the current operation with the appropriate 
 * {@link UnmarshalException}, {@link ValidationException}, or {@link MarshalException}. In particular, this implementation
 * will return false if the upcoming {@link ValidationEvent} is not null, true otherwise.
 * 
 * @author Vincibean
 *
 */
class FoobarValidationEventHandler implements ValidationEventHandler{

	private static final Logger logger = LoggerFactory.getLogger(FoobarValidationEventHandler.class);

	@Override
	public boolean handleEvent(ValidationEvent event) {
		boolean validationResult = true;
		StringBuilder stringBuilder = new StringBuilder("");
		if(event != null){
			stringBuilder.append("A new Validation Event was thrown: ")
			.append("\n Severity:  " + event.getSeverity())
			.append("\n Message:  " + event.getMessage())
			.append("\n Linked Exception:  " + event.getLinkedException());
			if(event.getLocator() != null){
				stringBuilder.append("\n Line Number:  " + event.getLocator() !=  null ? event.getLocator().getLineNumber() : "Unknown")
				.append("\n Column Nummber:  " +  event.getLocator() !=  null ? event.getLocator().getColumnNumber() : "Unknown")
				.append("\n Offset:  " +  event.getLocator() !=  null ? event.getLocator().getOffset() : "Unknown")
				.append("\n Object:  " +  event.getLocator() !=  null ? event.getLocator().getObject() : "Unknown")
				.append("\n Node:  " +  event.getLocator() !=  null ? event.getLocator().getNode() : "Unknown")
				.append("\n URL:  " +  event.getLocator() !=  null ? event.getLocator().getURL() : "Unkown");
			}
			logger.warn(stringBuilder.toString());
			validationResult = false;
		}
		return validationResult;
	}

}
