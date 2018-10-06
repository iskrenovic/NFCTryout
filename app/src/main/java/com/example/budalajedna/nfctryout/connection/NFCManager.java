package com.example.budalajedna.nfctryout.connection;

import android.app.Activity;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Parcelable;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import java.io.IOException;

public class NFCManager implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback{

    private NfcAdapter nfcAdapter;
    private Activity activity;
    private static final String messageToSend = "Bravo legendo!";
    private Callback callback;

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

    //TAKE #2

//    public void onNfcDetected(Ndef ndef, String message) {
//        writeToNfc(ndef, message);
//    }
//
//    public void onNfcDetected(Ndef ndef) {
//        readFromNfc(ndef);
//    }
//
//    private void writeToNfc(Ndef ndef, String message) {
//        if (ndef != null) {
//            try {
//                ndef.connect();
//                NdefRecord record = NdefRecord.createMime("text/plain", message.getBytes(Charset.defaultCharset()));
//                ndef.writeNdefMessage(new NdefMessage(record));
//                ndef.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (FormatException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
    public void readFromNfc(Ndef ndef) {
        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            String message = new String(ndefMessage.getRecords()[0].getPayload());
            callback.messageReceived(message);
            ndef.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }


    /*TAKE 3*/

    public String ndefDiscovered(Intent intent){
        Parcelable[] parcelables =
                intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        NdefMessage inNdeMessage = (NdefMessage) parcelables[0];
        NdefRecord[] inNdeRecords = inNdeMessage.getRecords();
        NdefRecord ndefRecord_0 = inNdeRecords[0];
        return new String(ndefRecord_0.getPayload());
    }


    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        NdefRecord rtdUriRecord = NdefRecord.createUri(messageToSend);

        NdefMessage ndefMessageOut = new NdefMessage(rtdUriRecord);
        return ndefMessageOut;
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

    public interface Callback{
        void messageReceived(String message);
    }
}