package com.neemre.bitplexus.backend.data.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.ChainRepository;
import com.neemre.bitplexus.backend.model.Chain;

public interface JpaChainRepository extends ChainRepository, JpaRepository<Chain, Short> {

	@Override
	@Query("SELECT ch FROM Chain AS ch INNER JOIN ch.currency AS cu WHERE ch.isOperational = "
			+ ":isOperational ORDER BY cu.currencyId, ch.name")
	List<Chain> findByIsOperational(@Param("isOperational") Boolean isOperational);
}