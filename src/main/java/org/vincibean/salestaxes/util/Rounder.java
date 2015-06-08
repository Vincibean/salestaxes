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

package org.vincibean.salestaxes.util;

/**
 * Utility class for rounding prices and taxes.
 * 
 * @author Vincibean
 *
 */
public class Rounder {

	/**
	 * Get the input number, rounded using the rounding logic of FooBar Market. 
	 * Logic of FooBar Market is:
	 * - the number is rounded to the 2nd digit after the decimal point;
	 * - the number is rounded to the neared 0.05
	 * E.g.:
	 * Input: 7.0 ->
	 * Output: 7.0
	 * | 
	 * Input: 7.5 ->
	 * Output: 7.5
	 * |
	 * Input: 6.63 ->
	 * Output: 6.65
	 * |
	 * Input: 10.02 ->
	 * Output: 10.0
	 * @param number2round a double representing the number to be rounded.
	 * @return the input number, rounded using the rounding logic of FooBar Market.
	 */
	public static double round(final double number2round) {
		return Math.round(number2round * 20) / 20.0;
	}

}
