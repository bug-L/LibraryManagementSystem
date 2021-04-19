/**
 * <h1>Library Management System</h1>
 * AddBookPage Class:
 * This is the controller class for AddBookPage.fxml.
 * It adds a new book to the library.
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
import model.Book;

public class AddBookPage {

    @FXML
    private TextField txtName;

    @FXML
    private ListView<String> lstPublisher;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtISBN;

    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private TextField txtYear;

    @FXML
    private Button btnRegister;

    @FXML
    private Label lblError;

    private String error;

    /**
     * Called when Add Book button is pressed
     * boook to the user
     * @param (event) (ActionEvent object)
     */
    @FXML
    void addBook(ActionEvent event) {

        //reset error
        error = "";

        //all fields
        String name = txtName.getText().trim();
        String publisher = lstPublisher.getSelectionModel().getSelectedItem();
        String author = txtAuthor.getText().trim();
        String isbn = txtISBN.getText().trim();
        String genre = cmbGenre.getSelectionModel().getSelectedItem();
        Long year = Long.parseLong(txtYear.getText().trim());

        //use boolean variables to validate inputs:
        boolean nameValid = true;
        if (name.length() < 1) {
            nameValid = false;
            error += "Please enter a name\n";
        }

        boolean authorValid = isAuthorValid(author);

        boolean isbnValid = true;
        if (isbn.length() < 1) {
            error += "Please enter an ISBN\n";
            isbnValid = false;
        }

        boolean yearValid = true;
        if (year == null) {
            error += "Please enter a year\n";
            yearValid = false;
        }


        if (nameValid && authorValid && yearValid && isbnValid) {

            //Create new book and add to main's library, close the window
            Book book = new Book(name, author, publisher, genre, isbn, year);
            Main.library.addBook(book);
            //show alert
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Registration Successful");
            a.setContentText("Registered " + book.getName() + " by " + book.getAuthor());
            a.initOwner(MainMenuPage.primaryStage);
            a.show();
            //close the window:
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else {
            lblError.setText(error);
        }


    }

    /**
     * Checks if a valid author name is provided
     * @param (author) (author name)
     * @return (true if name valid)
     */
    boolean isAuthorValid(String author){
        //Return false if author's < 3 characters:
        if (author.length() < 3) {
            error += "Author author must be at least 3 characters\n";
            return false;
        }
        //Check if author contains letters and spaces only
        for(int i=0;i<author.length();i++){
            char ch = author.charAt(i);
            if (Character.isLetter(ch) == false && ch != ' ') {
                error += "Author can contain letters and spaces only\n";
                return false;
            }
        }
        return true;
    }

    public void initialize() {
        error = "";
        lblError.setText(error);
        //Populate publisher list view:
        lstPublisher.getItems().addAll("Allen and Unwin", "Disney", "Pearson", "Penguin", "Simon & Schuster");
        lstPublisher.getSelectionModel().selectFirst();
        cmbGenre.getItems().addAll("Education", "Adventure", "Thriller", "History");
        cmbGenre.getSelectionModel().selectFirst();
    }

}
