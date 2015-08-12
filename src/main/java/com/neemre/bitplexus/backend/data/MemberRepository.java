package com.neemre.bitplexus.backend.data;

import java.util.List;

import com.neemre.bitplexus.backend.model.Member;

public interface MemberRepository {

	Member findByUsername(String username);
	
	List<String> findMemberRolesByUsername(String username);
}