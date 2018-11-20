package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.ViewModel;

public class InputContactViewModel extends ViewModel {

    private InputContactFragment fragment;

    public InputContactViewModel(){

    }

    public void setFragment(InputContactFragment fragment) {
        this.fragment = fragment;
    }
}
