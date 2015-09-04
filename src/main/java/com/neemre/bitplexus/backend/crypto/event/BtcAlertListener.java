package com.neemre.bitplexus.backend.crypto.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.daemon.event.AlertListener;

public class BtcAlertListener extends AlertListener {

	private static final Logger LOG = LoggerFactory.getLogger(BtcAlertListener.class);


	@Override
	public void alertReceived(String alert) {
		LOG.error("<< alertReceived(String): [FATAL] An alert was received by 'bitcoind' "
				+ "regarding a (potentially critical) network problem: '{}'", alert);
	}
}