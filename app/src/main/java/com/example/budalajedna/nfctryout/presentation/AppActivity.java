package com.example.budalajedna.nfctryout.presentation;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.connection.NFCManager;
import com.example.budalajedna.nfctryout.presentation.share.ShareFragment;
import com.example.budalajedna.nfctryout.presentation.share.ShareViewModel;


public class AppActivity extends AppCompatActivity implements ShareViewModel.Callback, IActivityCallback{

    private NFCManager nfcManager;
    private ShareFragment shareFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.app_activity);

        shareFragment = new ShareFragment();

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

}
