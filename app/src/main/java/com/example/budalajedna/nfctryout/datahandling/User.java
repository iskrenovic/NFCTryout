package com.example.budalajedna.nfctryout.datahandling;

import org.json.JSONObject;

public class User {

    private String contactName = "";

    private String phoneNumber = "";
    private boolean sPhone = false;

    private String email = "";
    private boolean sEmail = false;

    private String facebookId = "";
    private boolean sFacebook = false;

    private String twitterId = "";
    private boolean sTwitter = false;

    Callback callback;

    public User(Callback callback){
        this.callback = callback;
    }

    public String read(){

        JSONObject object = new JSONObject();
        try {
            object.put("contactName", contactName);
            object.put("phoneNumber", phoneNumber);
            object.put("sPhone", sPhone);
            object.put("email",email);
            object.put("sEmail", sEmail);
            object.put("facebookId", facebookId);
            object.put("sFacebook", sFacebook);
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
            if(sFacebook)object.put("facebookId", facebookId);
            if(sTwitter)object.put("twitterId", twitterId);
        }
        catch (Exception e){}
        return object.toString();
    }

    public boolean[] set(String info){

        try {
            JSONObject object = new JSONObject(info);
            contactName = object.getString("contactName");
            callback.getUserName(contactName);
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

    public String getFacebookId(){return facebookId;}

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public void clickPhone() {
        sPhone = !sPhone;
    }

    public void clickEmail() {
       sEmail = !sEmail;
    }

    public void clickTwitter(){
        sTwitter = !sTwitter;
    }

    public void clickFacebook(){sFacebook = !sFacebook;}

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setTwitterId(String twitterId){
        this.twitterId = twitterId;
    }

    public void setFacebookId(String facebookId) {this.facebookId = facebookId;}

    public interface Callback{
        void getUserName(String name);
    }
}
