package com.example.budalajedna.nfctryout.datahandling;

import android.content.ContentProviderOperation;
import android.graphics.Bitmap;
import android.provider.ContactsContract;

import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddContact{
    private ContactCallback callback;
    private PictureTransform pictureTransform;
    public AddContact(ContactCallback callback, PictureTransform pictureTransform) {
        this.callback=callback;
        this.pictureTransform = pictureTransform;
    }

    public void addContactInfo(String contactName, String phoneNumber, String mail, String profilePicture){

        ArrayList<ContentProviderOperation> operations=new ArrayList<ContentProviderOperation>();

        operations.add(ContentProviderOperation.newInsert(
                ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        // Dodaje ime kontakta
        if (!contactName.equals("")) {
            operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contactName)
                    .build());
        }

        //Dodaje broj
        if (!phoneNumber.equals("")) {
            operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
        }

        //Dodaje mail
        if (!mail.equals("")) {
            operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, mail)
                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_MOBILE)
                    .build());
        }


        //PROFILE PICTURE
        if(!profilePicture.equals("")){
            Bitmap photo = pictureTransform.decodeBitmapString(profilePicture);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100,stream);
            byte[] profile = stream.toByteArray();
            operations.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE,profile)
                    .build());
        }
        callback.addContact(operations);
    }
    public interface ContactCallback{
        void addContact(ArrayList<ContentProviderOperation> operations);
    }
}
