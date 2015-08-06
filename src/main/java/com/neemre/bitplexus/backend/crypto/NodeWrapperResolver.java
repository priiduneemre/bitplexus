package com.neemre.bitplexus.backend.crypto;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.daemon.BtcdDaemon;

@Component
public class NodeWrapperResolver {

	private static final String CLIENT_BEAN_SUFFIX = "_CLIENT";
	private static final String DAEMON_BEAN_SUFFIX = "_DAEMON";

	@Autowired
	private ApplicationContext context;


	public BtcdClient getBtcdClient(String chainCode) {
		try {
			return (BtcdClient)context.getBean(chainCode + CLIENT_BEAN_SUFFIX);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	public BtcdDaemon getBtcdDaemon(String chainCode) {
		try {
			return (BtcdDaemon)context.getBean(chainCode + DAEMON_BEAN_SUFFIX);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	public BtcdClient getLtcdClient(String chainCode) {
		try {
			return (BtcdClient)context.getBean(chainCode + CLIENT_BEAN_SUFFIX);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}

	public BtcdDaemon getLtcdDaemon(String chainCode) {
		try {
			return (BtcdDaemon)context.getBean(chainCode + DAEMON_BEAN_SUFFIX);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}
}