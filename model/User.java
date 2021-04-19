
/**
 * <h1>Library Management System</h1>
 * User Class:
 * This class creates User objects, and contains
 * getter and setter methods to access and modify
 * user attributes.
 *
 * @author  Tajbid Hasib
 * @version 1.0
 * @since   2021-04-08
 */

package model;

import java.time.LocalDate;

public class User {

    //Attributes:
    private int ID;
    private String name;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private boolean isStudent;
    private double balance;
    private static int usersAdded = 0;

    //Constructor:
    public User(String name, String email, String address, LocalDate dateOfBirth, boolean isStudent) {

        this.ID = usersAdded;
        this.name = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.isStudent = isStudent;
        this.balance = 0;
        //increment usersAdded:
        usersAdded++;
    }

    //Getter methods:
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() { return address; }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public double getBalance() {
        return balance;
    }

    public boolean getStudent() {
        return isStudent;
    }

    //Setter methods:
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    @Override
    public String toString() {
        return "ID: " + ID + ", Name: " + name;
    }

}
