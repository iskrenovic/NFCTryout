package com.example.budalajedna.nfctryout.presentation;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.connection.NFCManager;
import com.example.budalajedna.nfctryout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private NFCManager nfcManager;
    private ViewModelMain viewModelMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        viewModelMain = ViewModelProviders.of(this).get(ViewModelMain.class);

        nfcManager = new NFCManager(this);
    }

    @Override
    protected void onResume() {

        Intent intent = getIntent();
        String action = intent.getAction();
        if(action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            toastMaker(nfcManager.getTextFromBeam(intent));
        }
        super.onResume();
    }

//    @Override
//    public void startClick(View view) {
//
//    }
//
//    @Override
//    public void sendClick(View view) {
//
//    }

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

}
