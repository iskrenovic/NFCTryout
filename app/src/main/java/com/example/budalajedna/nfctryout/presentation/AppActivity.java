package com.example.budalajedna.nfctryout.presentation;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.connection.NFCManager;

import com.example.budalajedna.nfctryout.datahandling.User;
import com.example.budalajedna.nfctryout.presentation.input.InputContactFragment;

import com.example.budalajedna.nfctryout.datahandling.AddContact;

import com.example.budalajedna.nfctryout.presentation.input.InputEmailFragment;
import com.example.budalajedna.nfctryout.presentation.share.ShareFragment;
import com.example.budalajedna.nfctryout.presentation.share.ShareViewModel;

import java.util.ArrayList;


public class AppActivity extends AppCompatActivity implements ShareViewModel.Callback, MainCallback,AddContact.ContactCallback, InputEmailFragment.callback {

    private NFCManager nfcManager;
    private ShareFragment shareFragment;

    private User user;

    private InputContactFragment inputContactFragment;
    private InputEmailFragment inputEmailFragment;

    private AddContact addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_activity);

        shareFragment = new ShareFragment();
        addContact=new AddContact(this.getApplicationContext(),this);

        // ova linija ce da se obrise
        // umesto toga ce kad primi poruku nfc manager da zove callback do add contact pa onda da ide do
        // add contact funkcije (cisto sam hteo da isprobam da dodam neki broj)
        addContact.AddNumber("Neko","1234","neko@gmail");



        inputContactFragment = new InputContactFragment();

        inputEmailFragment = new InputEmailFragment();

        inputEmailFragment.setCallbacks(this,this);

        nfcManager = new NFCManager(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,this.shareFragment).commitAllowingStateLoss();
    }

    @Override
    protected void onResume() {

        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        if(action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            toastMaker(nfcManager.getTextFromBeam(intent));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);

        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())){
            toastMaker(nfcManager.getTextFromBeam(intent));
        }

        super.onNewIntent(intent);
    }

    public void toastMaker(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }





    @Override
    public void startClick() {

    }

    @Override
    public void sendClick() {

    }

    @Override
    public void AddContact(ArrayList<ContentProviderOperation> operations) {
        try {
            this.getApplicationContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);
            Toast.makeText(this.getApplicationContext(),"Dodat broj", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this.getApplicationContext(),"Nije dodat broj", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void nextEmailFragment() {

    }
}
