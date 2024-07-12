package com.revature.crs.User;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isFaculty;

    public User(int userID, String firstName, String lastName, String email, String password, boolean isFaculty) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isFaculty = isFaculty;
    }

    public User() {
    }

    //#region Getters & Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFaculty() {
        return isFaculty;
    }

    public void setFaculty(boolean faculty) {
        isFaculty = faculty;
    }

    //endregion

    @Override
    public String toString() {
        return ("User{ID: " + userID + ", Name: " + firstName + " " + lastName +
                ", Email: " + email + (isFaculty ? ", Faculty" : ", Student") + "}");
    }
}
