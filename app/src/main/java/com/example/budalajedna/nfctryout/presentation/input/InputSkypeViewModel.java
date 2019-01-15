package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.Editable;

public class InputSkypeViewModel extends ViewModel {

    private MutableLiveData<String> username;
    private Callback callback;

    public InputSkypeViewModel(){
        username = new MutableLiveData<>();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
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

    public void nextFragment(){
        if(username.getValue()!=null){
            callback.nextFragment(username.getValue());
        }

    }

    public LiveData<String> getUsername() {
        return username;
    }

    public interface Callback{
        void nextFragment(String username);
    }
}
