package com.example.veierovioum.lesson13registration;

import java.io.Serializable;

/**
 * Created by Veierovioum on 28/01/2016.
 */
public class User implements Serializable{

    String fName;
    String lName;
    String email;
    String phoneNumber;
    String address;
    String id;
    String password;

    public User(String fName, String lName, String email, String phoneNumber, String address, String id, String password) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.id = id;
        this.password = password;
    }


    @Override
    public String toString() {
        return fName+" "+lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    @Override
    public boolean equals(Object object) {

        User user;
        //check if object is a user
        try {
            user=(User) object;
        }
        catch (Exception e){
            return false;
        }
        //compare fields
        if (this.lName.equals(user.lName) && this.fName.equals(user.fName) && this.address.equals(user.address)
                && this.email.equals(user.email) && this.id.equals(user.id) && this.password.equals(user.password)
                && this.phoneNumber.equals(user.phoneNumber)){
            return false;
        }
        else return true;
    }
}
