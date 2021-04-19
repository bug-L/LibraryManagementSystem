/**
 * <h1>Library Management System</h1>
 * Transaction Class:
 * This class creates Transaction objects that
 * are used to keep track of books borrowed from
 * the library along with its corresponding user
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-08
 */

package model;

import java.time.LocalDate;

public class Transaction {

    //Attributes:
    private int bookID;
    private int userID;
    private LocalDate issueDate;
    private boolean status;

    //Constructor
    Transaction(int bookID, int userID) {

        this.bookID = bookID;
        this.userID = userID;
        issueDate = LocalDate.now();
        status = true;

    }

    //Getters:
    public int getBookID() {
        return bookID;
    }

    public int getUserID() {
        return userID;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public boolean isStatus() {
        return status;
    }

    //Setters:
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "bookID=" + bookID +
                ", userID=" + userID +
                ", issueDate=" + issueDate +
                ", status=" + status +
                '}';
    }
}
