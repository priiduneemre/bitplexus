package com.neemre.bitplexus.backend.crypto.event;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.common.Defaults;
import com.neemre.bitplexus.common.WrappedCheckedException;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.util.DateUtils;
import com.neemre.btcdcli4j.core.domain.Transaction;
import com.neemre.btcdcli4j.daemon.event.WalletListener;

public class BtcWalletListener extends WalletListener {

	@Autowired
	private AddressService addressService;
	@Autowired
	private TransactionService transactionService;

	@Resource(name = "balanceUpdateScheduler")
	private TaskScheduler scheduler;

	private String chainCode;


	public BtcWalletListener(String chainCode) {
		this.chainCode = chainCode;
	}

	@Override
	public void walletChanged(final Transaction transaction) {
		if (transactionService.findTransactionByNetworkUid(transaction.getTxId()) == null) {
			try {
				transactionService.receiveNewTransaction(transaction.getTxId(), chainCode);
			} catch (NodeWrapperException e) {
				throw new WrappedCheckedException(e);
			}
		}
		scheduler.schedule(new Runnable() {
			@Override
			public void run() {
				for (String encodedForm : addressService.findAddressesByTransactionNetworkUid(
						transaction.getTxId())) {
					try {
						AddressDto txnAddress = addressService.findAddressByEncodedForm(encodedForm);
						if (txnAddress.getWalletId() != null) {
							addressService.updateAddressBalance(txnAddress.getAddressId());
						}
					} catch (NodeWrapperException e) {
						throw new WrappedCheckedException(e);
					}
				}
			}
		}, DateUtils.addSeconds(new Date(), Defaults.NEW_TXN_SETTLE_TIME));
	}
}