package creditcard.ui;


import java.util.List;

import javax.swing.JOptionPane;


import databaseLayer.contextLayer.ContextLayer;
import financialcore.customer.Customer;
import financialcore.customer.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * Created by orifjon9 on 4/19/2017.
 */

public class PersonOverviewController {
	
	@FXML
	private GridPane gpRoot;
	
	@FXML
	private TextField txtLastName;
	
	@FXML
	private TextField txtFirstName;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private TableView tbPerson;
	
	@FXML
	private TableColumn clCustomerId;
	@FXML
	private TableColumn clmFirstName;
	@FXML
	private TableColumn clmLastName;
	@FXML
	private TableColumn clmAge;
	
	
	@FXML
	private TableView tbAccaunt;
	
	@FXML
	private void searchPersons(){
		
		List<Customer> customers = ContextLayer.Model().Persons().getCustomers();
		
		clCustomerId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("personId"));
		clmFirstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		clmAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
		
		ObservableList<Customer> data = FXCollections.observableArrayList();
		for(Customer customer: customers){
			data.add(customer);
		}
		
		tbPerson.setItems(data);
	}
}
