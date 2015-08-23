package com.neemre.bitplexus.backend.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.MemberRepository;
import com.neemre.bitplexus.backend.model.Member;

public interface JpaMemberRepository extends MemberRepository, JpaRepository<Member, Integer> {
	
	@Override
	@Query("SELECT m FROM Member AS m WHERE m.username = :username")
	Member findByUsername(@Param("username") String username);
	
	@Override
	@Procedure("f_get_member_roles")
	String findMemberRolesByUsername(String username);
}