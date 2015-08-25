package com.neemre.bitplexus.backend.crypto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, BtcdClient> getBtcdClients(List<String> chainCodes) {
		Map<String, BtcdClient> btcdClients = new HashMap<String, BtcdClient>();
		for (String chainCode : chainCodes) {
			BtcdClient btcdClient = getBtcdClient(chainCode);
			if (btcdClient != null) {
				btcdClients.put(chainCode, btcdClient);
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

	public Map<String, BtcdDaemon> getBtcdDaemons(List<String> chainCodes) {
		Map<String, BtcdDaemon> btcdDaemons = new HashMap<String, BtcdDaemon>();
		for (String chainCode : chainCodes) {
			BtcdDaemon btcdDaemon = getBtcdDaemon(chainCode);
			if (btcdDaemon != null) {
				btcdDaemons.put(chainCode, btcdDaemon);
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

	public Map<String, LtcdClient> getLtcdClients(List<String> chainCodes) {
		Map<String, LtcdClient> ltcdClients = new HashMap<String, LtcdClient>();
		for (String chainCode : chainCodes) {
			LtcdClient ltcdClient = getLtcdClient(chainCode);
			if (ltcdClient != null) {
				ltcdClients.put(chainCode, ltcdClient);
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

	public Map<String, LtcdDaemon> getLtcdDaemons(List<String> chainCodes) {
		Map<String, LtcdDaemon> ltcdDaemons = new HashMap<String, LtcdDaemon>();
		for (String chainCode : chainCodes) {
			LtcdDaemon ltcdDaemon = getLtcdDaemon(chainCode);
			if (ltcdDaemon != null) {
				ltcdDaemons.put(chainCode, ltcdDaemon);
			}
		}
		return ltcdDaemons;
	}
}