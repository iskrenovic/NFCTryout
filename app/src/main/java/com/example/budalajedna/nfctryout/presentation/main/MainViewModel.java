package com.example.budalajedna.nfctryout.presentation.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> text;

    public MainViewModel(){
        text = new MutableLiveData<>();
        text.setValue("Dobrodosao u HandShake!");
    }

    public LiveData<String> getText() {
        return text;
    }

    public void setText(String text) {
        this.text.setValue(text);
    }
}
