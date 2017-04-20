package financialcore.customer;

/**
 * Created by ganzo on 4/13/17.
 */
public class Customer extends Person {
	
	public Customer(){
		super(PersonType.Customer);
	}

	public Customer(long pCustomerId, String pFirstName, String pLastName, int pAge, String pUserName, String pPassword) {
		super(pCustomerId, pFirstName, pLastName, pAge, pUserName, pPassword, PersonType.Customer);
	}
	
	public Customer(String pFirstName, String pLastName, int pAge, String pUserName, String pPassword) {
		super(pFirstName, pLastName, pAge, pUserName, pPassword, PersonType.Customer);
	}

/*	@Override
	public void createPerson() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePerson() {
		// TODO Auto-generated method stub

	}*/
}
