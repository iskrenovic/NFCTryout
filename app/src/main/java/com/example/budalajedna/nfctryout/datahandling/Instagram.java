package com.example.budalajedna.nfctryout.datahandling;

public class Instagram {
    private String authToken;
    public Instagram(){

    }
    public void setAuthToken(String authToken){
        this.authToken=authToken;
        InstagramRequest instagramRequest=new InstagramRequest();
        instagramRequest.setToken(authToken);
        instagramRequest.execute();
    }

}
