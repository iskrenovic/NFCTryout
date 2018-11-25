package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.ViewModel;

public class InputPhoneNumberViewModel extends ViewModel {

    Callback callback;

    public InputPhoneNumberViewModel() {

    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    public void nextFragment(){
        callback.nextFragment();
    }

    public interface Callback{
        void nextFragment();
    }
}
