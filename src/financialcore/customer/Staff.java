package financialcore.customer;

public class Staff extends Person {
	
	public Staff(){
		super(PersonType.Staff);
	}
	
	public Staff(long pStaffId, String pFirstName, String pLastName, int pAge, String pUserName, String pPassword) {
		super(pStaffId, pFirstName, pLastName, pAge, pUserName, pPassword, PersonType.Staff);
	}
	
	public Staff(String pFirstName, String pLastName, int pAge, String pUserName, String pPassword) {
		super(pFirstName, pLastName, pAge, pUserName, pPassword, PersonType.Staff);
		
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
