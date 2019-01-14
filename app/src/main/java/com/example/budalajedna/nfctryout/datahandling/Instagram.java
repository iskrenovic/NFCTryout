package com.example.budalajedna.nfctryout.datahandling;

import com.example.budalajedna.nfctryout.presentation.input.InstagramAuthDialog;

public class Instagram implements InstagramAuthDialog.AuthenticationListener {
    private String authToken;
    public Instagram(){

    }
    @Override
    public void onTokenReceived(String auth_token) {
        this.authToken=authToken;
        InstagramRequest instagramRequest=new InstagramRequest();
        instagramRequest.setToken(authToken);
        instagramRequest.execute();
    }
}
