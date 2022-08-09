package com.jerucloud.epursuit;

public class chatlist {
    private  String idreciever,idsender,namereciver,namesender;

    public chatlist(String idreciever, String idsender, String namereciver, String namesender) {
        this.idreciever = idreciever;
        this.idsender = idsender;
        this.namereciver = namereciver;
        this.namesender = namesender;
    }

    public String getIdreciever() {
        return idreciever;
    }

    public void setIdreciever(String idreciever) {
        this.idreciever = idreciever;
    }

    public String getIdsender() {
        return idsender;
    }

    public void setIdsender(String idsender) {
        this.idsender = idsender;
    }

    public String getNamereciver() {
        return namereciver;
    }

    public void setNamereciver(String namereciver) {
        this.namereciver = namereciver;
    }

    public String getNamesender() {
        return namesender;
    }

    public void setNamesender(String namesender) {
        this.namesender = namesender;
    }
}
