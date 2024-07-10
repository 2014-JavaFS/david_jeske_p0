package com.revature.crs.User;

public abstract class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public User(int userID, String firstName, String lastName, String emailAddress, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    //#region getters & setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion

    @Override
    public String toString() {
        return ("ID: " + userID + ", Name: " + firstName + " " + lastName + ", Email: " + emailAddress);
    }
}
