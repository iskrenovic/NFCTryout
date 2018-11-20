package com.example.budalajedna.nfctryout.datahandling;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.provider.ContactsContract;
import android.widget.Toast;


import java.util.ArrayList;

public class AddContact{
    private Context context;
    private ContactCallback callback;
    public AddContact(Context context,ContactCallback callback) {
        this.context=context;
        this.callback=callback;
    }

    public void AddNumber(String contactName,String phoneNumber){

        ArrayList<ContentProviderOperation> operations=new ArrayList<ContentProviderOperation>();

        operations.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        // Dodaje ime kontakta
        if (contactName != null) {
            operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contactName)
                    .build());
        }

        //Dodaje broj
        if (phoneNumber != null) {
            operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
        }
        callback.AddContact(operations);



    }
    public interface ContactCallback{
        void AddContact(ArrayList<ContentProviderOperation> operations);
    }
}
