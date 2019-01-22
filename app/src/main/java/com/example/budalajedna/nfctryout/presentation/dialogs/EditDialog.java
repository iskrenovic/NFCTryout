package com.example.budalajedna.nfctryout.presentation.dialogs;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;

public class EditDialog extends DialogFragment {

    private Callback callback;

    public EditDialog(){

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback{

    }
}
