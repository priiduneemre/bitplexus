package com.neemre.bitplexus.backend.crypto.event;

import com.neemre.btcdcli4j.daemon.event.AlertListener;

public class BtcdAlertListener extends AlertListener {

	@Override
	public void alertReceived(String alert) {
		System.out.println("TODO: logging");
	}
}