package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.Editable;

public class InputPhoneNumberViewModel extends ViewModel {

    Callback callback;

    MutableLiveData<String> phoneNumber;

    public InputPhoneNumberViewModel(){
        phoneNumber = new MutableLiveData<>();
    }

    public void textChanged(Editable editable){
        if(phoneNumber.getValue()!=null) {
            if (!phoneNumber.getValue().equals(editable.toString())) {
                phoneNumber.setValue(editable.toString());
            }
        }
        else {
            phoneNumber.setValue(editable.toString());
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setValue(phoneNumber);
    }

    public LiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    public void nextFragment(){
        if(phoneNumber.getValue()!=null) callback.nextFragment(phoneNumber.getValue());
        else callback.emptyPhone();
    }

    public interface Callback{
        void nextFragment(String phoneNumber);
        void emptyPhone();
    }
}
