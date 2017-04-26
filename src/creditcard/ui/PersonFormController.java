package creditcard.ui;

import creditcard.service.MainWindowService;
import databaseLayer.ElementState;
import databaseLayer.contextLayer.ContextLayer;
import financialcore.customer.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PersonFormController {
	private Customer customer = new Customer();;
	@FXML
	private Label lbTitle;
	@FXML
	private TextField txtCustomerId;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtAge;
		
	@FXML
	private void saveCustomer(){
		
		customer.setFirstName(txtFirstName.getText());
		customer.setLastName(txtLastName.getText());
		customer.setAge(Integer.parseInt(txtAge.getText()));
		customer.setPassword("");
		customer.setUserName("");
		
		customer.setElementState(customer.getPersonId() > 0 ? ElementState.Updated : ElementState.Inserted);
		
		ContextLayer.Model().Persons().save(customer);
		MainWindowService.Current().getMainScreenController().openCustomerView();
	}

	public void setCustomer(Customer customer){
		this.customer = customer;
		lbTitle.setText("Edit customer form");
		loadData();
	}
	
	private void loadData(){
		txtCustomerId.setText(""+this.customer.getPersonId());
		txtLastName.setText(this.customer.getLastName());
		txtFirstName.setText(this.customer.getFirstName());
		txtAge.setText(""+this.customer.getAge());
	}
}
