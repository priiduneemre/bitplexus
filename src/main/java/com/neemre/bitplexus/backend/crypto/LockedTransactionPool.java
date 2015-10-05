package com.neemre.bitplexus.backend.crypto;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.annotation.Resource;

import org.springframework.scheduling.TaskScheduler;

import com.neemre.bitplexus.common.util.DateUtils;

public class LockedTransactionPool {

	private static final int TXN_RELEASE_DELAY = 5;

	private Set<String> networkUids;

	@Resource(name = "lockedTxnPoolScheduler")
	private TaskScheduler scheduler;


	public LockedTransactionPool() {
		networkUids = new ConcurrentSkipListSet<String>();
	}

	public boolean addTransaction(String networkUid) {
		return networkUids.add(networkUid);
	}

	public boolean containsTransaction(String networkUid) {
		return networkUids.contains(networkUid);
	}

	public void removeTransaction(final String networkUid) {
		scheduler.schedule(new Runnable() {
			@Override
			public void run() {
				networkUids.remove(networkUid);
			}
		}, DateUtils.addSeconds(new Date(), TXN_RELEASE_DELAY));
	}
}