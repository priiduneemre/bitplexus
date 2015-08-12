package com.neemre.bitplexus.backend.crypto;

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

	public BtcdDaemon getBtcdDaemon(String chainCode) {
		try {
			return (BtcdDaemon)context.getBean(chainCode + DAEMON_BEAN_SUFFIX);
		} catch (ClassCastException e) {
			return null;
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
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

	public LtcdDaemon getLtcdDaemon(String chainCode) {
		try {
			return (LtcdDaemon)context.getBean(chainCode + DAEMON_BEAN_SUFFIX);
		} catch (ClassCastException e) {
			return null;
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}
}