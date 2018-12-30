package com.example.budalajedna.nfctryout.datahandling;

import org.json.JSONObject;

public class User {

    private String contactName = "";
    private String phoneNumber = "";

    private String email = "";

    public String read(){

        JSONObject object = new JSONObject();
        try {
            object.put("contactName", contactName);
            object.put("phoneNumber", phoneNumber);
            object.put("email",email);
        }
        catch (Exception e){}
        return object.toString();
    }

    public boolean[] set(String info){

        try {
            JSONObject object = new JSONObject(info);

            contactName = object.getString("contactName");
            phoneNumber = object.getString("phoneNumber");
            email = object.getString("email");
        }
        catch (Exception e){}
        return getKnown();
    }

    public boolean[] getKnown(){
        return new boolean[] {false,false,phoneNumber!="", email!=""};
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
