package com.example.budalajedna.nfctryout.datahandling;

public class User {

    private String contactName;
    private int contactNumber;

    private String email;

    public User(){
        contactNumber = -1;
        contactName = null;
    }

    public String readInfo(){

        String out = "";
        if(contactName!=null){
            out += "//" +  contactName + "*-*" + contactNumber;
        }
        return out;
    }

    public void setContact(String contactName, int contactNumber){
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
