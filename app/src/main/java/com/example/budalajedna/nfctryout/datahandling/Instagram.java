package com.example.budalajedna.nfctryout.datahandling;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class Instagram {
    private String authToken;
    private Activity appActivity;
    private InstagramRequest.SetInstagramUserCallback setInstagramUserCallback;
    public Instagram(Activity activity, InstagramRequest.SetInstagramUserCallback setInstagramUserCallback){
        this.appActivity=activity;
        this.setInstagramUserCallback=setInstagramUserCallback;
    }

    public void onTokenReceived(String auth_token) {
        this.authToken=authToken;
        InstagramRequest instagramRequest=new InstagramRequest();
        instagramRequest.setToken(authToken);
        instagramRequest.setInstagramUserCallback(setInstagramUserCallback);
        instagramRequest.execute();
    }

    public void openAccount(String userName){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://instagram.com/"+userName));


    }
}
