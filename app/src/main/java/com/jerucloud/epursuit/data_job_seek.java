package com.jerucloud.epursuit;

import java.io.Serializable;

public class data_job_seek implements Serializable {
    String firstnamee,
            lastname,
            Gender,
            Date,
            Phone,email,
            Password;

    public data_job_seek(String firstnamee, String lastname, String gender, String date, String phone, String email, String password) {
        this.firstnamee = firstnamee;
        this.lastname = lastname;
        Gender = gender;
        Date = date;
        Phone = phone;
        this.email = email;
        Password = password;
    }

    public data_job_seek() {

    }

    public String getFirstnamee() {
        return firstnamee;
    }

    public void setFirstnamee(String firstnamee) {
        this.firstnamee = firstnamee;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
