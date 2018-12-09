package com.example.budalajedna.nfctryout.presentation.main;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.connection.nfc.NFCManager;

import com.example.budalajedna.nfctryout.datahandling.ReadWriteClient;
import com.example.budalajedna.nfctryout.datahandling.SharedUser;
import com.example.budalajedna.nfctryout.datahandling.User;
import com.example.budalajedna.nfctryout.presentation.hello.HelloFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputPhoneNumberFragment;

import com.example.budalajedna.nfctryout.presentation.input.InputEmailFragment;
import com.example.budalajedna.nfctryout.presentation.setup.AllDoneFragment;
import com.example.budalajedna.nfctryout.presentation.share.ShareFragment;

import java.util.ArrayList;

public class AppActivity extends AppCompatActivity implements MainCallback,HelloFragment.Callback, ShareFragment.Callback, InputEmailFragment.callback,
        InputPhoneNumberFragment.Callback,  AllDoneFragment.Callback, SharedUser.Callback {

    private NFCManager nfcManager;
    private ReadWriteClient readWriteClient;
    private User user;
    private SharedUser sharedUser;

    private HelloFragment helloFragment;
    private ShareFragment shareFragment;
    private InputPhoneNumberFragment inputPhoneNumberFragment;
    private InputEmailFragment inputEmailFragment;
    private AllDoneFragment allDoneFragment;

    private boolean[] mediaToShare;
    private final int mediaNumber = 4;
    private Animation animation;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_activity);

        readWriteClient = new ReadWriteClient(this);

        textView = findViewById(R.id.textView);

        shareFragment = new ShareFragment();
        shareFragment.setCallback(this);

        helloFragment = new HelloFragment();
        helloFragment.setCallbacks(this, this);

        inputPhoneNumberFragment = new InputPhoneNumberFragment();
        inputPhoneNumberFragment.setCallbacks(this, this);

        inputEmailFragment = new InputEmailFragment();
        inputEmailFragment.setCallbacks(this,this);

        allDoneFragment = new AllDoneFragment();
        allDoneFragment.setCallbacks(this, this);

        nfcManager = new NFCManager(this);

        user = new User();
        sharedUser = new SharedUser(this);

        String userInfo = readWriteClient.read();

        if(userInfo.equals("")){
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,this.helloFragment).commitAllowingStateLoss();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,this.shareFragment).commitAllowingStateLoss();
            shareFragment.setButtonStates(user.set(userInfo));
        }
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
            sharedUser.save(nfcManager.getTextFromBeam(intent));
        }

        super.onNewIntent(intent);
    }

    public void toastMaker(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private int getNextMediaIndex(int startIndex){
        for (int i = startIndex; i < mediaNumber; i++) {
            if(mediaToShare[i]) return i;
        }
        return -1;
    }
    //Za sad ovako slepacki izgleda, jbg. Kad se napravi Facebook i Insta bice bolje :)
    private Fragment getFragment(int index){
        switch (index){
            case 2:
                return inputPhoneNumberFragment;
            case 3:
                return inputEmailFragment;
            default:
                return allDoneFragment;
        }
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public ReadWriteClient getReadWriteClient() {
        return readWriteClient;
    }

    @Override
    public void nextEmailFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,getFragment(getNextMediaIndex(4))).commitAllowingStateLoss();
    }

    @Override
    public void nextAllDone() {
        readWriteClient.save(user.read());
    }

    @Override
    public void nextContact() {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,getFragment(getNextMediaIndex(3))).commitAllowingStateLoss();
    }

    @Override
    public void nextHello() {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,this.shareFragment).commitAllowingStateLoss();
    }

    @Override
    public void nextShare(boolean[] mediaToShare) {
        this.mediaToShare = mediaToShare;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,getFragment(getNextMediaIndex(0))).commitAllowingStateLoss();
    }

    @Override
    public void addContact(ArrayList<ContentProviderOperation> operations) {
        try{
            this.getApplicationContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);
        }
        catch (Exception e){}
    }
}
