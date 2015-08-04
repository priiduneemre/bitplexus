package com.neemre.bitplexus.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.data.VisitRepository;
import com.neemre.bitplexus.backend.model.Visit;
import com.neemre.bitplexus.backend.service.VisitService;
import com.neemre.bitplexus.common.dto.VisitDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class VisitServiceImpl implements VisitService {
	
	@Autowired
	private VisitRepository visitRepository;
	
	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	
	
	@Transactional
	@Override
	public VisitDto addVisit(VisitDto visitDto) {
		Visit visit = dtoAssembler.disassemble(visitDto, VisitDto.class, Visit.class);
		Visit savedVisit = visitRepository.save(visit);
		return dtoAssembler.assemble(savedVisit, Visit.class, VisitDto.class);
	}

	@Transactional
	@Override
	public List<VisitDto> getVisitsByUsername(String username) {
		List<Visit> visits = visitRepository.findByMemberUsername(username);
		return dtoAssembler.assemble(visits, Visit.class, VisitDto.class);
	}
}