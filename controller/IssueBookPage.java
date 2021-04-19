/**
 * <h1>Library Management System</h1>
 * IssueBookPage Class:
 * This is the controller class for IssueBookPage.fxml.
 * It adds a book to a user.
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-15
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Book;
import model.Transaction;
import model.User;
import java.util.ArrayList;

public class IssueBookPage {

    @FXML
    private TextField txtBook;

    @FXML
    private TextField txtUser;

    @FXML
    private ListView<Book> lstBook;

    @FXML
    private ListView<User> lstUser;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtUserID;

    @FXML
    private Button btnIssueBook;

    @FXML
    private Label lblError;

    @FXML
    private Label lblSuccess;

    //contains all users
    private ArrayList<User> users = Main.library.users;;
    private ObservableList<User> obsUserList = FXCollections.observableArrayList(users);;

    //contains all books
    private ArrayList<Book> books = Main.library.books;
    private ObservableList<Book> obsBookList = FXCollections.observableArrayList(books);

    public void initialize() {

        lblError.setText("");
        lblSuccess.setText("");
        //populate listviews
        lstBook.setItems(obsBookList);
        lstUser.setItems(obsUserList);

        //event handlers for item clicks on each listview:
        lstBook.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Book book = lstBook.getSelectionModel().getSelectedItem();
                if (book != null) {
                    txtBookID.setText(String.valueOf(book.getID()));
                }
            }
        });

        lstUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                User user = lstUser.getSelectionModel().getSelectedItem();
                if (user != null) {
                    txtUserID.setText(String.valueOf(user.getID()));
                }
            }
        });
    }

    /**
     * Dynamically updates lstBooks based on search term:
     * @param (event) (KeyEvent object)
     */
    @FXML
    void bookTextChanged(KeyEvent event) {
        obsBookList.clear();    //clear observable list
        String query = txtBook.getText().toLowerCase().trim();
        //loop through books to find matches
        for (Book book: books) {
            if (book.getName().toLowerCase().contains(query))
                obsBookList.add(book); //add to observable list
        }
    }

    /**
     * Dynamically updates lstUsers based on search term:
     * @param (event) (KeyEvent object)
     */
    @FXML
    void userTextChanged(KeyEvent event) {
        obsUserList.clear();    //clear observable list
        String query = txtUser.getText().toLowerCase().trim();
        //loop through users to find matches
        for (User user: users) {
            if (user.getName().toLowerCase().contains(query))
                obsUserList.add(user); //add to observable list
        }

    }

    /**
     * Triggered when Issue Book button is pressed
     * @param (event) (ActionEvent object)

     */
    @FXML
    void issueBook(ActionEvent event) {

        String error = "";
        lblSuccess.setText("");
        lblError.setText("");
        int bookId;
        int userId;

        //check if both id's are provided
        if (txtBookID.getText().trim() == "" || txtUserID.getText().trim() == "") {
            error += "Please enter Book ID and User ID\n";
            lblError.setText(error);
        } else {
            bookId = Integer.parseInt(txtBookID.getText());
            userId = Integer.parseInt(txtUserID.getText());

            //retrieve book and user from library
            User user = Main.library.getUser(userId);
            Book book = Main.library.getBook(bookId);

            if (user != null && book != null) {
                //Check if user exceeded borrow limit
                int userBookCout = Main.library.getBorrowCount(userId);
                if (userBookCout == Main.library.MAX_BOOK_LIMIT) {
                    error = "User has reached the maximum number of borrowed books.";
                    lblError.setText(error);
                } else {

                    //check if user has outstanding balance:
                    if (user.getBalance() != 0) {
                        error += user.getName() + " has an outstanding balance of $" + user.getBalance() + ".\n" +
                                    "Cannot issue book.";
                        lblError.setText(error);
                    } else {
                        //issue book
                        boolean issueBook = Main.library.issueBook(userId, bookId);
                        if (issueBook) {
                            //get the transaction
                            Transaction transaction = null;
                            for (Transaction t: Main.library.transactions) {
                                if (userId == t.getUserID() && bookId == t.getBookID() && t.isStatus()) {
                                    transaction = t;
                                }
                            }

                            lblSuccess.setText(book.getName() + " has been issued to " + user.getName() + "\n" +
                                    "The due date is " + Main.library.getDueDate(transaction.getIssueDate()));

                        } else {
                            error = "The book is unavailable";
                            lblError.setText(error);
                        }
                    }
                }
            } else {
                if (book == null)
                    error += "Invalid Book ID\n";
                if (user == null)
                    error += "Invalid User ID\n";
                lblError.setText(error);
            }
        }

    }

}
