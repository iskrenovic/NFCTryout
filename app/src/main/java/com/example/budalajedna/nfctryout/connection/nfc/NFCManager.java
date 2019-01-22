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
    private MainCallback mainCallback;

    private Callback callback;

    public NFCManager(MainCallback mainCallback, Callback callback) {
        this.mainCallback = mainCallback;
        this.callback = callback;
        nfcAdapter = NfcAdapter.getDefaultAdapter(mainCallback.getActivity());
        if (nfcAdapter == null) {
            callback.noNFC();
        } else if (!nfcAdapter.isEnabled()) {
            callback.nfcNotON();
        }
        else {
            nfcAdapter.setNdefPushMessageCallback(this, mainCallback.getActivity());
            nfcAdapter.setOnNdefPushCompleteCallback(this, mainCallback.getActivity());
        }
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

    }

    public interface Callback{
        void nfcNotON();
        void noNFC();
    }
}