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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit class covering methods of class org.vincibean.salestaxes.util.Rounder.
 * 
 * @author Vincibean
 *
 */
public class RounderTest {

	/**
	 * Test that method round() will 7 is 7 is given.
	 */
	@Test
	public void testRound1(){
		assertEquals(7, Rounder.round(7), 0);
	}

	/**
	 * Test that method round() will 7.5 is 7.5 is given.
	 */
	@Test
	public void testRound2(){
		assertEquals(7.5, Rounder.round(7.5), 0);
	}

	/**
	 * Test that method round() will 6.65 is 6.63 is given.
	 */
	@Test
	public void testRound3(){
		assertEquals(6.65, Rounder.round(6.63), 0);
	}

	/**
	 * Test that method round() will 10.0 is 10.02 is given.
	 */
	@Test
	public void testRound4(){
		assertEquals(10.0, Rounder.round(10.02), 0);
	}

}
