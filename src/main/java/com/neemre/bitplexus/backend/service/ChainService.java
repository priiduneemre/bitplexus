package com.neemre.bitplexus.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.neemre.bitplexus.common.dto.ChainDto;

public interface ChainService {
	
	List<ChainDto> findChainsByOperationality(Boolean isOperational);
	
	BigDecimal findChainUnitPrice(String code);
}