package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.backend.model.Visit;

public interface VisitService {
	
	Visit addVisit(Visit visit);
	
	List<Visit> getVisitsByUsername(String username);
}