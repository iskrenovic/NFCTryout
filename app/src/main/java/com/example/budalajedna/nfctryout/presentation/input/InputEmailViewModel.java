package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class InputEmailViewModel extends ViewModel {

    private MutableLiveData<String> text;
    private MutableLiveData<String> email;
    private callback callback;

    public InputEmailViewModel(callback callback){

        this.callback = callback;
        text = new MutableLiveData<>();
        email = new MutableLiveData<>();
        text.setValue("Ovde upisati email adresu");
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

    public LiveData<String> getText() {
        return text;
    }

    public interface callback{
        void nextFragment(String email);
    }
}
