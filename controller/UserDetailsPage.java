/**
 * <h1>Library Management System</h1>
 * UserDetailsPage Class:
 * This is the controller class for UserDetailsPage.fxml.
 * It shows details of a particular user.
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-15
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import model.Book;
import model.Transaction;
import model.User;
import java.util.ArrayList;

public class UserDetailsPage {

    @FXML
    private ComboBox<User> cmbUsers;

    @FXML
    private ListView<Book> lstBooks;

    @FXML
    private Label lblName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblBirthday;

    @FXML
    private Label lblType;

    @FXML
    private Label lblBalance;

    @FXML
    private Button btnCollectFine;

    //contains all users
    private ArrayList<User> users = Main.library.users;;
    private ObservableList<User> obsUserList = FXCollections.observableArrayList(users);
    //Holds currently selected user:
    private User user;

    public void initialize() {
        resetLabels();
        //populate user combo box
        cmbUsers.setItems(obsUserList);
        //Collect fine button should be invisible
        btnCollectFine.setVisible(false);
    }

    /**
     * Triggered when a new user is selected from combobox
     * @param (event) (ActionEvent object)
     */
    @FXML
    void viewUser(ActionEvent event) {

        user = cmbUsers.getSelectionModel().getSelectedItem();

        //assign user info to labels
        lblName.setText(user.getName());
        lblEmail.setText(user.getEmail());
        lblBirthday.setText(user.getDateOfBirth().toString());

        //properly colorize balance, set collect fine button visibility:
        if (user.getBalance() == 0) {
            //Green if balance 0, else red
            lblBalance.setText("$0.0");
            lblBalance.setTextFill(Color.GREEN);
            btnCollectFine.setVisible(false);
        } else {
            lblBalance.setText("$"+user.getBalance());
            lblBalance.setTextFill(Color.RED);
            btnCollectFine.setVisible(true);
        }

        lblAddress.setText(user.getAddress());

        if (user.getStudent())
            lblType.setText("Student");
        else
            lblType.setText("Faculty");

        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Transaction> transactions = Main.library.transactions;

        for (Transaction t: transactions) {
            if (t.getUserID() == user.getID() && t.isStatus()) {
                Book book = Main.library.getBook(t.getBookID());
                books.add(book);
            }
        }

        //Observable list of books for listview
        ObservableList<Book> obsBookList = FXCollections.observableArrayList(books);
        lstBooks.setItems(obsBookList);

    }

    /**
     * Triggered when Collect Fine button is pressed
     * @param (event) (ActionEvent object)
     */
    @FXML
    void collectFine(ActionEvent event) {
        Main.library.collectFine(user);
        lblBalance.setText("$0.0");
        lblBalance.setTextFill(Color.GREEN);
    }

    /**
     * Clears all user info labels
     */
    private void resetLabels() {
        //reset all detail labels
        lblAddress.setText("");
        lblBalance.setText("");
        lblBirthday.setText("");
        lblType.setText("");
        lblEmail.setText("");
        lblName.setText("");
    }

}
