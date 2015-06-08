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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller interface representing a component that receives HttpServletRequest and 
 * HttpServletResponse instances (just like a HttpServlet) but is able to participate in an 
 * MVC workflow.
 * This Controller receives requests and serves responses concerning the "index" page.
 * This Controller is a reusable, thread-safe class, capable of handling multiple HTTP requests throughout the lifecycle of an application. To be able to configure a Controller easily, Controller implementations are encouraged to be (and usually are) JavaBeans.
 * 
 * @author Vincibean
 *
 */
@Controller
class HomeController {

	/**
	 * Get the web page describing the index page of FooBar market.
	 * @return a {@link String} representing the name of the HTML index page describing 
	 * the FooBar market.
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}