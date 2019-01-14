package com.example.budalajedna.nfctryout.datahandling;

import android.content.Intent;
import android.net.Uri;

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

    public void openAccount(String userName){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://instagram.com/"+userName));
    }
    public interface OpenInstagramCallback{
        void StartIntent(Intent intent);
    }
}
