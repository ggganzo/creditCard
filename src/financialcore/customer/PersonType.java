package financialcore.customer;

public enum PersonType {
	Customer(0),
	Staff(1);
	
	PersonType(){}
	PersonType(int typeId){
		this.typeId = typeId;
	}
	
	private int typeId;
	
	public int getTypeId(){
		return typeId;
	}
}
