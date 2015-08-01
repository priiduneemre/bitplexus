package com.neemre.bitplexus.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.neemre.bitplexus.backend.model.Visit;
import com.neemre.bitplexus.backend.service.VisitService;

@Controller
@RequestMapping("member/visits")
public class VisitController {

	@Autowired
	private VisitService visitService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewShowAll(ModelMap model) {
		List<Visit> visits = visitService.getVisitsByUsername("rebel_sloth");
		model.addAttribute("visits", visits);
		return "member/visit_list";
	}
}