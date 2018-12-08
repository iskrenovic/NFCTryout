package com.example.budalajedna.nfctryout.datahandling;

public class User {

    private String contactName;
    private String phoneNumber;

    private String email;

    public String read(){

        String out = "";
        if(contactName!=null){
            out += "//" + "CONTACTNAME>>" + contactName + "**";
        }
        if(phoneNumber!=null){
            out += "//" + "PHONENUMBER>>" + phoneNumber + "**";
        }
        if(email!=null){
            out += "//" + "EMAIL>>" + email + "**";
        }
        return out;
    }

    public boolean[] set(String info){

        boolean[] out = new boolean[4];

        while(!info.equals("")) {
            String type = info.substring(2, info.indexOf(">>"));
            switch (type) {
                case "CONTACTNAME":
                    contactName = info.substring(info.indexOf(">>") + 2, info.indexOf("**"));
                    break;
                case "PHONENUMBER":
                    out[2] = true;
                    phoneNumber = info.substring(info.indexOf(">>") + 2, info.indexOf("**"));
                    break;
                case "EMAIL":
                    out[3] = true;
                    email = info.substring(info.indexOf(">>") + 2, info.indexOf("**"));
                    break;
            }
            info = info.substring(info.indexOf("**") + 2);
        }
        return out;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
