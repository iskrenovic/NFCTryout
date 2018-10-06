package com.example.budalajedna.nfctryout.connection;

import android.app.Activity;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import java.io.IOException;
import java.nio.charset.Charset;

public class NFCManager {

    private NfcAdapter nfcAdapter;
    private Activity activity;
    private Callback callback;

    public NFCManager(Activity activity) {
        this.activity = activity;
        nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        if (nfcAdapter == null) {
            Toast.makeText(activity, "No NFC supported on this device.", Toast.LENGTH_SHORT).show();
        } else if (!nfcAdapter.isEnabled()) {
            Toast.makeText(activity, "Please enable NFC via Settings.", Toast.LENGTH_SHORT).show();
        }
    }

    //TAKE #2

    public void onNfcDetected(Ndef ndef, String message) {
        writeToNfc(ndef, message);
    }

    public void onNfcDetected(Ndef ndef) {
        readFromNfc(ndef);
    }

    private void writeToNfc(Ndef ndef, String message) {
        if (ndef != null) {
            try {
                ndef.connect();
                NdefRecord record = NdefRecord.createMime("text/plain", message.getBytes(Charset.defaultCharset()));
                ndef.writeNdefMessage(new NdefMessage(record));
                ndef.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFromNfc(Ndef ndef) {
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

    public interface Callback{
        void messageReceived(String message);
    }
}