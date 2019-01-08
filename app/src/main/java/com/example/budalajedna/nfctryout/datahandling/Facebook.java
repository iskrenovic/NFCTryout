package com.example.budalajedna.nfctryout.datahandling;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

public class Facebook {

    String userId = "";
    FacebookCallback facebookCallback;
    Context context;

    public Facebook(Context context,FacebookCallback facebookCallback) {
        this.context=context;

        this.facebookCallback = facebookCallback;
    }

    public void openAccount(String url) {
        if(context!=null) {
            PackageManager pm = context.getPackageManager();
            Uri uri = Uri.parse(url);
            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
                if (applicationInfo.enabled) {
                    uri = Uri.parse("https://www.facebook.com/app_scoped_user_id/" + url);
                }
            } catch (PackageManager.NameNotFoundException ignored) {
            }
            facebookCallback.openAccount(new Intent(Intent.ACTION_VIEW, uri));
        }
    }



    public void setUserId(AccessToken accessToken){


        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        userId=object.toString();
                        userId= userId.substring(7,userId.lastIndexOf("}")-1);
                        openAccount(userId);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id");
        request.setParameters(parameters);
        request.executeAsync();



    }
    public String getUserId(){
        return userId;
        }
    public interface FacebookCallback{
        void openAccount(Intent intent);
    }
}
