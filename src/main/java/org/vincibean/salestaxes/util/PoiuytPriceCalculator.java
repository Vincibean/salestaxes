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

import java.util.List;

import org.vincibean.salestaxes.domain.Category;
import org.vincibean.salestaxes.domain.Fee;
import org.vincibean.salestaxes.domain.Poiuyt;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

/**
 * Utility class for calculating prices and taxes of (a basket of) {@link Poiuyt} objects.
 * 
 * @author Vincibean
 *
 */
public class PoiuytPriceCalculator {

	/**
	 * Utility method for calculating the base price of a {@link List} of {@link Poiuyt}s. 
	 * @param poiuytList the {@link List} of {@link Poiuyt} objects whose final price you intend to calculate. 
	 * @return the final price of the input {@link Poiuyt}s.
	 */
	public static double calculatePoiuytBasketTotalBasePrice(final List<Poiuyt> poiuytList) {
		double totalBasePrice = 0;
		for(Poiuyt poiuyt : poiuytList){
			totalBasePrice += poiuyt.getPrice();
		}
		return totalBasePrice;
	}

	/**
	 * Utility method for calculating the final tax amount of the taxes for a {@link List} of {@link Poiuyt}, 
	 * taking into account the duty or discount of the {@link Category}(ies) they belong to.  
	 * @param poiuytList the {@link List} of {@link Poiuyt} objects whose final price you intend to calculate. 
	 * @return the final price of the input basket of {@link Poiuyt} objects.
	 */
	public static double calculatePoiuytBasketTotalTaxes(final List<Poiuyt> poiuytList){
		double taxAmount = 0;
		for(Poiuyt poiuyt : poiuytList){
			taxAmount += calculateTotalTaxesPerPoiuyt(poiuyt);
		}
		return taxAmount;
	}

	/**
	 * Utility method for calculating the total amount of the taxes of a {@link Poiuyt}, taking into 
	 * account the duty or discount of the {@link Category}(ies) it belongs to.  
	 * @param poiuyt the {@link Poiuyt} object whose final price you intend to calculate. 
	 * @return the final price of the input {@link Poiuyt}.
	 */
	public static double calculateTotalTaxesPerPoiuyt(final Poiuyt poiuyt) {
		double totalTaxes = 0;
		for(Double feeAmount : FluentIterable.from(poiuyt.getCategorySet()).transform(createCategoryFeeCalculator())){
			totalTaxes += feeAmount;
		}
		return poiuyt.getPrice() * totalTaxes / 100;
	}

	/**
	 * {@link Function} implementation, given a {@link Category}, get the value associated to the corresponding {@link Fee}.
	 * @return a {@link Function} which get the value associated to a {@link Category}.
	 */
	private static Function<Category, Double> createCategoryFeeCalculator() {
		return new Function<Category, Double>() {
			@Override
			public Double apply(final Category category) {
				return category.getFee().getValue();
			}
		};
	}

}
