package com.neemre.bitplexus.backend.crypto.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.common.WrappedCheckedException;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.ltcdcli4j.core.domain.Transaction;
import com.neemre.ltcdcli4j.daemon.event.WalletListener;

public class LtcdWalletListener extends WalletListener {

	@Autowired
	private AddressService addressService;
	@Autowired
	private TransactionService transactionService;

	private String chainCode;


	public LtcdWalletListener(String chainCode) {
		this.chainCode = chainCode;
	}

	@Override
	public void walletChanged(Transaction transaction) {
		if (transactionService.findTransactionByNetworkUid(transaction.getTxId()) == null) {
			//TODO: create new incoming tx & insert it into db
		}
		for (String encodedForm : addressService.findAddressesByTransactionNetworkUid(
				transaction.getTxId())) {
			try {
				AddressDto txnAddress = addressService.findAddressByEncodedForm(encodedForm);
				addressService.updateAddressBalance(txnAddress.getAddressId());
			} catch (NodeWrapperException e) {
				throw new WrappedCheckedException(e);
			}
		}
	}
}