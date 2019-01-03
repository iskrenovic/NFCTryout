package com.example.budalajedna.nfctryout.datahandling;

import android.content.ContentProviderOperation;

import org.json.JSONObject;

import java.util.ArrayList;

public class SharedUser implements AddContact.ContactCallback {

    AddContact addContact;
    Callback callback;

    public SharedUser(Callback callback){
        this.callback = callback;
    }

    public void save(String info){

        addContact = new AddContact(this);

        try {
            JSONObject object = new JSONObject(info);
            addContact.addContactInfo(object.getString("contactName"),object.getString("phoneNumber"),object.getString("email"));
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
        void userSaved();
    }
}
