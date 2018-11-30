package com.example.budalajedna.nfctryout.presentation.setup;

import android.arch.lifecycle.ViewModel;

public class AllDoneViewModel extends ViewModel {

    private callback callback;

    public void setCallback(callback callback) {
        this.callback = callback;
    }

    public void saveUser(){
        callback.onClickButton();
    }

    public interface callback{
        void onClickButton();
    }

}
