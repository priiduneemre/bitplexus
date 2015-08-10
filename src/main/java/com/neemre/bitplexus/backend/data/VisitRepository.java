package com.neemre.bitplexus.backend.data;

import java.util.List;

import com.neemre.bitplexus.backend.model.Visit;

public interface VisitRepository {
	
	List<Visit> findByMemberUsername(String username);

	Visit findOne(Long visitId);
	
	Visit saveAndFlush(Visit visit);
}