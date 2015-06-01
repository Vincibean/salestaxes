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

package org.vincibean.salestaxes.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.vincibean.salestaxes.domain.Poiuyt;
import org.vincibean.salestaxes.service.PoiuytService;

import com.google.common.base.Optional;

/**
 * Controller interface representing a component that receives HttpServletRequest and 
 * HttpServletResponse instances (just like a HttpServlet) but is able to participate in an 
 * MVC workflow.
 * This Controller receives requests and serves responses concerning the "market" pages.
 * This Controller is a reusable, thread-safe class, capable of handling multiple HTTP requests throughout the lifecycle of an application. To be able to configure a Controller easily, Controller implementations are encouraged to be (and usually are) JavaBeans.
 * 
 * @author Vincibean
 *
 */
@Controller
@RequestMapping("/market")
public class MarketController {

	@Autowired
	private PoiuytService poiuytService;

	/**
	 * Get the web page describing the market page of FooBar market.
	 * @param model model a {@link Model} interface that defines a holder for model attributes. 
	 * Primarily designed for adding attributes to the model. Allows for accessing the overall 
	 * model as a {@link Map}.
	 * @return a {@link String} representing the name of the HTML market page describing 
	 * the list of Poiuyt in the market.
	 */
	@RequestMapping(value = "/poiuyts", method = RequestMethod.GET)
	public String poiuytList(Model model){
		model.addAttribute("poiuyts", poiuytService.findAllPoiuyts());
		return "market/poiuyt_list";
	}

	/**
	 * Given an ID, get the web page containing details of that {@link Poiuyt}.
	 * @param poiuytId a {@link Long} representing the unique identifier of a {@link Poiuyt}.
	 * @param model a {@link Model} interface that defines a holder for model attributes. 
	 * Primarily designed for adding attributes to the model. Allows for accessing the overall 
	 * model as a {@link Map}.
	 * @return a {@link String} representing the name of the HTML market page describing 
	 * the Poiuyt detail page in the market.
	 */
	@RequestMapping(value = "/poiuyt_detail", method = RequestMethod.GET)
	public String getPoiuytDetail(@RequestParam(value="poiuytId", required=true) Long poiuytId, Model model){
		Optional<Poiuyt> optionalPoiuyt = poiuytService.findPoiuytById(poiuytId);
		String nextPage = "market/poiuyt_list";
		if(optionalPoiuyt.isPresent()){
			model.addAttribute("poiuyt", optionalPoiuyt.get());
			nextPage = "market/poiuyt_detail";
		}
		return nextPage;
	}

}