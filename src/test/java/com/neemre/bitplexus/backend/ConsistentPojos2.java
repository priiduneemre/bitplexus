package com.neemre.bitplexus.backend;

import com.neemre.bitplexus.backend.model.Customer;
import com.neemre.bitplexus.backend.model.Wallet;
import com.neemre.bitplexus.backend.model.reference.WalletStateType;

public class ConsistentPojos2 {

	public static void main(String[] args) {
		Customer customer1 = new Customer();
		
		Wallet wallet1 = new Wallet();
		wallet1.setName("Aadu rahakott");
		WalletStateType walletStateType1 = new WalletStateType();
		walletStateType1.setWalletStateTypeId(new Short("2"));
		wallet1.setWalletStateType(walletStateType1);
		wallet1.setCustomer(customer1);
		
		Wallet wallet2 = new Wallet();
		wallet2.setName("Peedu rahakott");
		WalletStateType walletStateType2 = new WalletStateType();
		walletStateType2.setWalletStateTypeId(new Short("2"));
		wallet2.setWalletStateType(walletStateType2);
		wallet2.setCustomer(customer1);
		
		Wallet wallet3 = new Wallet();
		wallet3.setName(null);
		WalletStateType walletStateType3 = new WalletStateType();
		walletStateType3.setWalletStateTypeId(new Short("2"));
		wallet3.setWalletStateType(walletStateType3);
		wallet3.setCustomer(customer1);
		
		Wallet wallet4 = new Wallet();
		wallet4.setName(null);
		WalletStateType walletStateType4 = new WalletStateType();
		walletStateType4.setWalletStateTypeId(null);
		wallet4.setWalletStateType(walletStateType4);
		wallet4.setCustomer(customer1);
		
		Wallet wallet5 = new Wallet();
		wallet5.setName("Seedu rahakott");
		wallet5.setCustomer(customer1);
		
		Wallet wallet6 = new Wallet();
		wallet6.setCustomer(customer1);
		
		Wallet wallet7 = new Wallet();
		wallet7.setName("Maili rahakott");
		WalletStateType walletStateType7 = new WalletStateType();
		walletStateType7.setWalletStateTypeId(new Short("3"));
		wallet7.setWalletStateType(walletStateType7);
		wallet7.setCustomer(customer1);
		
		Wallet wallet8 = new Wallet();
		wallet8.setName("Liia rahakott");
		WalletStateType walletStateType8 = new WalletStateType();
		walletStateType8.setWalletStateTypeId(new Short("1"));
		wallet8.setWalletStateType(walletStateType8);
		wallet8.setCustomer(customer1);
		
		System.out.printf("customer1: \"%s\";\n", customer1);
		System.out.printf("wallets (customer1): \"%s\";\n", customer1.getWallets());
	}
}
