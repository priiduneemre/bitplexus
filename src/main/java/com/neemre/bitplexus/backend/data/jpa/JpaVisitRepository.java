package com.neemre.bitplexus.backend.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neemre.bitplexus.backend.data.VisitRepository;
import com.neemre.bitplexus.backend.model.Visit;

public interface JpaVisitRepository extends VisitRepository, JpaRepository<Visit, Long> {
	
	@Override
	@Query("SELECT v FROM visit AS v JOIN member AS m WHERE m.username = :username")
	List<Visit> findByMemberUsername(String username);
}