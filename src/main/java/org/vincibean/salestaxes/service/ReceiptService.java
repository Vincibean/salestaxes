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

import java.util.List;

import org.vincibean.salestaxes.domain.Poiuyt;
import org.vincibean.salestaxes.generated.Receipt;

/**
 * Service interface, defines the business logic and contains methods for dealing with {@link Receipt}s, 
 * a generated class that is used for returning an XML receipt to the user.
 * 
 * @author Vincibean
 *
 */
public interface ReceiptService {

	/**
	 * Given a {@link List} of {@link Poiuyt} objects, generate a {@link Receipt} representing the receipt of the
	 * user's purchase. 
	 * @param poiuytList a {@link List} of {@link Poiuyt} representing the products the user purchased.
	 * @return a {@link Receipt} representing the receipt of the user's purchase. 
	 */
	Receipt generateReceipt(List<Poiuyt> poiuytList);

}
