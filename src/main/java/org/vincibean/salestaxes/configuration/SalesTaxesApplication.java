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

package org.vincibean.salestaxes.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * A configuration class that declares one or more @Bean methods for this application and also 
 * triggers auto-configuration and component scanning. 
 * 
 * @author Vincibean
 *
 */
@SpringBootApplication
@ComponentScan("org.vincibean.salestaxes")
public class SalesTaxesApplication {

	/**
	 * Get the web page describing the index page of FooBar market.
	 * @return a {@link String} representing the name of the index HTML page of 
	 * FooBar market.
	 */
    public static void main(String[] args) {
        SpringApplication.run(SalesTaxesApplication.class, args);
    }
}
