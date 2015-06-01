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

import com.google.common.base.Optional;

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
	
	/**
	 * Given a long representing the ID of a {@link Poiuyt}, return an {@link Optional} that may contain
	 * a {@link Poiuyt} (if a {@link Poiuyt} with the given ID was found) or not (if a {@link Poiuyt} 
	 * with the given ID was not found)
	 * @param poiuytId the ID ot the {@link Poiuyt} you are looking for.
	 * @return an {@link Optional} that may contain a {@link Poiuyt} (if a {@link Poiuyt} with the given 
	 * ID was found) or not (if a {@link Poiuyt} with the given ID was not found)
	 */
	Optional<Poiuyt> findPoiuytById(long poiuytId);

}
