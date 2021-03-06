package com.neemre.bitplexus.backend.data;

import java.math.BigDecimal;

import com.neemre.bitplexus.backend.model.TransactionEndpoint;
import com.neemre.bitplexus.backend.model.reference.TransactionEndpointType;

public interface TransactionEndpointRepository {

	TransactionEndpoint findOne(Long transactionEndpointId);

	TransactionEndpointType findTransactionEndpointTypeByCode(String code);

	TransactionEndpoint saveAndFlush(TransactionEndpoint transactionEndpoint);

	BigDecimal sumAmountByAddressIdAndTxnEndpointTypeCode(Long addressId, String txnEndpointTypeCode);
}