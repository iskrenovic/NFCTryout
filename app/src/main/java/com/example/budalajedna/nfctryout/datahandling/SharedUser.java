package com.example.budalajedna.nfctryout.datahandling;

import android.content.ContentProviderOperation;

import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

import org.json.JSONObject;

import java.util.ArrayList;

public class SharedUser implements AddContact.ContactCallback {

    AddContact addContact;
    Callback callback;
    MainCallback mainCallback;

    public SharedUser(Callback callback, MainCallback mainCallback){

        this.callback = callback;
        this.mainCallback = mainCallback;
    }

    public void save(String info){

        addContact = new AddContact(this);

        try {
            JSONObject object = new JSONObject(info);
            addContact.addContactInfo(object.getString("contactName"),object.getString("phoneNumber"),object.getString("email"));
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
        void userSaved();
    }
}
