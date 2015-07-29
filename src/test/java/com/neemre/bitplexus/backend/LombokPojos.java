package com.neemre.bitplexus.backend;

import com.neemre.bitplexus.backend.model.Address;
import com.neemre.bitplexus.backend.model.AddressBookEntry;
import com.neemre.bitplexus.backend.model.AddressType;
import com.neemre.bitplexus.backend.model.Chain;
import com.neemre.bitplexus.backend.model.Currency;
import com.neemre.bitplexus.backend.model.Customer;
import com.neemre.bitplexus.backend.model.Employee;
import com.neemre.bitplexus.backend.model.EmployeeRole;
import com.neemre.bitplexus.backend.model.Member;
import com.neemre.bitplexus.backend.model.PaymentRequest;
import com.neemre.bitplexus.backend.model.Person;
import com.neemre.bitplexus.backend.model.Transaction;
import com.neemre.bitplexus.backend.model.TransactionEndpoint;
import com.neemre.bitplexus.backend.model.Visit;
import com.neemre.bitplexus.backend.model.Wallet;
import com.neemre.bitplexus.backend.model.reference.AddressStateType;
import com.neemre.bitplexus.backend.model.reference.Role;
import com.neemre.bitplexus.backend.model.reference.TransactionEndpointType;
import com.neemre.bitplexus.backend.model.reference.TransactionStatusType;
import com.neemre.bitplexus.backend.model.reference.WalletStateType;

public class LombokPojos {
	
	public static void main(String[] args) {
		System.out.printf("1. Address: '%s';\n", new Address());
		System.out.printf("2. AddressBookEntry: '%s';\n", new AddressBookEntry());
		System.out.printf("3. AddressType: '%s';\n", new AddressType());
		System.out.printf("4. Chain: '%s';\n", new Chain());
		System.out.printf("5. Currency: '%s';\n", new Currency());
		System.out.printf("6. Customer: '%s';\n", new Customer());
		System.out.printf("7. Employee: '%s';\n", new Employee());
		System.out.printf("8. EmployeeRole: '%s';\n", new EmployeeRole());
		System.out.printf("9. Member: '%s';\n", new Member());
		System.out.printf("10. PaymentRequest: '%s';\n", new PaymentRequest());
		System.out.printf("11. Person: '%s';\n", new Person());
		System.out.printf("12. Transaction: '%s';\n", new Transaction());
		System.out.printf("13. TransactionEndpoint: '%s';\n", new TransactionEndpoint());
		System.out.printf("14. Visit: '%s';\n", new Visit());
		System.out.printf("15. Wallet: '%s';\n", new Wallet());
		
		System.out.printf("16. AddressStateType: '%s';\n", new AddressStateType());
		System.out.printf("17. Role: '%s';\n", new Role());
		System.out.printf("18. TransactionEndpointType: '%s';\n", new TransactionEndpointType());
		System.out.printf("19. TransactionStatusType: '%s';\n", new TransactionStatusType());
		System.out.printf("20. WalletStateType: '%s';\n", new WalletStateType());
	}
}