package com.neemre.bitplexus.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.neemre.bitplexus.backend.data.MemberRepository;
import com.neemre.bitplexus.backend.model.Member;
import com.neemre.bitplexus.backend.service.MemberService;
import com.neemre.bitplexus.common.Constants;
import com.neemre.bitplexus.common.dto.MemberDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;


	@Transactional(readOnly = true) 
	@Override
	public MemberDto findMemberByUsername(String username) {
		Member member = memberRepository.findByUsername(username);
		System.out.println(member);
		return dtoAssembler.assemble(member, Member.class, MemberDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<String> findMemberRolesByUsername(String username) {
		String memberRolesCsv = memberRepository.findMemberRolesByUsername(username);
		return Lists.newArrayList(Splitter.on(Constants.STRING_COMMA).omitEmptyStrings().trimResults()
				.split(MoreObjects.firstNonNull(memberRolesCsv, Constants.STRING_EMPTY)
						.replaceAll(Constants.STRING_NULL, Constants.STRING_EMPTY)));
	}
}