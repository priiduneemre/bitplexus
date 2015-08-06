package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.common.dto.VisitDto;

public interface VisitService {
	
	VisitDto createNewVisit(VisitDto visitDto);
	
	List<VisitDto> findVisitsByMemberUsername(String username);
}