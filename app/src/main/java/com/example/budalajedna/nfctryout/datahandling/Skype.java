package com.example.budalajedna.nfctryout.datahandling;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;


public class Skype {

    Activity main;
    public Skype(Activity activity){
        main=activity;
    }
    public void sendSkypeMessage(String sendTo){
        /* Intent skype = new Intent("android.intent.action.VIEW");
        skype.setData(Uri.parse("skype:" +sendTo+ "?chat&topic=Topic"));
        main.startActivity(skype); */

        Uri skypeUri = Uri.parse(sendTo);
        Intent intent = new Intent(Intent.ACTION_VIEW, skypeUri);

        intent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "ej");
        intent.setData(Uri.parse("skype:" +sendTo+ "?chat&topic=Topic"));
        intent.setType("text/plain");

        main.startActivity(intent);
    }

}
