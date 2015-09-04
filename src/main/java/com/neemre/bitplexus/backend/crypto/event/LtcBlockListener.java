package com.neemre.bitplexus.backend.crypto.event;

import org.springframework.beans.factory.annotation.Autowired;

import com.neemre.bitplexus.backend.crypto.NodeWrapperException;
import com.neemre.bitplexus.backend.service.AddressService;
import com.neemre.bitplexus.backend.service.TransactionService;
import com.neemre.bitplexus.common.WrappedCheckedException;
import com.neemre.bitplexus.common.dto.AddressDto;
import com.neemre.bitplexus.common.util.DateUtils;
import com.neemre.ltcdcli4j.core.domain.Block;
import com.neemre.ltcdcli4j.daemon.event.BlockListener;

public class LtcBlockListener extends BlockListener {

	@Autowired
	private AddressService addressService;
	@Autowired
	private TransactionService transactionService;

	private String chainCode;


	public LtcBlockListener(String chainCode) {
		this.chainCode = chainCode;
	}

	@Override
	public void blockDetected(Block block) {
		transactionService.confirmTransactions(block.getTx(), block.getHeight(), DateUtils.toDate(
				block.getTime()), chainCode);
		transactionService.completeTransactions(block.getHeight(), DateUtils.toDate(block.getTime()),
				chainCode);
		transactionService.dropTransactions(chainCode);
		for (AddressDto walletAddress : addressService.findWalletAddressesByChainCode(chainCode)) {
			try {
				addressService.updateAddressBalance(walletAddress.getAddressId());
			} catch (NodeWrapperException e) {
				throw new WrappedCheckedException(e);
			}
		}
	}
}