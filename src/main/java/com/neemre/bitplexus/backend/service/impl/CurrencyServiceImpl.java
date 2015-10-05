package com.neemre.bitplexus.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neemre.bitplexus.backend.data.CurrencyRepository;
import com.neemre.bitplexus.backend.model.Currency;
import com.neemre.bitplexus.backend.service.CurrencyService;
import com.neemre.bitplexus.common.dto.CurrencyDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;


	@Transactional(readOnly = true)
	@Override
	public List<CurrencyDto> findAllCurrencies() {
		List<Currency> currencies = currencyRepository.findAll();
		return dtoAssembler.assemble(currencies, Currency.class, CurrencyDto.class);
	}

	@Transactional(readOnly = true)
	@Override
	public CurrencyDto findCurrencyByChainCode(String chainCode) {
		Currency currency = currencyRepository.findByChainCode(chainCode);
		return dtoAssembler.assemble(currency, Currency.class, CurrencyDto.class);
	}
}