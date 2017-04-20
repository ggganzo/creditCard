package financialcore.customer;

import databaseLayer.AbstractElement;

public abstract class Person extends AbstractElement {
	private final PersonType personType;
	private long personId;
	private String firstName;
	private String lastName;
	private int age;

	private String userName;
	private String password;
	
	public Person(PersonType personType){
		this.personType = personType;
	}
	
	public Person(long personId, String pFirstName, String pLastName, int pAge, String pUserName, String pPassword, PersonType personType) {
		this.personId = personId;
		this.firstName = pFirstName;
		this.lastName = pLastName;
		this.age = pAge;
		this.userName = pUserName;
		this.password = pPassword;
		this.personType = personType;
	}
	
	public Person(String pFirstName, String pLastName, int pAge, String pUserName, String pPassword, PersonType personType) {
		this(0, pFirstName, pLastName, pAge, pUserName, pPassword, personType);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*public abstract void createPerson();

	public abstract void updatePerson();
*/
	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	public PersonType getType(){
		return personType;
	}
	

}
