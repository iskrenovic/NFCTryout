package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.ViewModel;

public class InputViewModel extends ViewModel {

    private InputFragment fragment;

    public InputViewModel(){

    }

    public void setFragment(InputFragment fragment) {
        this.fragment = fragment;
    }
}
