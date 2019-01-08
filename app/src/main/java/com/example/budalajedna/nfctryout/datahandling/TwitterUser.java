package com.example.budalajedna.nfctryout.datahandling;

import android.content.Intent;
import android.net.Uri;

import com.twitter.sdk.android.core.Twitter;

public class TwitterUser {
    private String userID;

    private TwitterMainCallback twitterMainCallback;
    public TwitterUser(TwitterMainCallback twitterMainCallback){
        this.twitterMainCallback=twitterMainCallback;
    }
    public void setUserID(String userID){
        this.userID=userID;
    }
    public void openUser(String userID){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://twitter.com/intent/follow?user_id="+userID));
        twitterMainCallback.openTwitterAccount(intent);
    }
    public interface TwitterMainCallback{
        void openTwitterAccount(Intent intent);
    }

}
