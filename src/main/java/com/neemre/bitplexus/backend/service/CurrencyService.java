package com.neemre.bitplexus.backend.service;

import java.util.List;

import com.neemre.bitplexus.common.dto.CurrencyDto;

public interface CurrencyService {

	List<CurrencyDto> findAllCurrencies();

	CurrencyDto findCurrencyByChainCode(String chainCode);
}