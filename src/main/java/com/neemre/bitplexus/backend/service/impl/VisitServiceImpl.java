package com.neemre.bitplexus.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.data.MemberRepository;
import com.neemre.bitplexus.backend.data.VisitRepository;
import com.neemre.bitplexus.backend.model.Visit;
import com.neemre.bitplexus.backend.service.VisitService;
import com.neemre.bitplexus.common.dto.VisitDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class VisitServiceImpl implements VisitService {
	
	@Autowired
	private VisitRepository visitRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	
	
	@Transactional
	@Override
	public VisitDto createNewVisit(VisitDto visitDto) {
		Visit visit = dtoAssembler.disassemble(visitDto, VisitDto.class, Visit.class);
		visit.setMember(memberRepository.findByUsername(visitDto.getUsername()));
		Visit createdVisit = visitRepository.saveAndFlush(visit);
		return dtoAssembler.assemble(createdVisit, Visit.class, VisitDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public VisitDto findVisitById(Long visitId) {
		Visit visit = visitRepository.findOne(visitId);
		return dtoAssembler.assemble(visit, Visit.class, VisitDto.class);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<VisitDto> findVisitsByMemberUsername(String username) {
		List<Visit> visits = visitRepository.findByMemberUsername(username);
		return dtoAssembler.assemble(visits, Visit.class, VisitDto.class);
	}
}