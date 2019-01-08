package com.example.budalajedna.nfctryout.datahandling;

import android.content.Intent;
import android.net.Uri;

import com.example.budalajedna.nfctryout.presentation.main.AppActivity;

public class WhatsApp {

    public WhatsApp(AppActivity activity, String number){
        String link = "https://wa.me/" + number.substring(1) + "?text=Zdravo,%20povezali%20smo%20se%20preko%20HandShake-a";
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);
    }
}
