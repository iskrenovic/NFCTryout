package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.Editable;

public class InputInstagramViewModel extends ViewModel {

    private MutableLiveData<String> username;

    private Callback callback;

    public InputInstagramViewModel(){
        username = new MutableLiveData<>();
    }

    public void textChanged(Editable editable){
        if(username.getValue()!=null) {
            if (!username.getValue().equals(editable.toString())) {
                username.setValue(editable.toString());
            }
        }
        else {
            username.setValue(editable.toString());
        }
    }

    public void setUsername(String username) {
        this.username.setValue(username);
    }

    public void nextFragment(){
        if(username.getValue()!=null) {
            if (username.getValue().contains("@"))
                username.setValue(username.getValue().substring(1));
            callback.nextFragment(username.getValue());
        }
    }

    public LiveData<String> getUsername() {
        return username;
    }

    public void login(){
        callback.onClickLogin();
    }
    public void setCallback(Callback callback){
        this.callback = callback;
    }
    public interface Callback {
        void onClickLogin();
        void nextFragment(String username);
    }
}
