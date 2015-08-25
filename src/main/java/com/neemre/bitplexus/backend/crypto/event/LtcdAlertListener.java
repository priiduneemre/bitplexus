package com.neemre.bitplexus.backend.crypto.event;

import com.neemre.ltcdcli4j.daemon.event.AlertListener;

public class LtcdAlertListener extends AlertListener {

	@Override
	public void alertReceived(String alert) {
		System.out.println("TODO: logging");
	}
}