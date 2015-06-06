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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * A Java Bean representing a Category, a common type for poiuyt objects.
 * Methods generated with Lombok.
 * 
 * @author Vincibean
 *
 */
@Data
@Entity
@Table(name="CATEGORY")
public class Category implements Serializable {

	private static final long serialVersionUID = -8744401854480574688L;
	
	public static final String SALES_TAXES_CATEGORY_NAME = "Sales Tax Good";

	/**
	 * The unique identifier for this Category.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	/**
	 * The name of this Category.
	 */
	@Column(name="NAME")
	@NotNull
	private String name;

	/**
	 * A description for this Category.
	 */
	@Column(name="DESCRIPTION")
	private String description;

	/**
	 * The {@link Fee} associated to this Category.
	 */	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="FEE")
	private Fee fee;

}
