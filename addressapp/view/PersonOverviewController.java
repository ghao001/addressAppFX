/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressapp.view;

import addressapp.MainApp;
import addressapp.helper.DateFormat;
import addressapp.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Howard
 */
public class PersonOverviewController {
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    
    private MainApp mainApp;

    @FXML
    private void initialize() {
        //Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        //clear person details
        showPersonDetails(null);
        
        //Listen for selection changes and show details
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    
    /**
     * Method called by main class
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getList());
    }
    
    /**
     * Fill out left part of the view, show details of a selected person
     */
    public void showPersonDetails(Person person) {
        if(person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateFormat.toString(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText(""); 
        }
    }
    
    /**
     * Action of clicking button
     */
    @FXML
    private void handleDelete(){
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
        personTable.getItems().remove(selectedIndex);
        }
        else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person.");
            alert.showAndWait();
        }
    }
    /**
     * Action of Edit button
     */
    @FXML
    private void handleEdit(){
        Person person = personTable.getSelectionModel().getSelectedItem();
        if(person != null){
            boolean okClicked = mainApp.showEditDialog(person);
            if(okClicked){
                showPersonDetails(person);
            }
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person.");
            alert.showAndWait();
        }
    }
    /**
     * Action of new
     */
    @FXML
    private void handleNew(){
        Person newPerson = new Person();
        
        Boolean isClicked = mainApp.showEditDialog(newPerson);
        if(isClicked){
            mainApp.getList().add(newPerson);
        }
    }
}
