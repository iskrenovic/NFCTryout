package com.example.budalajedna.nfctryout.connection;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.widget.Toast;

public class NFCManager implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback{

    private NfcAdapter nfcAdapter;
    private Activity activity;
    private static final String messageToSend = "Bravo legendo!";

    public NFCManager(Activity activity) {
        this.activity = activity;
        nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        if (nfcAdapter == null) {
            Toast.makeText(activity, "No NFC supported on this device.", Toast.LENGTH_SHORT).show();
        } else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(activity, "Please enable NFC via Settings.", Toast.LENGTH_SHORT).show();
        }
        nfcAdapter.setNdefPushMessageCallback(this,activity);
        nfcAdapter.setOnNdefPushCompleteCallback(this,activity);
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

        byte[] bytesOut = messageToSend.getBytes();

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
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(),
                        eventString,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}