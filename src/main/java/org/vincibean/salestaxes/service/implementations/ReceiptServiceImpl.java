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

package org.vincibean.salestaxes.service.implementations;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vincibean.salestaxes.domain.Poiuyt;
import org.vincibean.salestaxes.generated.HeaderType;
import org.vincibean.salestaxes.generated.Receipt;
import org.vincibean.salestaxes.service.ReceiptService;
import org.vincibean.salestaxes.service.converter.PoiuytConverter;
import org.vincibean.salestaxes.util.PoiuytPriceCalculator;
import org.vincibean.salestaxes.util.Rounder;

/**
 * Service class implementing methods for dealing with {@link Receipt} objects, 
 * a generated class that is used for returning an XML receipt to the user.
 * 
 * @author Vincibean
 *
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

	Logger logger = LoggerFactory.getLogger(ReceiptServiceImpl.class);

	@Autowired
	private PoiuytConverter poiuytConverter;

	private static final String ISSUER_NAME = "FooBar Market";

	@Override
	public Receipt generateReceipt(final List<Poiuyt> poiuytList) {
		Receipt receipt = new Receipt();
		receipt.setHeader(createHeader());
		for(Poiuyt poiuyt : poiuytList){
			receipt.getPoiuyts().add(poiuytConverter.convert(poiuyt));
		}
		receipt.setTotalBasePrices(PoiuytPriceCalculator.calculatePoiuytBasketTotalBasePrice(poiuytList));
		receipt.setTotalTaxes(Rounder.round(PoiuytPriceCalculator.calculatePoiuytBasketTotalTaxes(poiuytList)));
		receipt.setGrandTotal(receipt.getTotalBasePrices() + receipt.getTotalTaxes());
		return receipt;
	}

	/**
	 * Factory method, create a {@link HeaderType}, an object containing basic informations (e.g. date of emission, 
	 * issuer, etc...)
	 * @return a fully populated {@link HeaderType}.
	 */
	private HeaderType createHeader() {
		HeaderType header = new HeaderType();
		header.setIssuer(ISSUER_NAME);
		try {
			header.setTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		} catch (DatatypeConfigurationException e) {
			logger.debug("Unexpected error while giving the Receipt's Header a timestamp.");
		}
		return header;
	}

}
