package com.payment_system.repository;

import org.springframework.stereotype.Repository;

@Repository
public class TransactRepository {
	
	public String findTransactionById(Long id) {
		return "Transaction id is: " + id + " found.";
	}

}
