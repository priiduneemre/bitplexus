package com.neemre.bitplexus.backend;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.Chain;
import com.neemre.bitplexus.backend.model.Currency;
import com.neemre.bitplexus.backend.model.Customer;
import com.neemre.bitplexus.backend.model.Employee;
import com.neemre.bitplexus.backend.model.Member;
import com.neemre.bitplexus.backend.model.Transaction;
import com.neemre.bitplexus.backend.model.Visit;

public class JpaPojos {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("bitplexus");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
//		Member member = new Member();
//		member.setMemberId(1);
//		Visit visit = new Visit();
//		visit.setMember(member);
//		visit.setIpAddress("127.0.0.1");
//		em.persist(visit);
//		Employee employee = em.find(Employee.class, 1);
//		Customer customer = em.find(Customer.class, 1);
//		System.out.printf("Instance of 'employee' (id = '%s') was: '%s';\n", 1, employee);
//		System.out.printf("Instance of 'customer' (id = '%s') was: '%s';\n", 1, customer);
		
//		Employee employee = em.find(Employee.class, 1);
//		System.out.println(employee);
		
//		Customer customer = em.find(Customer.class, 5);
//		System.out.println(customer);
//		System.out.println(customer.getWallets());
		
//		Currency currency = em.find(Currency.class, new Short("1"));
//		System.out.println(currency);
//		System.out.println(currency.getChains());
		
//		Chain chain = em.find(Chain.class, new Short("4"));
//		System.out.println(chain);
//		System.out.println(chain.getAddressTypes());
		
		Transaction transaction = em.find(Transaction.class, 2L);
		System.out.println(transaction);
		System.out.println(transaction.getTransactionEndpoints());
		
//		Address address = em.find(Address.class, 14L);
//		System.out.println(address);
//		System.out.println(address.getPaymentRequests());
		
		em.getTransaction().commit();

		em.close();
		emf.close();		
	}
}