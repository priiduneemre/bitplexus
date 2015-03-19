package com.neemre.bitplexus.backend.data;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, K extends Serializable> {

	K create(T record);
	
	T read(K recordId);
	
	List<T> readAll();
	
	void update(T record);
	
	void delete(K recordId);
}