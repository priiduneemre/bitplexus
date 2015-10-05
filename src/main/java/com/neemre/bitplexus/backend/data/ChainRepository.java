package com.neemre.bitplexus.backend.data;

import java.util.List;

import com.neemre.bitplexus.backend.model.Chain;

public interface ChainRepository {

	List<Chain> findByIsOperational(Boolean isOperational);

	Chain findOne(Short chainId);
}