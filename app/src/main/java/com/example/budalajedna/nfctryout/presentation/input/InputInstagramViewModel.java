package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.ViewModel;

public class InputInstagramViewModel extends ViewModel {

    private InstagramFragmentCallback instagramFragmentCallback;
    public void login(){
        instagramFragmentCallback.onClickLogin();
    }
    public void setInstagramFragmentCallback(InstagramFragmentCallback instagramFragmentCallback){
        this.instagramFragmentCallback=instagramFragmentCallback;
    }
    public interface InstagramFragmentCallback{
        void onClickLogin();
    }
}
