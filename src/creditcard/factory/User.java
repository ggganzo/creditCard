package creditcard.factory;

import financialcore.customer.Staff;

public class User {
	private static User currentUser = new User();
	private Staff staff;
	
	private User() {}
	
	public static User GetCurrent(){
		return currentUser;
	}
	
	public void setStaff(Staff staff){
		this.staff = staff;
	}
	
	public Staff getStaff(){
		return staff;
	}
	
	public boolean isAuthorized(){
		if(staff == null) return false;
		if(staff.getPersonId() == 0) return false;
		
		return true;
	}
	
	public String GetUserFullName(){
		if(this.isAuthorized()){
			return this.staff.getFirstName() + " " + this.staff.getLastName();
		}
		return "";
	}

}
