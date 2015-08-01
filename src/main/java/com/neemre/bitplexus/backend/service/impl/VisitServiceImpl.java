package com.neemre.bitplexus.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.data.VisitRepository;
import com.neemre.bitplexus.backend.model.Visit;
import com.neemre.bitplexus.backend.service.VisitService;

@Service
public class VisitServiceImpl implements VisitService {
	
	@Autowired
	private VisitRepository visitRepository;

	
	@Transactional
	@Override
	public Visit addVisit(Visit visit) {
		return visitRepository.save(visit);
	}

	@Transactional
	@Override
	public List<Visit> getVisitsByUsername(String username) {
		return visitRepository.findByMemberUsername(username);
	}
}