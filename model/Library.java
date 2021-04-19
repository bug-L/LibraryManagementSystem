/**
 * <h1>Library Management System</h1>
 * Library Class:
 * This class creates Library objects. Each library
 * object contains a list of transactions, users and books.
 * In addition, it contains methods that are used to manipulate
 * the data stored within.
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-08
 */

package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Library {

    //Attributes:
    public final int MAX_BOOK_LIMIT = 3;
    private final int MAX_LOAN_DAYS = 14;
    public ArrayList<Transaction> transactions;
    public ArrayList<User> users;
    public ArrayList<Book> books;
    public ArrayList<String> msgLog;

    //No-arg constructor:
    public Library() {
        transactions = new ArrayList<Transaction>();
        users = new ArrayList<User>();
        books = new ArrayList<Book>();
    }

    //Add a new book to arraylist
    public void addBook(Book book) {
        books.add(book);
    }

    //Add a new user to arraylist
    public void addUser(User user) {
        users.add(user);
    }

    //Add a new transaction to arraylist
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Checks if a book is available to be borrowed, if the user has not
     * reached their max borrow count and if the user doesn't have any
     * outstanding balance, then creates a new transaction, issuing the
     * boook to the user
     * @param (userID) (ID of user)
     * @param (bookID) (ID of book)
     * @return (if book is issued, returns true, else returns false)
     */
    public boolean issueBook(int userID, int bookID) {

        User user = null;
        for (User u: users) {
            if (u.getID() == userID)
                user = u;
        }

        //Make sure userID matches a user in our arrayList
        if (user != null){
            if (isAvailable(bookID) && getBorrowCount(userID) < MAX_BOOK_LIMIT && user.getBalance() == 0) {
                Transaction t = new Transaction(bookID, userID);
                addTransaction(t);
                return true;
            }
        }
        return false;

    }

    /**
     * This method accepts a bookID, marks it as returned and computes any
     * necessary late fines
     * @param (bookID) (ID of book)
     * @return (if book is returned, returns true, else returns false)
     */
    public boolean returnBook(int bookID) {
        for (Transaction t: transactions) {
            if (t.getBookID() == bookID && t.isStatus()) {

                double fine = computeFine(t);

                if (fine > 0) {
                    //find the user
                    for (User u: users) {
                        if (u.getID() == t.getUserID()) {
                            //add the fine to user's balance
                            u.setBalance(u.getBalance() + fine);
                        }
                    }
                }
                t.setStatus(false);
                return true;
            }
        }
        return false;
    }

    /**
     * This method accepts a a string, returns list of book objects that
     * match in title
     * @param (search) (search term)
     * @return (ArrayList of books matching the search term)
     */
    public ArrayList<Book> searchBook(String search) {
        ArrayList<Book> searchResults = new ArrayList<Book>();

        //iterate through books, add matching titles to searchResults
        for (Book b: books) {
            if (b.getName().contains(search))
                searchResults.add(b);
        }

        return searchResults;
    }

    /**
     * This method returns book object based on ID
     * @param (bookID) (ID of book)
     * @return (Book object if book exists, else null)
     */
    public Book getBook(int bookID) {
        for (Book b: books) {
            if (b.getID() == bookID)
                return b;
        }
        return null;
    }

    /**
     * This method returns book object based on ID
     * @param (userID) (ID of book)
     * @return (User object if user exists, else null)
     */
    public User getUser(int userID) {
        for (User u: users) {
            if (u.getID() == userID)
                return u;
        }
        return null;
    }

    //Sets user's balance to 0
    /**
     * This method resets a user's balance to 0
     * @param (user) (User object)
     */
    public void collectFine(User user) {
        for (User u: users) {
            if (u.getID() == user.getID()) {
                u.setBalance(0);
            }
        }
    }

    /**
     * This method computes the fine based on the number of days late
     * @param (transaction) (Transaction object)
     * @return (dollar amount of fine)
     */
    private double computeFine(Transaction transaction) {
        LocalDate todayDate = LocalDate.now();
        LocalDate dueDate = getDueDate(transaction.getIssueDate());

        double daysLate = 0;
        if (todayDate.isAfter(dueDate)) {
            daysLate = ChronoUnit.DAYS.between(dueDate, todayDate);
        }

        return daysLate;
    }

    /**
     * This method checks if book is already borrowed or not based on its
     * transaction history
     * @param (bookID) (ID of book)
     * @return (true if book is available to be borrwed, false otherwise)
     */
    public boolean isAvailable(int bookID) {
        for (Transaction t: transactions) {
            if (t.getBookID() == bookID && t.isStatus())
                return false;
        }
        return true;
    }

    /**
     * This method counts the number of books borrowed by a user based on userID
     * @param (userID) (ID of user)
     * @return (an integer value of the number of books borrowed)
     */
    public int getBorrowCount(int userID) {
        int count = 0;
        for (Transaction t: transactions) {
            if (t.getUserID() == userID && t.isStatus())
                count++;
        }
        return count;
    }

    /**
     * This method calculates the due date of a borrwed book
     * @param (issueDate) (when the book was issued)
     * @return (the due date for)
     */
    public LocalDate getDueDate(LocalDate issueDate) {
        return issueDate.plusDays(MAX_LOAN_DAYS);
    }

}
