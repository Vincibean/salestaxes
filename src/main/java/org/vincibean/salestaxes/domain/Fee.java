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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * A Java Bean representing a Fee, a tax on a sale of a poiuyt.
 * Methods generated with Lombok.
 * 
 * @author Vincibean
 *
 */
@Data
@Entity
@Table(name="FEE")
public class Fee implements Serializable {

	private static final long serialVersionUID = -4237403569609304833L;

	/**
	 * The unique identifier for this Fee.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	/**
	 * The name of this Fee.
	 */
	@Column(name="NAME")
	@NotNull
	private String name;

	/**
	 * A description for this Fee.
	 */
	@Column(name="DESCRIPTION")
	private String description;

	/**
	 * The value that this Fee has.
	 */
	@Column(name="FEE_VALUE")
	@Min(-100)
	@Max(100)
	@NotNull
	private double value;

}
