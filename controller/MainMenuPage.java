/**
 * <h1>Library Management System</h1>
 * MainMenuPage Class:
 * This is the controller class for MainMenuPage.fxml.
 * It shows the main menu of the library.
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-15
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Library;

import java.io.IOException;

public class MainMenuPage {

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnViewUsers;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewBooks;

    public static Stage primaryStage;

    void initialize() {

    }

    /**
     * This method accepts a bookID, marks it as returned and computes any
     * necessary late fines
     * @param (event) (ActionEvent Object)
     * @param (viewName) (name of fxml file)
     * @param (stageTitle) (title of new window)
     */
    //This method accepts shows the appropriate view based on viewName
    private void showStage(ActionEvent event, String viewName, String stageTitle) throws IOException {
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(viewName));
        newStage.initOwner(primaryStage);
        newStage.initModality(Modality.WINDOW_MODAL);   //This disables the parent stage (MainMenuPage)
        newStage.setTitle(stageTitle);
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    void addBook(ActionEvent event) throws IOException {
        showStage(event, "AddBookPage.fxml", "Add New Book");
    }

    @FXML
    void addUser(ActionEvent event) throws IOException {
        showStage(event, "AddUserPage.fxml", "Add New User");
    }

    @FXML
    void issueBook(ActionEvent event) throws IOException {
        showStage(event, "IssueBookPage.fxml", "Issue a Book");
    }

    @FXML
    void returnBook(ActionEvent event) throws IOException {
        showStage(event, "ReturnBookPage.fxml", "Return a Book");
    }

    @FXML
    void viewBooks(ActionEvent event) throws IOException {
        showStage(event, "ViewBooksPage.fxml", "View Issued Books");
    }

    @FXML
    void viewUsers(ActionEvent event) throws IOException {
        showStage(event, "UserDetailsPage.fxml", "User Details");
    }

}
