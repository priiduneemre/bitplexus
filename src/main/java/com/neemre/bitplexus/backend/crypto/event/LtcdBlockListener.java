package com.neemre.bitplexus.backend.crypto.event;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.ltcdcli4j.core.domain.Block;
import com.neemre.ltcdcli4j.daemon.event.BlockListener;

public class LtcdBlockListener extends BlockListener {

	@Autowired
	private AddressService addressService;
	@Autowired
	private TransactionService transactionService;

	private String chainCode;


	public LtcdBlockListener(String chainCode) {
		this.chainCode = chainCode;
	}

	@Override
	public void blockDetected(Block block) {
		transactionService.confirmTransactions(block.getTx(), block.getHeight(),
				new Date(TimeUnit.SECONDS.toMillis(block.getTime())), chainCode);
		transactionService.completeTransactions(block.getHeight(),
				new Date(TimeUnit.SECONDS.toMillis(block.getTime())), chainCode);
		transactionService.dropTransactions(chainCode);
		for (AddressDto walletAddress : addressService.findWalletAddressesByChainCode(chainCode)) {
			try {
				addressService.updateAddressBalance(walletAddress.getAddressId());
			} catch (NodeWrapperException e) {
				System.out.println("TODO: logging");
			}
		}	
	}
}