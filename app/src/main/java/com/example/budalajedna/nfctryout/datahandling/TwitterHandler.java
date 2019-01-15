package com.example.budalajedna.nfctryout.datahandling;

import android.content.Intent;
import android.net.Uri;

import com.example.budalajedna.nfctryout.R;

public class TwitterHandler {
    public Intent openUser(String userID){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://twitter.com/intent/follow?user_id="+userID));
        return intent;
    }

    public Intent isFollowing(String userID){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.twitter.com/1.1/users/lookup.json?user_id="+userID));
        return intent;
        // Not working
    }
}
