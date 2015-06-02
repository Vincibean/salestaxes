package org.vincibean.salestaxes.util;

import java.util.List;

import org.vincibean.salestaxes.domain.Category;
import org.vincibean.salestaxes.domain.Poiuyt;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

/**
 * Utility class for calculating the final price of (a basket of) {@link Poiuyt} objects.
 * 
 * @author Vincibean
 *
 */
public class PoiuytPriceCalculator {

	/**
	 * Utility method for calculating the final price of a {@link Poiuyt}, taking into 
	 * account its base price and the duty or discount of the {@link Category}(ies) 
	 * it belongs to.  
	 * @param poiuyt the {@link Poiuyt} object whose final price you intend to calculate. 
	 * @return the final price of the input {@link Poiuyt}.
	 */
	public static double calculatePoiuytPrice(final Poiuyt poiuyt){
		double result = 100;
		for(Double feeAmount : FluentIterable.from(poiuyt.getCategorySet()).transform(categoryFeeCalculator())){
			result += feeAmount;
		}
		return poiuyt.getPrice() * result / 100;
	}
	
	/**
	 * Utility method for calculating the final price of a {@link List} of {@link Poiuyt}, 
	 * taking into account their base price and the duty or discount of the {@link Category}(ies) 
	 * they belong to.  
	 * @param poiuytList the {@link List} of {@link Poiuyt} objects whose final price you intend to calculate. 
	 * @return the final price of the input basket of {@link Poiuyt} objects.
	 */
	public static double calculatePoiuytBasketPrice(final List<Poiuyt> poiuytList){
		double result = 0;
		for(Poiuyt poiuyt : poiuytList){
			result += calculatePoiuytPrice(poiuyt);
		}
		return result;
	}

	private static Function<Category, Double> categoryFeeCalculator() {
		return new Function<Category, Double>() {
			@Override
			public Double apply(final Category category) {
				return category.getFee().getValue();
			}
		};
	}

}
