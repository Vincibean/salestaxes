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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vincibean.salestaxes.domain.Category;
import org.vincibean.salestaxes.repository.CategoryRepository;
import org.vincibean.salestaxes.service.CategoryService;

/**
 * Service class implementing methods for dealing with {@link Category}. 
 * 
 * @author Vincibean
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Iterable<Category> findAllCategories(){
		return categoryRepository.findAll();
	}

	@Override
	public Category save(final Category category) {
		return categoryRepository.save(category);
	}

}
