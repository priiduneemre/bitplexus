package com.neemre.bitplexus.backend.crypto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.daemon.BtcdDaemon;
import com.neemre.ltcdcli4j.core.client.LtcdClient;
import com.neemre.ltcdcli4j.daemon.LtcdDaemon;

@Component
public class NodeWrapperResolver {

	private static final String CLIENT_BEAN_SUFFIX = "_CLIENT";
	private static final String DAEMON_BEAN_SUFFIX = "_DAEMON";

	@Autowired
	private ApplicationContext context;


	public BtcdClient getBtcdClient(String chainCode) {
		try {
			return (BtcdClient)context.getBean(chainCode + CLIENT_BEAN_SUFFIX);
		} catch (ClassCastException e) {
			return null;
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	public List<BtcdClient> getBtcdClients(List<String> chainCodes) {
		List<BtcdClient> btcdClients = new ArrayList<BtcdClient>();
		for (String chainCode : chainCodes) {
			BtcdClient btcdClient = getBtcdClient(chainCode);
			if (btcdClient != null) {
				btcdClients.add(btcdClient);
			}
		}
		return btcdClients;
	}

	public BtcdDaemon getBtcdDaemon(String chainCode) {
		try {
			return (BtcdDaemon)context.getBean(chainCode + DAEMON_BEAN_SUFFIX);
		} catch (ClassCastException e) {
			return null;
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	public List<BtcdDaemon> getBtcdDaemons(List<String> chainCodes) {
		List<BtcdDaemon> btcdDaemons = new ArrayList<BtcdDaemon>();
		for (String chainCode : chainCodes) {
			BtcdDaemon btcdDaemon = getBtcdDaemon(chainCode);
			if (btcdDaemon != null) {
				btcdDaemons.add(btcdDaemon);
			}
		}
		return btcdDaemons;
	}

	public LtcdClient getLtcdClient(String chainCode) {
		try {
			return (LtcdClient)context.getBean(chainCode + CLIENT_BEAN_SUFFIX);
		} catch (ClassCastException e) {
			return null;
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	public List<LtcdClient> getLtcdClients(List<String> chainCodes) {
		List<LtcdClient> ltcdClients = new ArrayList<LtcdClient>();
		for (String chainCode : chainCodes) {
			LtcdClient ltcdClient = getLtcdClient(chainCode);
			if (ltcdClient != null) {
				ltcdClients.add(ltcdClient);
			}
		}
		return ltcdClients;
	}

	public LtcdDaemon getLtcdDaemon(String chainCode) {
		try {
			return (LtcdDaemon)context.getBean(chainCode + DAEMON_BEAN_SUFFIX);
		} catch (ClassCastException e) {
			return null;
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	public List<LtcdDaemon> getLtcdDaemons(List<String> chainCodes) {
		List<LtcdDaemon> ltcdDaemons = new ArrayList<LtcdDaemon>();
		for (String chainCode : chainCodes) {
			LtcdDaemon ltcdDaemon = getLtcdDaemon(chainCode);
			if (ltcdDaemon != null) {
				ltcdDaemons.add(ltcdDaemon);
			}
		}
		return ltcdDaemons;
	}
}