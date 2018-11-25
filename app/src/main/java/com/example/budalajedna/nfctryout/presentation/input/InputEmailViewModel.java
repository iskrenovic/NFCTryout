package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class InputEmailViewModel extends ViewModel {

    private MutableLiveData<String> email;
    private callback callback;

    public InputEmailViewModel(){
        email = new MutableLiveData<>();
    }

    public void setCallback(InputEmailViewModel.callback callback) {
        this.callback = callback;
    }

    public void nextFragment(){
        if(email.getValue()!=null){
            callback.nextFragment(email.getValue());
        }
    }

    public void setEmail(String emailText){
        email.setValue(emailText);
    }



    public LiveData<String> getEmail() {
        return email;
    }

    public interface callback{
        void nextFragment(String email);
    }
}
