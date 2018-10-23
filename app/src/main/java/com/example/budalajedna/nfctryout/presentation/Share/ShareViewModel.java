package com.example.budalajedna.nfctryout.presentation.Share;

import android.arch.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    Callback callback;
    public void startClick(){
        callback.startClick();
    }

    public void sendClick(){
        callback.sendClick();
    }

    public interface Callback{
        void startClick();
        void sendClick();
    }

}
