package financialcore.customer;

public abstract class Person {

	public Person(String pFirstName, String pLastName, int pAge, String pUserName, String pPassword) {

		this.firstName = pFirstName;
		this.lastName = pLastName;
		this.age = pAge;
		this.userName = pUserName;
		this.password = pPassword;
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

	private String firstName;
	private String lastName;
	private int age;

	private String userName;
	private String password;

	private String personNo;

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

	public abstract void createPerson();

	public abstract void updatePerson();

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

}
