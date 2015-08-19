package com.neemre.bitplexus.backend.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.CurrencyRepository;
import com.neemre.bitplexus.backend.model.Currency;

public interface JpaCurrencyRepository extends CurrencyRepository, JpaRepository<Currency, Short> {

	@Override
	@Query("SELECT cu FROM Currency AS cu INNER JOIN cu.chains AS ch WHERE ch.code = :chainCode")
	Currency findByChainCode(@Param("chainCode") String chainCode);
}