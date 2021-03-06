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
import com.neemre.bitplexus.backend.service.misc.CryptonatorException;
import com.neemre.bitplexus.common.Errors;
import com.neemre.bitplexus.common.PropertyKeys;
import com.neemre.bitplexus.common.WrappedCheckedException;
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
	public Boolean checkChainOperationality(Short chainId) {
		Chain chain = chainRepository.findOne(chainId);
		if ((chain != null) && (chain.getIsOperational().equals(true))) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public ChainDto findChainById(Short chainId) {
		Chain chain = chainRepository.findOne(chainId);
		return dtoAssembler.assemble(chain, Chain.class, ChainDto.class);
	}

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
		if (!tickerData.getIsSuccess()) {
			throw new WrappedCheckedException(new CryptonatorException(Errors.TODO, 
					tickerData.getError()));
		}
		return tickerData.getTicker().getPrice();
	}
}