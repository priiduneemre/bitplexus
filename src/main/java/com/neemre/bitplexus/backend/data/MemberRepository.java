package com.neemre.bitplexus.backend.data;

import com.neemre.bitplexus.backend.model.Member;

public interface MemberRepository {

	Member findByUsername(String username);
	
	String findMemberRolesByUsername(String username);
}