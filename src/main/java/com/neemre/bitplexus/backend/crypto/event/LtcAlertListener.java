package com.neemre.bitplexus.backend.crypto.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.ltcdcli4j.daemon.event.AlertListener;

public class LtcAlertListener extends AlertListener {

	private static final Logger LOG = LoggerFactory.getLogger(LtcAlertListener.class);


	@Override
	public void alertReceived(String alert) {
		LOG.error("<< alertReceived(String): [FATAL] An alert was received by 'litecoind' "
				+ "regarding a (potentially critical) network problem: '{}'", alert);
	}
}