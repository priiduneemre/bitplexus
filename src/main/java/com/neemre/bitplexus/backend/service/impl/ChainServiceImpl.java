package com.neemre.bitplexus.backend.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.neemre.bitplexus.backend.data.ChainRepository;
import com.neemre.bitplexus.backend.data.CurrencyRepository;
import com.neemre.bitplexus.backend.model.Chain;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.common.PropertyKeys;
import com.neemre.bitplexus.common.dto.ChainDto;
import com.neemre.bitplexus.common.dto.assembly.DtoAssembler;
import com.neemre.bitplexus.common.dto.virtual.TickerWrapperDto;

@Service
public class ChainServiceImpl implements ChainService {

	@Autowired
	private ChainRepository chainRepository;
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Resource(name = "dtoAssembler")
	private DtoAssembler dtoAssembler;
	@Resource(name = "restTemplate")
	private RestTemplate restTemplate;
	@Resource(name = "tickerApiClientConfig")
	private Properties tickerProperties;
	
	
	@Transactional(readOnly = true)
	@Override
	public List<ChainDto> findChainsByOperationality(Boolean isOperational) {
		List<Chain> chains = chainRepository.findByIsOperational(isOperational);
		return dtoAssembler.assemble(chains, Chain.class, ChainDto.class);
	}
	
	@Transactional(readOnly = true)
	@Override
	public BigDecimal findChainUnitPrice(String code) {
		String currencyName = currencyRepository.findByChainCode(code).getName().trim().toUpperCase();
		String urlBase = tickerProperties.getProperty(PropertyKeys.TICKER_URLBASE.getValue());
		String endpointUri = tickerProperties.getProperty(PropertyKeys.valueOf(
				String.format("%s_%s_%s", "TICKER", "ENDPOINT", currencyName)).getValue());
		TickerWrapperDto tickerData = restTemplate.getForObject(String.format("%s/%s", urlBase, 
				endpointUri), TickerWrapperDto.class);
		return tickerData.getTicker().getPrice();
	}
}