package com.example.budalajedna.nfctryout.datahandling;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.budalajedna.nfctryout.presentation.main.AppActivity;

public class Viber {


    public Viber(Activity activity, String phone){

        String link = "viber://contact/?number=%2B" + phone;
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(intent);


    }


}
