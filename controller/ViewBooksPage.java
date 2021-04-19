/**
 * <h1>Library Management System</h1>
 * ViewBooksPage Class:
 * This is the controller class for ViewBooksPage.fxml.
 * It shows all the issued books that are currently borrowed.
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-15
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

public class ViewBooksPage {

    @FXML
    private TableView<Transaction> tblBooks;

    public void initialize() {
        //remove all columns from table
        tblBooks.getColumns().removeAll();

        //Column 1
        TableColumn<Transaction, Integer> column1 = new TableColumn<>("Book ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        column1.setMinWidth(100);

        //Column 2
        TableColumn<Transaction, Integer> column2 = new TableColumn<>("User ID");
        column2.setCellValueFactory(new PropertyValueFactory<>("userID"));
        column2.setMinWidth(100);

        //Column 3
        TableColumn<Transaction, LocalDate> column3 = new TableColumn<>("Issue Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        column3.setMinWidth(180);

        //Add columns to tableview
        tblBooks.getColumns().add(column1);
        tblBooks.getColumns().add(column2);
        tblBooks.getColumns().add(column3);

        //Loop through all transactions, add currently issued transactions to table view
        for (Transaction t: Main.library.transactions) {
            if (t.isStatus()) {
                tblBooks.getItems().add(t);
            }
        }

    }

}
