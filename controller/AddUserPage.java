/**
 * <h1>Library Management System</h1>
 * AddUserPage Class:
 * This is the controller class for AddUserPage.fxml.
 * It adds a new user to the library.
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-15
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.time.LocalDate;

public class AddUserPage {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextArea txtAddress;

    @FXML
    private RadioButton radStudent;

    @FXML
    private ToggleGroup userType;

    @FXML
    private RadioButton radFaculty;

    @FXML
    private DatePicker dpkDOB;

    @FXML
    private Button btnRegister;

    @FXML
    private Label lblError;

    //Error message:
    private String error;

    public void initialize() {
        //clear error label, initialize error string
        lblError.setText("");
        error = "";
    }

    /**
     * Triggered when Add User button is pressed.
     * @param (event) (ActionEvent object)
     */
    @FXML
    void addUser(ActionEvent event) {

        error = "";

        String name = txtName.getText().trim();
        String email = txtEmail.getText().toLowerCase().trim();
        String address = txtAddress.getText().trim();
        boolean isStudent = radStudent.isSelected();
        LocalDate dob = dpkDOB.getValue();

        //variables to ensure fields are valid
        boolean nameValid = isNameValid(name);
        boolean emailValid = isEmailValid(email);
        boolean addressValid = true;
        boolean dobValid = true;

        if (dob == null)
            dobValid = false;

        if (address.length() < 9)
            addressValid = false;

        if (nameValid && emailValid && addressValid && dobValid) {
            //Validation successful, create new user, add user to library
            User user = new User(name, email, address, dob, isStudent);
            Main.library.addUser(user);
            //show alert
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Registration Successful");
            a.setContentText("Registered " + user.getName());
            a.initOwner(MainMenuPage.primaryStage);
            a.show();
            //close the window:
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else {
            //invalid field(s). show error message.
            if (!nameValid)
                error += "Name must be at least 2 characters and may only contain letters and spaces.\n";
            if (!emailValid)
                error += "Invalid email address.\n";
            if (!addressValid)
                error += "Address must be at least 8 characters.\n";
            if (!dobValid)
                error += "Please choose a date of birth.\n";
            lblError.setText(error);
        }

    }

    /**
     * Validates Email
     * @param (email) (email address)
     * @return (true if email is valid)
     */
    boolean isEmailValid(String email) {
        if (email.contains("@") && email.contains(".")) {
            //check if @ comes before .
            if (email.indexOf(".") > email.indexOf("@"))
                return true;
        }
        return false;
    }

    /**
     * Validates Name
     * @param (name) (name of user)
     * @return (true if name is valid)
     */
    boolean isNameValid(String name){
        //Return false if name < 3 characters:
        if (name.length() < 3) {
            return false;
        }
        //Check if name contains letters and spaces only
        for(int i=0;i<name.length();i++){
            char ch = name.charAt(i);
            if (Character.isLetter(ch) == false && ch != ' ')
                return false;
        }
        return true;
    }

}
