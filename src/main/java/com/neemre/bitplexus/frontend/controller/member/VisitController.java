package com.neemre.bitplexus.frontend.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("v1/member/visits")
public class VisitController {


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showAllVisits() {
		return "layout/page";
	}
}