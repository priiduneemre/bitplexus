package com.neemre.bitplexus.backend.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neemre.bitplexus.backend.data.CustomerRepository;
import com.neemre.bitplexus.backend.model.Customer;

public interface JpaCustomerRepository extends CustomerRepository, JpaRepository<Customer, Integer> {

	@Override
	@Query("SELECT c FROM Customer AS c WHERE c.username = :username")
	Customer findByUsername(@Param("username") String username);
}