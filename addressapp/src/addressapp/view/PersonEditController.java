/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressapp.view;

import addressapp.helper.DateFormat;
import addressapp.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Howard
 */
public class PersonEditController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField street;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField city;
    @FXML
    private TextField birthday;
    
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
    
    public void setStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    /**
     * put previous person details on text field 
     * @param person
     */
    public void setPerson(Person person){
        this.person = person;
        
        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        street.setText(person.getStreet());
        postalCode.setText(Integer.toString(person.getPostalCode()));
        city.setText(person.getCity());
        birthday.setText(DateFormat.toString(person.getBirthday()));
        birthday.setPromptText("mm.dd.yyyy");
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstName.getText() == null || firstName.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastName.getText() == null || lastName.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (street.getText() == null || street.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (postalCode.getText() == null || postalCode.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCode.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (city.getText() == null || city.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (birthday.getText() == null || birthday.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateFormat.isDate(birthday.getText())) {
                errorMessage += "No valid birthday. Use the format MM.dd.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    @FXML
    private void handleOK(){
        if(isInputValid()){
            person.setFirstName(firstName.getText());
            person.setLastName(lastName.getText());
            person.setCity(city.getText());
            person.setBirthday(DateFormat.toDate(birthday.getText()));
            person.setPostalCode(Integer.parseInt(postalCode.getText()));
            person.setStreet(street.getText());
            
            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML void handleCancel(){
        dialogStage.close();
    }
}
