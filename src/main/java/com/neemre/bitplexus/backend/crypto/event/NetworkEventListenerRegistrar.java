package com.neemre.bitplexus.backend.crypto.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.neemre.bitplexus.backend.crypto.adapter.NodeDaemonAdapter;
import com.neemre.bitplexus.backend.service.ChainService;
import com.neemre.bitplexus.common.dto.ChainDto;
import com.neemre.bitplexus.common.dto.ChainDto.CodeExtractor;

@Component
public class NetworkEventListenerRegistrar implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ChainService chainService;

	@Autowired
	private AutowireCapableBeanFactory beanFactory;
	@Autowired
	private NodeDaemonAdapter nodeDaemon;

	private boolean isRegistered;


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (!isRegistered) {
			registerBtcEventListeners();
			registerLtcEventListeners();
		}
		isRegistered = true;
	}

	private void registerBtcEventListeners() {
		List<ChainDto> chains = chainService.findChainsByOperationality(true);
		List<String> chainCodes = Lists.transform(chains, new CodeExtractor());
		for (String chainCode : chainCodes) {
			nodeDaemon.addBtcAlertListener(autowireBeanProperties(new BtcAlertListener()), 
					chainCode);
			nodeDaemon.addBtcBlockListener(autowireBeanProperties(new BtcBlockListener(chainCode)),
					chainCode);
			nodeDaemon.addBtcWalletListener(autowireBeanProperties(new BtcWalletListener(chainCode)),
					chainCode);
		}
	}

	private void registerLtcEventListeners() {
		List<ChainDto> chains = chainService.findChainsByOperationality(true);
		List<String> chainCodes = Lists.transform(chains, new CodeExtractor());
		for (String chainCode : chainCodes) {
			nodeDaemon.addLtcAlertListener(autowireBeanProperties(new LtcAlertListener()), 
					chainCode);
			nodeDaemon.addLtcBlockListener(autowireBeanProperties(new LtcBlockListener(chainCode)),
					chainCode);
			nodeDaemon.addLtcWalletListener(autowireBeanProperties(new LtcWalletListener(chainCode)),
					chainCode);
		}
	}

	private <T> T autowireBeanProperties(T bean) {
		beanFactory.autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
		return bean;
	}
}