package com.neemre.bitplexus.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.neemre.bitplexus.common.dto.ChainDto;

public interface ChainService {

	Boolean checkChainOperationality(Short chainId);

	ChainDto findChainById(Short chainId);

	List<ChainDto> findChainsByOperationality(Boolean isOperational);

	BigDecimal findChainUnitPrice(String code);
}