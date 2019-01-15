package com.example.budalajedna.nfctryout.datahandling;

import android.content.Intent;
import android.net.Uri;

import com.example.budalajedna.nfctryout.presentation.main.AppActivity;


public class Skype {

    AppActivity main;
    public Skype(AppActivity activity){
        main=activity;
    }
    public void openSkypeChat(String skypeUserName){
        if(!skypeUserName.equals("")) {
            Intent skype = new Intent("android.intent.action.VIEW");
            skype.setData(Uri.parse("skype:" + skypeUserName + "?chat"));
            main.startActivity(skype);
        }
    }
}
