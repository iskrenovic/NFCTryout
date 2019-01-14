package com.example.budalajedna.nfctryout.presentation.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> text;

    private Callback callback;

    public MainViewModel(){
        text = new MutableLiveData<>();
        text.setValue("Dobrodosao u HandShake!");
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public LiveData<String> getText() {
        return text;
    }

    public void clickBack(){
        callback.clickBack();
    }

    public void setText(String text) {
        this.text.setValue(text);
    }

    public interface Callback{
        void clickBack();
    }
}
