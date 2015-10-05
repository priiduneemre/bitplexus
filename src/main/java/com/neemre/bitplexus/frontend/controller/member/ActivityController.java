package com.neemre.bitplexus.frontend.controller.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neemre.bitplexus.backend.service.VisitService;
import com.neemre.bitplexus.frontend.controller.misc.Views;

import static com.neemre.bitplexus.frontend.controller.misc.RequestAttributes.*;
import static com.neemre.bitplexus.frontend.controller.misc.RequestMappings.*;

@Controller
@RequestMapping(MEMBER_MAPPING)
public class ActivityController {

	@Autowired
	private VisitService visitService;


	@RequestMapping(value = {VIEW_ALL_EVENTS_MAPPING_1, VIEW_ALL_EVENTS_MAPPING_2}, 
			method = RequestMethod.GET)
	public String viewAllEvents(ModelMap model) {
		List<Object> events = new ArrayList<Object>();
		events.addAll(visitService.findVisitsByMemberUsername("rebel_sloth"));
		model.addAttribute(EVENTS_ATTRIBUTE, events);
		return Views.ALL_EVENTS_VIEW.getPath();
	}
}