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

import org.vincibean.salestaxes.domain.Category;

/**
 * Service interface, defines the business logic and contains methods for dealing with {@link Category}. 
 * 
 * @author Vincibean
 *
 */
public interface CategoryService {

	/**
	 * Get all {@link Category} that were previously saved and persisted in the system.
	 * @return all {@link Category} that were previously saved and persisted in the system.
	 */
	Iterable<Category> findAllCategories();

	/**
	 * Saves a given {@link Category}. Use the returned instance for further operations as the save operation 
	 * might have changed the entity instance.
	 * @param category the just saved {@link Category} instance. The save operation 
	 * might have changed the instance.
	 * @return
	 */
	Category save(Category category);

	/**
	 * Given a category ID, retrieve the corresponding {@link Category}.
	 * @param categoryId a {@link Long} representing the ID of the {@link Category} 
	 * you are looking for
	 * @return the {@link Category} the given ID corresponds to. 
	 */
	Category findCategoryById(long categoryId);
	
}
