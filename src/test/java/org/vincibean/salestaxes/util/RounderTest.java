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
