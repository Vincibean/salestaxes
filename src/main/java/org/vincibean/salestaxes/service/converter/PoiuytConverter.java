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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.vincibean.salestaxes.generated.Receipt;

/**
 * {@link Converter} implementation, converts {@link org.vincibean.salestaxes.domain.Poiuyt} objects in {@link org.vincibean.salestaxes.generated.Poiuyt} objects.
 * Useful on {@link Receipt} generation.
 * 
 * @author Vincibean
 *
 */
@Component
public class PoiuytConverter implements Converter<org.vincibean.salestaxes.domain.Poiuyt, org.vincibean.salestaxes.generated.Poiuyt> {

	@Autowired
	private Converter<org.vincibean.salestaxes.domain.Category, org.vincibean.salestaxes.generated.Category> categoryConverter;

	@Override
	public org.vincibean.salestaxes.generated.Poiuyt convert(org.vincibean.salestaxes.domain.Poiuyt source) {
		org.vincibean.salestaxes.generated.Poiuyt target = new org.vincibean.salestaxes.generated.Poiuyt();
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		for(org.vincibean.salestaxes.domain.Category category : source.getCategorySet()){
			target.getCategory().add(categoryConverter.convert(category));
		}
		target.setBasePrice(source.getPrice());
		return target;
	}

}
