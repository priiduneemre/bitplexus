package com.neemre.bitplexus.backend.service;

import java.io.Serializable;
import java.util.List;

public interface Service<T, K extends Serializable> {

	T findById(K entityId);
	
	List<T> findAll();
}