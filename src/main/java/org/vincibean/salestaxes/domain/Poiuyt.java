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

package org.vincibean.salestaxes.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * A Java Bean representing a Poiuyt, a (impossible) product.
 * Belongs to a category, has a price and a final (with taxes) price. 
 * Methods generated with Lombok.
 * 
 * @author Vincibean
 *
 */
@Data
@Entity
@Table(name="POIUYT")
public class Poiuyt implements Serializable {

	private static final long serialVersionUID = -4038301102441428967L;

	/**
	 * The unique identifier for this Poiuyt.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	/**
	 * The name of this Poiuyt.
	 */
	@Column(name="NAME")
	@NotNull
	private String name;

	/**
	 * A description for this Poiuyt.
	 */
	@Column(name="DESCRIPTION")
	private String description;

	/**
	 * The {@link Set} of {@link Category}s associated to this Poiuyt.
	 */
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "CATEGORY_POIUYT",
	joinColumns = {
			@JoinColumn(name = "POIUYT_ID", nullable = false) 
	}, 
	inverseJoinColumns = { 
			@JoinColumn(name = "CATEGORY_ID", nullable = false) 
	} 
			)
	private Set<Category> categorySet;

}
