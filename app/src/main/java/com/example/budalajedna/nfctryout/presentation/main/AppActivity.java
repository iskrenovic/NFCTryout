package com.example.budalajedna.nfctryout.presentation.main;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.connection.nfc.NFCManager;
import com.example.budalajedna.nfctryout.connection.wifi.WifiManager;
import com.example.budalajedna.nfctryout.datahandling.Facebook;
import com.example.budalajedna.nfctryout.datahandling.ReadWriteClient;
import com.example.budalajedna.nfctryout.datahandling.SharedUser;
import com.example.budalajedna.nfctryout.datahandling.Skype;
import com.example.budalajedna.nfctryout.datahandling.User;
import com.example.budalajedna.nfctryout.datahandling.Viber;
import com.example.budalajedna.nfctryout.presentation.hello.HelloFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputEmailFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputFacebookFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputPhoneNumberFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputTwitterFragment;
import com.example.budalajedna.nfctryout.presentation.setup.AllDoneFragment;
import com.example.budalajedna.nfctryout.presentation.share.NewShareFragment;
import com.example.budalajedna.nfctryout.presentation.share.ShareFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.ArrayList;

public class AppActivity extends AppCompatActivity implements MainCallback,User.Callback,HelloFragment.Callback, ShareFragment.Callback, InputEmailFragment.callback,
        InputPhoneNumberFragment.Callback, InputFacebookFragment.Callback, InputTwitterFragment.Callback,  AllDoneFragment.Callback, SharedUser.Callback, WifiManager.Callback,
        Facebook.FacebookCallback {

    private MainViewModel mainViewModel;

    private NFCManager nfcManager;
    private WifiManager wifiManager;
    private ReadWriteClient readWriteClient;
    private User user;
    private SharedUser sharedUser;

    private Facebook facebook;

    private CallbackManager callbackManager;

    private HelloFragment helloFragment;

    private ShareFragment shareFragment;
    private NewShareFragment newShareFragment;

    private InputPhoneNumberFragment inputPhoneNumberFragment;
    private InputEmailFragment inputEmailFragment;
    private InputFacebookFragment inputFacebookFragment;
    private InputTwitterFragment inputTwitterFragment;
    private AllDoneFragment allDoneFragment;

    private boolean[] mediaToShare;
    private final int mediaNumber = 6;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_activity);

        mainViewModel = new MainViewModel();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager=CallbackManager.Factory.create();

        nfcManager = new NFCManager(this);
        wifiManager = new WifiManager(this, this,this);

        readWriteClient = new ReadWriteClient(this);

        shareFragment = new ShareFragment();
        shareFragment.setCallback(this, this);

        newShareFragment = new NewShareFragment();

        helloFragment = new HelloFragment();
        helloFragment.setCallbacks(this, this);

        inputPhoneNumberFragment = new InputPhoneNumberFragment();
        inputPhoneNumberFragment.setCallbacks(this, this);

        inputEmailFragment = new InputEmailFragment();
        inputEmailFragment.setCallbacks(this,this);

        inputFacebookFragment = new InputFacebookFragment();
        inputFacebookFragment.setCallback(this,this);

        inputTwitterFragment = new InputTwitterFragment();
        inputTwitterFragment.setCallback(this);

        allDoneFragment = new AllDoneFragment();
        allDoneFragment.setCallbacks(this, this);

        user = new User(this);
        sharedUser = new SharedUser(this, this);

        String userInfo = readWriteClient.read();


        if(userInfo.equals("")){
            mainViewModel.setText("Dobrodoso u Handshake");
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,this.helloFragment).commitAllowingStateLoss();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,this.shareFragment).commitAllowingStateLoss();
            shareFragment.setButtonStates(user.set(userInfo));
        }

        facebook = new Facebook(this,this);

        Skype skype=new Skype(this);

    }

    @Override
    protected void onResume() {

        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        if(action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            wifiManager.connect(nfcManager.getTextFromBeam(intent));
        }
    }

    @Override
    protected void onStop() {
        disconnectWIFI();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        disconnectWIFI();
        super.onDestroy();
    }

    private void disconnectWIFI(){
        if(wifiManager.isRegistred()) {
            wifiManager.unregisterReceiver();
            wifiManager.disconnect();
            wifiManager.setRegistred(false);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);

        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())){
            /*wifiManager.connect(nfcManager.getTextFromBeam(intent));*/
        }

        super.onNewIntent(intent);
    }

    public void toastMaker(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private Fragment getNextMediaIndex(int startIndex){
        for (int i = startIndex; i < mediaNumber; i++) {
            if(mediaToShare[i]) if(getFragment(i)!=null) return getFragment(i);
        }
        if(startIndex == 0) toastMaker("VEC STE UBACILI OVE INFORMACIJE");
        return null;
    }
    private Fragment getFragment(int index){
        switch (index){ //OSNOVA INDEXA SE NALAZI U MEDIA TYPE
            case 0:
                if(user.getPhoneNumber().equals("")) //PHONE NUMBER
                    return inputPhoneNumberFragment;
                else return null;
            case 1:
                if(user.getEmail().equals(""))  //EMAIL
                    return inputEmailFragment;
                else return null;
            case 2:
                if(user.getFacebookId().equals(""))  //FACEBOOK
                    return inputFacebookFragment;
                else return null;

            case 4:
                if(user.getPhoneNumber().equals("")) //WHATSAPP
                    return inputPhoneNumberFragment;
                else return null;

            case 5:
                if(user.getTwitterId().equals("")) { //TWITTER


                    return inputTwitterFragment;
                }
                else return null;


            default:
                readWriteClient.save(user.read());
                return allDoneFragment;
        }
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public String getDeviceAdress() {
        return wifiManager.getDeviceAdress();
    }


    @Override
    public AppActivity getActivity() {
        return this;
    }

    @Override
    public ReadWriteClient getReadWriteClient() {
        return readWriteClient;
    }

    @Override
    public void nextAllDone() {
        readWriteClient.save(user.read());
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,this.shareFragment).commitAllowingStateLoss();
        shareFragment.setButtonStates(user.getKnown());
    }

    @Override
    public void nextHello() {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,this.shareFragment).commitAllowingStateLoss();
    }


    @Override
    public void nextShare(boolean[] mediaToShare) {
        this.mediaToShare = mediaToShare;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,getNextMediaIndex(0)).commitAllowingStateLoss();
    }

    @Override
    public void setAccesToken(AccessToken accesToken) {
        facebook.setUserId(accesToken);
    }

    @Override
    public void setTwitterUserID(String userID) {
        user.setTwitterId(userID);
    }

    @Override
    public void nextFragment(int startIndex) {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame,getNextMediaIndex(startIndex)).commitAllowingStateLoss();
    }


    @Override
    public void addContact(ArrayList<ContentProviderOperation> operations) {
        try{
            this.getApplicationContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);
        }
        catch (Exception e){
            toastMaker(e.toString());
        }
    }

    @Override
    public void userSaved() {
        disconnectWIFI();
    }

    @Override
    public void onInvitationSend(boolean sent) {

    }

    @Override
    public void onUserReceived(String info) {
        sharedUser.save(info);
    }

    @Override
    public void openAccount(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                fragment.onActivityResult(requestCode, resultCode, data);
                Log.d("Activity", "ON RESULT CALLED");
            }
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
    }

    @Override
    public void openTwitterLink(Intent intent) {
        startActivity(intent);
    }


    @Override
    public void getUserName(String name) {
        mainViewModel.setText(name +", sta zelis da delis?");
    }

}
