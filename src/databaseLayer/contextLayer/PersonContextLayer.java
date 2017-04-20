package databaseLayer.contextLayer;

import java.util.List;
import java.util.stream.Collectors;

import databaseLayer.dao.IDataManager;
import databaseLayer.factory.DataStoreFactory;
import financialcore.customer.Customer;
import financialcore.customer.Person;
import financialcore.customer.PersonType;
import financialcore.customer.Staff;

public class PersonContextLayer extends IContextLayer<Person>{
	private static PersonContextLayer instance = new PersonContextLayer();
    private IDataManager<Person> dataManager = null;

    private PersonContextLayer(){}

    public static PersonContextLayer Persons(){
        return instance;
    }
    
    
	@Override
	public Person getElement(int id) {
		return getDataManager().getElement(id);
	}

	@Override
	public List<Person> getElements() {
		return getDataManager().getElements();
	}
	
	public List<Customer> getCustomers() {
		return getDataManager().getElements().stream().filter(f->f.getType() == PersonType.Customer)
				.map(m-> (Customer)m).collect(Collectors.toList());
	}
	
	public List<Staff> getStaff() {
		return getDataManager().getElements().stream().filter(f->f.getType() == PersonType.Staff)
				.map(m-> (Staff)m).collect(Collectors.toList());
	}
	
	public List<Person> getElementsbyNames(String lastName, String firstName) {
		return getDataManager().getElements(lastName, firstName);
	}

	public Staff getStaff(String login, String password) {
		return (Staff)getDataManager().getElement(login, password);
	}
	
	@Override
	public IDataManager<Person> getDataManager() {
		 if (dataManager == null) {
	            dataManager = DataStoreFactory.getInstance().createManager(Person.class);
	        }

	        return dataManager;
	}

}
