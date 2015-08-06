package com.neemre.bitplexus.backend.data;

import com.neemre.bitplexus.backend.model.Customer;

public interface CustomerRepository {

	Customer findByUsername(String username);
}