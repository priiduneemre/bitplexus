package com.neemre.bitplexus.backend.crypto.adapter;

import org.springframework.stereotype.Component;

@Component
public class NodeDaemonAdapter extends NodeWrapperAdapter {

	public void addBtcAlertListener(com.neemre.btcdcli4j.daemon.event.AlertListener listener, 
			String chainCode) {
		if (isOperationalBtcChain(chainCode)) {
			wrapperResolver.getBtcdDaemon(chainCode).addAlertListener(listener);
		}
	}

	public void addLtcAlertListener(com.neemre.ltcdcli4j.daemon.event.AlertListener listener,
			String chainCode) {
		if (isOperationalLtcChain(chainCode)) {
			wrapperResolver.getLtcdDaemon(chainCode).addAlertListener(listener);
		}
	}

	public void addBtcBlockListener(com.neemre.btcdcli4j.daemon.event.BlockListener listener,
			String chainCode) {
		if (isOperationalBtcChain(chainCode)) {
			wrapperResolver.getBtcdDaemon(chainCode).addBlockListener(listener);
		}
	}

	public void addLtcBlockListener(com.neemre.ltcdcli4j.daemon.event.BlockListener listener,
			String chainCode) {
		if (isOperationalLtcChain(chainCode)) {
			wrapperResolver.getLtcdDaemon(chainCode).addBlockListener(listener);
		}
	}

	public void addBtcWalletListener(com.neemre.btcdcli4j.daemon.event.WalletListener listener,
			String chainCode) {
		if (isOperationalBtcChain(chainCode)) {
			wrapperResolver.getBtcdDaemon(chainCode).addWalletListener(listener);
		}
	}

	public void addLtcWalletListener(com.neemre.ltcdcli4j.daemon.event.WalletListener listener,
			String chainCode) {
		if (isOperationalLtcChain(chainCode)) {
			wrapperResolver.getLtcdDaemon(chainCode).addWalletListener(listener);
		}
	}
}