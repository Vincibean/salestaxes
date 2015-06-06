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
 * {@link Converter} implementation, converts {@link org.vincibean.salestaxes.domain.Category} objects in {@link org.vincibean.salestaxes.generated.Category} objects.
 * Useful on {@link Receipt} generation.
 * 
 * @author Vincibean
 *
 */
@Component
class CategoryConverter implements Converter<org.vincibean.salestaxes.domain.Category, org.vincibean.salestaxes.generated.Category> {

	@Autowired
	private Converter<org.vincibean.salestaxes.domain.Fee, org.vincibean.salestaxes.generated.Fee> feeConverter;

	@Override
	public org.vincibean.salestaxes.generated.Category convert(org.vincibean.salestaxes.domain.Category source) {
		org.vincibean.salestaxes.generated.Category target = new org.vincibean.salestaxes.generated.Category();
		target.setId(source.getId());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setFee(feeConverter.convert(source.getFee()));
		return target;
	}

}
