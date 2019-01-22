package com.example.budalajedna.nfctryout.datahandling;

import android.util.Log;

import org.json.JSONObject;

public class User {

    private String contactName = "";
    private String profilePicture = "";

    private String phoneNumber = "";
    private boolean sPhone = false;

    private String email = "";
    private boolean sEmail = false;

    private String facebookId = "";
    private boolean sFacebook = false;

    private String twitterId = "";
    private String twitterUserName="";
    private boolean sTwitter = false;

    private String skypeId = "";
    private boolean sSkype = false;

    private String instagramUsername ="";
    private boolean sInstagram = false;

    private boolean whatsApp = false;

    private Callback callback;

    public User(Callback callback){
        this.callback = callback;
    }

    public String read(){

        JSONObject object = new JSONObject();
        try {
            object.put("contactName", contactName);
            object.put("profilePicture",profilePicture);
            object.put("phoneNumber", phoneNumber);
            object.put("sPhone", sPhone);
            object.put("email",email);
            object.put("sEmail", sEmail);
            object.put("facebookId", facebookId);
            object.put("sFacebook", sFacebook);
            object.put("twitterId", twitterId);
            object.put("sTwitter", sTwitter);
            object.put("sSkype", sSkype);
            object.put("skypeId", skypeId);
            object.put("whatsApp", whatsApp);
            object.put("instagramUsername",instagramUsername);
            object.put("sInstagram", sInstagram);
            object.put("twitterUserName",twitterUserName);

        }
        catch (Exception e){}
        return object.toString();
    }

    public String send(){
        JSONObject object = new JSONObject();
        try {
            object.put("contactName", contactName);
            object.put("profilePicture","");
            object.put("phoneNumber", sPhone ? phoneNumber : "");
            object.put("email", sEmail ? email : "");
            object.put("facebookId", sFacebook ? facebookId : "");
            object.put("twitterId", sTwitter ? twitterId : "");
            object.put("skypeId", sSkype ? skypeId : "");
            object.put("instagramUsername", sInstagram ? instagramUsername : "");
            object.put("whatsApp", whatsApp);
        }
        catch (Exception e){
            Log.d("SEND","FAILED");
        }
        return object.toString();
    }

    public boolean[] set(String info){

        try {
            JSONObject object = new JSONObject(info);
            contactName = object.getString("contactName");
            callback.getUserName(contactName);
            profilePicture = object.getString("profilePicture");
            phoneNumber = object.getString("phoneNumber");
            sPhone = object.getBoolean("sPhone");
            email = object.getString("email");
            sEmail = object.getBoolean("sEmail");
            twitterId = object.getString("twitterId");
            sTwitter = object.getBoolean("sTwitter");
            skypeId = object.getString("skypeId");
            sSkype = object.getBoolean("sSkype");
            whatsApp = object.getBoolean("whatsApp");
            twitterUserName = object.getString("twitterUserName");
            sInstagram = object.getBoolean("sInstagram");
            instagramUsername = object.getString("instagramUsername");
        }
        catch (Exception e){
        }
        return getClicked();
    }

    public boolean[] getClicked(){
        return new boolean[] {sPhone,sEmail,sSkype, whatsApp, sTwitter, sFacebook, sInstagram};
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

    public String getSkypeId() {
        return skypeId;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public boolean getTwitter(){
        return sTwitter;
    }

    public String getInstagramUsername() {
        return instagramUsername;
    }

    public String getName() {
        return contactName;
    }

    public boolean getWhatsApp(){
        return whatsApp;
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

    public void clickTwitter(){
        sTwitter = !sTwitter;
    }

    public void clickFacebook(){sFacebook = !sFacebook;}

    public void clickSkype() {
        sSkype = !sSkype;
    }

    public void clickWhatsApp(){
        whatsApp = !whatsApp;
    }

    public void clickInstagram(){sInstagram = !sInstagram;}

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public void setTwitterId(String twitterId){
        this.twitterId = twitterId;
    }

    public void setFacebookId(String facebookId) {this.facebookId = facebookId;}

    public void setInstagramUsername(String username) {
        this.instagramUsername =username;
    }

    public void setTwitterUserName(String userName){this.twitterUserName=userName;}

    public interface Callback{
        void getUserName(String name);
    }
}
