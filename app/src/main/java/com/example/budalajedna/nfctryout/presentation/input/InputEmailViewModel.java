package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.Editable;
import android.text.TextWatcher;

public class InputEmailViewModel extends ViewModel {

    private MutableLiveData<String> email;
    private callback callback;

    public InputEmailViewModel(){
        email = new MutableLiveData<>();
    }

    public void setCallback(InputEmailViewModel.callback callback) {
        this.callback = callback;
    }

    public void textChanged(Editable editable){
        if(email.getValue()!=null) {
            if (!email.getValue().equals(editable.toString())) {
                email.setValue(editable.toString());
            }
        }
        else {
            email.setValue(editable.toString());
        }
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public void nextFragment(){
        if(email.getValue()!=null){
            callback.nextFragment(email.getValue());
        }
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public interface callback{
        void nextFragment(String email);
    }
}
