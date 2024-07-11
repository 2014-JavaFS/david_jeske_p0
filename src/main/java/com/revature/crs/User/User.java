package com.revature.crs.User;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserType type;

    public enum UserType {
        STUDENT, FACULTY
    }

    public User(int userID, String firstName, String lastName, String email, String password, UserType userType) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.type = userType;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
    //endregion

    @Override
    public String toString() {
        return ("User{ID: " + userID + ", Name: " + firstName + " " + lastName + ", Email: " + email + ", Type: " + type+"}");
    }
}
