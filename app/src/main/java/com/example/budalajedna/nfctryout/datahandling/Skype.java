package com.example.budalajedna.nfctryout.datahandling;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;


public class Skype {

    Activity main;
    public Skype(Activity activity){
        main=activity;
    }
    public void openSkypeChat(String skypeUserName){
        Intent skype = new Intent("android.intent.action.VIEW");
        skype.setData(Uri.parse("skype:" +skypeUserName+ "?chat"));
        main.startActivity(skype);
    }

}
