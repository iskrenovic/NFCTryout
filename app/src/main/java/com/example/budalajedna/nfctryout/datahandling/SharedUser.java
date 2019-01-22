package com.example.budalajedna.nfctryout.datahandling;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.util.Log;

import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

import org.json.JSONObject;

import java.util.ArrayList;

public class SharedUser implements AddContact.ContactCallback{

    private AddContact addContact;
    private Callback callback;
    private MainCallback mainCallback;

    private TwitterHandler twitterHandler;
    private Skype skype;
    private Instagram instagram;


    public SharedUser(Callback callback, MainCallback mainCallback){

        this.callback = callback;
        this.mainCallback = mainCallback;
        twitterHandler = new TwitterHandler();
        instagram = new Instagram();
    }

    public void save(String info){

        addContact = new AddContact(this,mainCallback.getPictureTransform());
        skype = new Skype(mainCallback.getActivity());

        try {
            JSONObject object = new JSONObject(info);
            addContact.addContactInfo(object.getString("contactName"),object.getString("phoneNumber"),object.getString("email"), object.getString("profilePicture"));
            if(!object.getString("twitterId").equals("")) callback.openIntent(twitterHandler.openUser(object.getString("twitterId")));
            //callback.openIntent(twitterHandler.isFollowing(object.getString("twitterId")));
            if(object.getBoolean("sWhatsApp")) new WhatsApp(mainCallback.getActivity(),object.getString("phoneNumber"));
            if(!object.getString("instagramUsername").equals("")) callback.openIntent(instagram.openAccount(object.getString("instagramUsername")));
            skype.openSkypeChat(object.getString("skypeId"));

            callback.userSaved();
        }
        catch (Exception e){
            Log.d("TAG",e.toString());
            callback.saveFailed();
        }
    }

    @Override
    public void addContact(ArrayList<ContentProviderOperation> operations) {
        callback.addContact(operations);
    }

    public interface Callback {
        void addContact(ArrayList<ContentProviderOperation> operations);
        void openIntent(Intent intent);
        void userSaved();
        void saveFailed();
    }
}
