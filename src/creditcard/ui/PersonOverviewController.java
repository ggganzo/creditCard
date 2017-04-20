package creditcard.ui;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import creditcard.service.MainWindowService;
import databaseLayer.contextLayer.ContextLayer;
import financialcore.customer.Customer;
import financialcore.customer.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * Created by orifjon9 on 4/19/2017.
 */

public class PersonOverviewController {

	@FXML
	private void initialize(){
		searchPersons();
	}
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
	private void searchPersons() {

		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();

		List<Person> customers = ContextLayer.Model().Persons().getElementsbyNames(lastName, firstName);
				
		clCustomerId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("personId"));
		clmFirstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
		clmLastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		clmAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));

		ObservableList<Person> data = FXCollections.observableArrayList();
		for (Person customer : customers) {
			data.add(customer);
		}

		tbPerson.setItems(data);
	}

	@FXML
	private void updatePerson() {

		Customer selectedCustomer = getSelectedItem();
		if (selectedCustomer == null) {
			JOptionPane.showConfirmDialog(null, "You should select one of customer for edit");
			return;
		}

		MainWindowService.Current().getMainScreenController().openCustomerEdit(selectedCustomer);
	}
	
	@FXML
	private void createPerson() {
		MainWindowService.Current().getMainScreenController().openCustomerCreate();
	}

	private Customer getSelectedItem() {
		try {
			return (Customer) tbPerson.getSelectionModel().getSelectedItem();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
}
