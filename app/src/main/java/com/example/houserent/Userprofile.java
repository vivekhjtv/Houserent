package com.example.houserent;

public class Userprofile {
    public String firstName;
    public String lastName;
    public String txtEmail;
    public String mobileNumber;

    public Userprofile() {
        //empty constructor
    }


    public Userprofile(String fname, String lname, String txtemail, String mnumber) {
        firstName = fname;
        lastName = lname;
        txtEmail = txtemail;
        mobileNumber = mnumber;


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

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}

