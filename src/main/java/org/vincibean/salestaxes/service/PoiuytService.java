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

import org.vincibean.salestaxes.domain.Poiuyt;

/**
 * Service interface, defines the business logic and contains methods for dealing with {@link Poiuyt}. 
 * 
 * @author Vincibean
 *
 */
public interface PoiuytService {

	/**
	 * Retrieve all {@link Poiuyt} that were persisted on database.
	 * @return all {@link Poiuyt} that were persisted on database, if at least one aws persisted;
	 * an empty {@link Iterable} otherwise.
	 */
	Iterable<Poiuyt> findAllPoiuyts();

}
