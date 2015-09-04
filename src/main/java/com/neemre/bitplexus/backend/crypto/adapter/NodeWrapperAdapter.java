package com.neemre.bitplexus.backend.crypto.adapter;

import org.springframework.beans.factory.annotation.Autowired;

import com.neemre.bitplexus.backend.crypto.NodeWrapperResolver;

public abstract class NodeWrapperAdapter {

	@Autowired
	protected NodeWrapperResolver wrapperResolver;


	public boolean isOperationalBtcChain(String chainCode) {
		if ((wrapperResolver.getBtcdClient(chainCode) != null) && (wrapperResolver.getBtcdDaemon(
				chainCode) != null)) {
			return true;
		}
		return false;
	}

	public boolean isOperationalLtcChain(String chainCode) {
		if ((wrapperResolver.getLtcdClient(chainCode) != null) && (wrapperResolver.getLtcdDaemon(
				chainCode) != null)) {
			return true;
		}
		return false;
	}
}