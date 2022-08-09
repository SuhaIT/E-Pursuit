package com.jerucloud.epursuit;

public class chatlist1 {
    private  String id,name,message,date,time;

    public chatlist1(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public chatlist1(String id, String name, String message, String date, String time) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
