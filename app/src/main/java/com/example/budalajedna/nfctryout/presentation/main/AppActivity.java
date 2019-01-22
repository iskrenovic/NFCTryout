package com.example.budalajedna.nfctryout.presentation.main;

import android.Manifest;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.connection.nfc.NFCManager;
import com.example.budalajedna.nfctryout.connection.wifi.WifiManager;
import com.example.budalajedna.nfctryout.datahandling.InstagramRequest;
import com.example.budalajedna.nfctryout.datahandling.PictureTransform;
import com.example.budalajedna.nfctryout.datahandling.ReadWriteClient;
import com.example.budalajedna.nfctryout.datahandling.SharedUser;
import com.example.budalajedna.nfctryout.datahandling.User;
import com.example.budalajedna.nfctryout.presentation.dialogs.EditDialog;
import com.example.budalajedna.nfctryout.presentation.hello.HelloFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputEmailFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputFacebookFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputInstagramFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputInstagramFragment.InstagramCallback;
import com.example.budalajedna.nfctryout.presentation.input.InputPhoneNumberFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputSkypeFragment;
import com.example.budalajedna.nfctryout.presentation.input.InputTwitterFragment;
import com.example.budalajedna.nfctryout.presentation.profile.ProfileFragment;
import com.example.budalajedna.nfctryout.presentation.setup.AllDoneFragment;
import com.example.budalajedna.nfctryout.presentation.share.ShareFragment;
import com.facebook.AccessToken;

import java.util.ArrayList;

public class AppActivity extends AppCompatActivity implements MainCallback,User.Callback,HelloFragment.Callback, ShareFragment.Callback,
        InputEmailFragment.callback, InputPhoneNumberFragment.Callback, InputFacebookFragment.Callback, InputTwitterFragment.Callback,
        AllDoneFragment.Callback, SharedUser.Callback, WifiManager.Callback, EditDialog.Callback, MainViewModel.Callback,InstagramCallback,
        InputSkypeFragment.Callback, ProfileFragment.Callback,InstagramRequest.SetInstagramUserCallback {

    private MainViewModel viewModel;

    private ImageButton backButton;
    private ImageButton profileButton;

    private NFCManager nfcManager;
    private WifiManager wifiManager;
    private ReadWriteClient readWriteClient;
    private User user;
    private SharedUser sharedUser;
    private PictureTransform pictureTransform;

    private HelloFragment helloFragment;
    private ShareFragment shareFragment;
    private ProfileFragment profileFragment;

    private boolean newUser = false;

    /*currentIndex -2 - mediaNumber
    * -2 - edit
    * -1 - Welcome
    *  0 - ShareFragment
    *  1-8 - Fragments*/
    private int currentIndex = 0;
    private boolean done;

    private InputPhoneNumberFragment inputPhoneNumberFragment;
    private InputEmailFragment inputEmailFragment;
    private InputSkypeFragment inputSkypeFragment;
    private InputFacebookFragment inputFacebookFragment;
    private InputTwitterFragment inputTwitterFragment;
    private AllDoneFragment allDoneFragment;
    private InputInstagramFragment inputInstagramFragment;

    private boolean[] mediaToShare;
    private final int mediaNumber = 7;
    private Animation animation;

    private static final int PERMISSION_ALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_activity);

        viewModel = new MainViewModel();

        String[] permissions = {
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.INTERNET
        };

        if (!hasPermissions(permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_ALL);
        }

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 1;
                getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, profileFragment).commitAllowingStateLoss();
            }
        });

        nfcManager = new NFCManager(this);
        wifiManager = new WifiManager(this, this, this);

        readWriteClient = new ReadWriteClient(this);

        shareFragment = new ShareFragment();
        shareFragment.setCallback(this, this);

        helloFragment = new HelloFragment();
        helloFragment.setCallbacks(this, this);

        profileFragment = new ProfileFragment();
        profileFragment.setCallback(this, this);

        inputPhoneNumberFragment = new InputPhoneNumberFragment();
        inputPhoneNumberFragment.setCallbacks(this, this);

        inputEmailFragment = new InputEmailFragment();
        inputEmailFragment.setCallbacks(this, this);

        inputSkypeFragment = new InputSkypeFragment();
        inputSkypeFragment.setCallback(this, this);

        inputFacebookFragment = new InputFacebookFragment();
        inputFacebookFragment.setCallback(this, this);

        inputTwitterFragment = new InputTwitterFragment();
        inputTwitterFragment.setCallback(this);

        allDoneFragment = new AllDoneFragment();
        allDoneFragment.setCallbacks(this, this);

        pictureTransform = new PictureTransform();

        user = new User(this);
        sharedUser = new SharedUser(this, this);

        inputInstagramFragment = new InputInstagramFragment();
        inputInstagramFragment.setCallback(this,this);

        String userInfo = readWriteClient.read();

        if (userInfo.equals("")) {
            viewModel.setText("Dobrodosao u Handshake");
            newUser = true;
            currentIndex = -1;
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, this.helloFragment).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, this.shareFragment).commitAllowingStateLoss();
            shareFragment.setButtonStates(user.set(userInfo));
        }


    }

    @Override
    protected void onResume() {

        super.onResume();
        Intent intent = getIntent();
        String action = intent.getAction();
        if (action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
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

    @Override
    public void onBackPressed() {
        if ((currentIndex == 0 && !newUser) || currentIndex == -1) super.onBackPressed();
        goBack();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);
    }

    private boolean hasPermissions(String[] permissions) {
        if (permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void goBack(){
        switch (currentIndex){
            case -2:
                currentIndex = 1;
                getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, profileFragment).commitAllowingStateLoss();
                break;
            case 1:
                currentIndex = 0;
                getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, shareFragment).commitAllowingStateLoss();
                break;
            default:
                getLastMediaFragment();
                break;
        }
    }

    private void disconnectWIFI() {
        if (wifiManager.isRegistred()) {
            wifiManager.unregisterReceiver();
            wifiManager.disconnect();
            wifiManager.setRegistred(false);
        }
    }


    public void toastMaker(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void getNextMediaFragment() {
        done = false;
        for (int i = currentIndex; i < mediaNumber && !done; ++i) {
            if (mediaToShare[i]) {
                Fragment fragment = getFragment(i);
                if (fragment != null) {
                    currentIndex = i + 1;
                    doneTrue();
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).commitAllowingStateLoss();
                }
            }
        }
        if (currentIndex == 0) {
            toastMaker("Sve je spremno za deljenje!");
        }
        if (!done) {
            currentIndex = 0;
            readWriteClient.save(user.read());
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, shareFragment).commitAllowingStateLoss();
            shareFragment.setButtonStates(user.getClicked());
        }
    }

    private void doneTrue() {
        done = true;
    }

    private void getLastMediaFragment() {
        if (currentIndex == 0 && newUser) {
            currentIndex = -1;
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, helloFragment).commitAllowingStateLoss();
        } else if (currentIndex != 0) {
            for (int i = currentIndex - 1; i > 0; --i) {
                if (mediaToShare[i]) {
                    currentIndex = i;
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, getEditFragment(i)).commitAllowingStateLoss();
                }
            }
            if (currentIndex == 1) {
                currentIndex = 0;
                getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, shareFragment).commitAllowingStateLoss();
                shareFragment.setButtonStates(user.getClicked());
            }
        }
    }

    private Fragment getFragment(int index) {
        switch (index) { //OSNOVA INDEXA SE NALAZI U MEDIA TYPE
            case 0:
            case 3:
                if (user.getPhoneNumber().equals("")) { //PHONE NUMBER
                    inputPhoneNumberFragment.setEdit(false);
                    return inputPhoneNumberFragment;
                }
                else return null;
            case 1:
                if (user.getEmail().equals("")) { //EMAIL
                    inputEmailFragment.setEdit(false);
                    return inputEmailFragment;
                }
                else return null;
            case 2:
                if (user.getSkypeId().equals("")) { //SKYPE
                    inputSkypeFragment.setEdit(false);
                    return inputSkypeFragment;
                }
                else return null;

            case 4:
                if (user.getTwitterId().equals("")) { //TWITTER
                    return inputTwitterFragment;
                } else return null;

            case 5:
                if (user.getFacebookId().equals(""))  //FACEBOOK
                    return inputFacebookFragment;
                else return null;

            case 6:
                return inputInstagramFragment;  //INSTAGRAM

            default:
                readWriteClient.save(user.read());
                return allDoneFragment;
        }
    }

    private Fragment getEditFragment(int index) {
        switch (index) {
            case 0:
            case 4:
                return inputPhoneNumberFragment;  //phone i whatsApp
            case 1:
                return inputEmailFragment;  //email
            case 2:
                return inputEmailFragment;  //skype
            case 5:
                return inputTwitterFragment;    //twitter
            case 6:
                return inputFacebookFragment; //facebook
            case 7:
                return inputInstagramFragment;  //INSTAGRAM
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
    public PictureTransform getPictureTransform() {
        return pictureTransform;
    }

    @Override
    public void nextAllDone() {
        readWriteClient.save(user.read());
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, this.shareFragment).commitAllowingStateLoss();
        shareFragment.setButtonStates(user.getClicked());
    }

    @Override
    public void nextHello() {
        currentIndex = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, this.shareFragment).commitAllowingStateLoss();
    }

    @Override
    public void emptyField(String text) {
        toastMaker(text);
    }


    @Override
    public void nextShare(boolean[] mediaToShare) {
        this.mediaToShare = mediaToShare;
        getNextMediaFragment();
    }

    @Override
    public void setTwitterUserID(String userID) {
        user.setTwitterId(userID);
    }

    @Override
    public void nextFragment(boolean edit) {
        if(edit) {
            currentIndex = 1;
            getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, profileFragment).commitAllowingStateLoss();
        }
        else
            getNextMediaFragment();
    }

    @Override
    public void nextFragment() {
        getNextMediaFragment();
    }

    @Override
    public void setTwitterUserName(String userName) {
        user.setTwitterUserName(userName);
    }

    public void openIntent(Intent intent) {
        startActivity(intent);
    }


    @Override
    public void addContact(ArrayList<ContentProviderOperation> operations) {
        try {
            this.getApplicationContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (Exception e) {
        }
    }

    @Override
    public void userSaved() {
        disconnectWIFI();
//        toastMaker("TRANSFER COMPLETE");
    }

    @Override
    public void saveFailed() {
        disconnectWIFI();
//        toastMaker("TRANSFER FAILED");
    }

    @Override
    public void onInvitationSend(boolean sent) {

    }

    @Override
    public void onUserReceived(String info) {
        sharedUser.save(info);
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
    public void openIntent(Intent intent) {
        startActivity(intent);
    }


    @Override
    public void getUserName(String name) {
        viewModel.setText(name + ", sta zelis da delis?");
    }


    @Override
    public Activity getMainActivity() {
        return this;
    }

    @Override
    public InstagramRequest.SetInstagramUserCallback getInstagramUserCallback() {
        return this;
    }

    @Override
    public void clickBack() {
        getLastMediaFragment();
    }

    @Override
    public void setUsername(String username) {
        user.setInstagramUsername(username);
    }

    @Override
    public void setAccesToken(AccessToken accesToken) {

    }

    @Override
    public void emailClick() {
        currentIndex = -2;
        inputEmailFragment.setEdit(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, this.inputEmailFragment).commitAllowingStateLoss();
    }

    @Override
    public void numberClick() {
        currentIndex = -2;
        inputPhoneNumberFragment.setEdit(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, this.inputPhoneNumberFragment).commitAllowingStateLoss();
    }

    @Override
    public void skypeClick() {
        currentIndex = -2;
        inputSkypeFragment.setEdit(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, this.inputSkypeFragment).commitAllowingStateLoss();
    }

    @Override
    public void instagramClick() {
        currentIndex = -2;
        inputInstagramFragment.setEdit(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, this.inputInstagramFragment).commitAllowingStateLoss();
    }
}