package com.example.budalajedna.nfctryout.datahandling;

public class User {

    private String contactName;
    private int phoneNumber;

    private String email;

    public User(){
        phoneNumber = -1;
    }

    public String read(){

        String out = "";
        if(contactName!=null){
            out += "//" + "CONTACTNAME>>" + contactName + "**";
        }
        if(phoneNumber !=-1){
            out += "//" + "PHONENUMBER>>" + phoneNumber + "**";
        }
        if(email!=null){
            out += "//" + "EMAIL>>" + phoneNumber + "**";
        }
        return out;
    }

    public void set(String info){

        while(!info.equals("")) {
            String type = info.substring(2, info.indexOf(">>"));
            switch (type) {
                case "CONTACTNAME":
                    contactName = info.substring(info.indexOf(">>"), info.indexOf("**"));
                    break;
                case "PHONENUMBER":
                    phoneNumber = Integer.valueOf(info.substring(info.indexOf(">>"), info.indexOf("**")));
                    break;
                case "EMAIL":
                    email = info.substring(info.indexOf(">>"), info.indexOf("**"));
                    break;
            }
            info = info.substring(info.indexOf("**"));
        }
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
