package org.vincibean.salestaxes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class AboutController {

	@RequestMapping("/about")
	public String about() {
		return "about/who-we-are";
	}
	
	@RequestMapping("/team")
	public String team(){
		return "about/team";
	}

}