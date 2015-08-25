package com.neemre.bitplexus.backend.crypto.event;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.neemre.bitplexus.backend.crypto.NodeWrapperResolver;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.common.dto.ChainDto;
import com.neemre.bitplexus.common.dto.ChainDto.CodeExtractor;
import com.neemre.btcdcli4j.daemon.BtcdDaemon;
import com.neemre.ltcdcli4j.daemon.LtcdDaemon;

@Component
public class NetworkEventListenerRegistrar implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ChainService chainService;
	@Autowired
	private NodeWrapperResolver daemonResolver;

	private boolean isRegistered;


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (!isRegistered) {
			registerBtcdEventListeners();
			registerLtcdEventListeners();
		}
		isRegistered = true;
	}

	private void registerBtcdEventListeners() {
		for (Map.Entry<String, BtcdDaemon> entry : getAllBtcdDaemons().entrySet()) {
			final String chainCode = entry.getKey();
			final BtcdDaemon btcdDaemon = entry.getValue();
			btcdDaemon.addAlertListener(new BtcdAlertListener());
			btcdDaemon.addBlockListener(new BtcdBlockListener(chainCode));
			btcdDaemon.addWalletListener(new BtcdWalletListener(chainCode));
		}
	}

	private void registerLtcdEventListeners() {
		for (Map.Entry<String, LtcdDaemon> entry : getAllLtcdDaemons().entrySet()) {
			final String chainCode = entry.getKey();
			final LtcdDaemon ltcdDaemon = entry.getValue();
			ltcdDaemon.addAlertListener(new LtcdAlertListener());
			ltcdDaemon.addBlockListener(new LtcdBlockListener(chainCode));
			ltcdDaemon.addWalletListener(new LtcdWalletListener(chainCode));
		}
	}

	private Map<String, BtcdDaemon> getAllBtcdDaemons() {
		List<ChainDto> chains = chainService.findChainsByOperationality(true);
		return daemonResolver.getBtcdDaemons(Lists.transform(chains, new CodeExtractor()));
	}

	private Map<String, LtcdDaemon> getAllLtcdDaemons() {
		List<ChainDto> chains = chainService.findChainsByOperationality(true);
		return daemonResolver.getLtcdDaemons(Lists.transform(chains, new CodeExtractor()));
	}
}