package com.example.grocery;

public class Users {
    String Name, Username, Email, PhoneNo, Password;

    public Users() {
    }

    public Users(String name,String username, String email, String phoneNo, String password) {
        Name = name;
        Username = username;
        Email = email;
        PhoneNo = phoneNo;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
