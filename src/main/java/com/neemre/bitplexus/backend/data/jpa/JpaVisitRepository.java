package com.neemre.bitplexus.backend.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.VisitRepository;
import com.neemre.bitplexus.backend.model.Visit;

public interface JpaVisitRepository extends VisitRepository, JpaRepository<Visit, Long> {
	
	@Override
	@Query("SELECT v FROM Visit AS v INNER JOIN v.member AS m WHERE m.username = :username "
			+ "ORDER BY v.loginAt DESC")
	List<Visit> findByMemberUsername(@Param("username") String username);
}