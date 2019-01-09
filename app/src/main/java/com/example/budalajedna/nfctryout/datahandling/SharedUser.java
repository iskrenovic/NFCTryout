package com.example.budalajedna.nfctryout.datahandling;

import android.content.ContentProviderOperation;
import android.content.Intent;

import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

import org.json.JSONObject;

import java.util.ArrayList;

public class SharedUser implements AddContact.ContactCallback{

    private AddContact addContact;
    private Callback callback;
    private MainCallback mainCallback;

    private TwitterHandler twitterHandler;

    public SharedUser(Callback callback, MainCallback mainCallback){

        this.callback = callback;
        this.mainCallback = mainCallback;
        twitterHandler = new TwitterHandler();
    }

    public void save(String info){

        addContact = new AddContact(this);

        try {
            JSONObject object = new JSONObject(info);
            addContact.addContactInfo(object.getString("contactName"),object.getString("phoneNumber"),object.getString("email"));
            callback.openTwitterAccount(twitterHandler.openUser(object.getString("twitterId")));
            new WhatsApp(mainCallback.getActivity(),object.getString("phoneNumber"));
            callback.userSaved();
        }
        catch (Exception e){

        }
    }

    @Override
    public void addContact(ArrayList<ContentProviderOperation> operations) {
        callback.addContact(operations);
    }

    public interface Callback {
        void addContact(ArrayList<ContentProviderOperation> operations);
        void openTwitterAccount(Intent intent);
        void userSaved();
    }
}
