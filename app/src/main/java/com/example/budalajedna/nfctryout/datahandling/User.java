package com.example.budalajedna.nfctryout.datahandling;

import org.json.JSONObject;

public class User {

    private String contactName = "";

    private String phoneNumber = "";
    private boolean sPhone = false;

    private String email = "";
    private boolean sEmail = false;

    private String twitterId = "";
    private boolean sTwitter = false;



    public String read(){

        JSONObject object = new JSONObject();
        try {
            object.put("contactName", contactName);
            object.put("phoneNumber", phoneNumber);
            object.put("sPhone", sPhone);
            object.put("email",email);
            object.put("sEmail", sEmail);
            object.put("twitterId", twitterId);
            object.put("sTwitter", sTwitter);
        }
        catch (Exception e){}
        return object.toString();
    }

    public String send(){
        JSONObject object = new JSONObject();
        try {
            object.put("contactName", contactName);
            if(sPhone)object.put("phoneNumber", phoneNumber);
            if(sEmail)object.put("email",email);
            if(sTwitter)object.put("twitterId", twitterId);
        }
        catch (Exception e){}
        return object.toString();
    }

    public boolean[] set(String info){

        try {
            JSONObject object = new JSONObject(info);
            contactName = object.getString("contactName");
            phoneNumber = object.getString("phoneNumber");
            sPhone = object.getBoolean("sPhone");
            email = object.getString("email");
            sEmail = object.getBoolean("sEmail");
            twitterId = object.getString("twitterId");
            sTwitter = object.getBoolean("sTwitter");
        }
        catch (Exception e){}
        return getKnown();
    }

    public boolean[] getKnown(){
        return new boolean[] {false,false,sPhone, sEmail, sTwitter};
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public void clickPhone() {
        sPhone = !sPhone;
    }

    public void clickEmail() {
       sEmail = !sEmail;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setTwitterId(String twitterId){
        this.twitterId = twitterId;
    }
}
