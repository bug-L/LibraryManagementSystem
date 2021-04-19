/**
 * <h1>Library Management System</h1>
 * ReturnBookPage Class:
 * This is the controller class for ReturnBookPage.fxml.
 * It accepts borrowed books back to the library.
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
import javafx.scene.paint.Color;
import model.Book;
import model.Transaction;
import model.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReturnBookPage {

    @FXML
    private TextField txtBook;

    @FXML
    private ListView<Book> lstBooks;

    @FXML
    private TextField txtBookId;

    @FXML
    private Button btnReturn;

    @FXML
    private Label lblMessage;

    private String message;

    //contains all transactions
    private ArrayList<Transaction> transactions;
    //contains all books
    private ArrayList<Book> books;
    private ObservableList<Book> obsBookList;

    public void initialize() {

        //clear label
        lblMessage.setText("");
        //get all transactions
        transactions = Main.library.transactions;
        //populate list view
        showBooks();

        //event handlers for item clicks on listview:
        lstBooks.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Book book = lstBooks.getSelectionModel().getSelectedItem();
                if (book != null) {
                    txtBookId.setText(String.valueOf(book.getID()));
                }
            }
        });
    }

    /**
     * Triggered when return book button is pressed
     * @param (event) (ActionEvent object)
     */
    @FXML
    void returnBook(ActionEvent event) {

        //check if no input
        if (txtBookId.getText().trim().equals("")) {
            message = "Please enter a book ID";
            lblMessage.setText(message);
            lblMessage.setTextFill(Color.RED);
        } else {
            //Get book ID, book from library
            int bookId = Integer.parseInt(txtBookId.getText());
            Book book = Main.library.getBook(bookId);
            if (book == null) {
                message = "Invalid book ID";
                lblMessage.setText(message);
                lblMessage.setTextFill(Color.RED);
            } else {
                //return book
                //book user variable
                User user = null;
                //user's balance before returning book
                double priorBalance = 0;
                for (Transaction t: transactions) {
                    if (t.getBookID() == bookId && t.isStatus()) {
                        //Calculate late return
                        //Get the user
                        user = Main.library.getUser(t.getUserID());
                        //Get user's balance before book return
                        priorBalance = user.getBalance();
                        break;
                    }
                }

                boolean returnBook = Main.library.returnBook(bookId);
                if (returnBook) {
                    //get user's balanace after returning book
                    double newBalance = user.getBalance();
                    //get the difference of balances (i.e. the days late)
                    double daysLate = newBalance - priorBalance;

                    if (daysLate > 0) {
                        //late submission
                        message = "You returned " + book.getName() + " " + daysLate + " days late!\n";
                    } else {
                        //timely submission
                        message = "You returned " + book.getName();
                    }
                    if (newBalance > 0) {
                        //Show outstanding balance
                        message += "Your outstanding balance is $" + user.getBalance();
                    }
                    lblMessage.setText(message);
                    lblMessage.setTextFill(Color.GREEN);

                } else {
                    message = "Book is currently not borrwed.";
                    lblMessage.setText(message);
                    lblMessage.setTextFill(Color.RED);
                }

                showBooks();
            }
        }
    }

    /**
     * populates listView
     */
    void showBooks() {

        books = Main.library.books;
        //put books in observable array list for list view
        obsBookList = FXCollections.observableArrayList(books);
        //populate listview
        lstBooks.setItems(obsBookList);
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
}
