package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.common.dto.MemberDto;

public interface MemberService {

	MemberDto findMemberByUsername(String username);

	List<String> findMemberRolesByUsername(String username);
}