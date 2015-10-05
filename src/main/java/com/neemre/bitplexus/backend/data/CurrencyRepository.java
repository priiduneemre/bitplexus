package com.neemre.bitplexus.backend.data;

import java.util.List;

import com.neemre.bitplexus.backend.model.Currency;

public interface CurrencyRepository {

	List<Currency> findAll();

	Currency findByChainCode(String chainCode);
}