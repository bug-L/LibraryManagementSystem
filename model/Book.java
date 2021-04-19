/**
 * <h1>Library Management System</h1>
 * Book Class:
 * This class creates Book objects, and contains
 * getter and setter methods to access and modify
 * book attributes.
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-08
 */

package model;

public class Book {

    //Attributes:
    private int ID;
    private String name;
    private String author;
    private String publisher;
    private String genre;
    private String ISBN;
    private long year;
    private static int booksAdded = 0;

    //Constructor:
    public Book(String name, String author, String publisher, String genre, String ISBN, long year) {

        this.ID = booksAdded;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.ISBN = ISBN;
        this.year = year;

        booksAdded++;
    }

    //Returns true if the object passed is of Book
    //and their ID, name and author matches
    public boolean equals(Object o) {

        if (o instanceof Book) {
            Book b = (Book) o;
            if (ID == b.ID && name.equals(b.name) && author.equals(b.author))
                return true;
        }
        return false;

    }

    //Getter methods:
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public long getYear() {
        return year;
    }

    //Setter methods:
    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setYear(long year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ID: " + ID + ", Name: " + name;
    }

}
