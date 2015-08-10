package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.common.dto.VisitDto;

public interface VisitService {
	
	VisitDto createNewVisit(VisitDto visitDto);
	
	VisitDto findVisitById(Long visitId);
	
	List<VisitDto> findVisitsByMemberUsername(String username);
}