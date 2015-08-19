package com.neemre.bitplexus.backend.data;

import com.neemre.bitplexus.backend.model.Currency;

public interface CurrencyRepository {

	Currency findByChainCode(String chainCode);
}