package com.example.budalajedna.nfctryout.connection.nfc;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.widget.Toast;

import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

public class NFCManager implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback{

    private NfcAdapter nfcAdapter;
    MainCallback mainCallback;

    public NFCManager(MainCallback mainCallback) {
        this.mainCallback = mainCallback;
        nfcAdapter = NfcAdapter.getDefaultAdapter(mainCallback.getActivity());
        if (nfcAdapter == null) {
            Toast.makeText(mainCallback.getActivity(), "No NFC supported on this device.", Toast.LENGTH_SHORT).show();
        } else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(mainCallback.getActivity(), "Please enable NFC via Settings.", Toast.LENGTH_SHORT).show();
        }
        nfcAdapter.setNdefPushMessageCallback(this,mainCallback.getActivity());
        nfcAdapter.setOnNdefPushCompleteCallback(this,mainCallback.getActivity());
    }


    public String getTextFromBeam(Intent intent){

        Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage inNdefMessage = (NdefMessage)parcelables[0];
        NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
        NdefRecord ndefRecord_0 = inNdefRecords[0];

        return new String(ndefRecord_0.getPayload());
    }


    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {

        byte[] bytesOut = mainCallback.getDeviceAdress().getBytes();

        NdefRecord ndefRecordOut = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA,
                "text/plain".getBytes(),
                new byte[] {},
                bytesOut);

        NdefMessage ndefMessageout = new NdefMessage(ndefRecordOut);
        return ndefMessageout;
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {
        final String eventString = "onNodePushComplete\n" + event.toString();
        mainCallback.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mainCallback.getActivity().getApplicationContext(),
                        eventString,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}