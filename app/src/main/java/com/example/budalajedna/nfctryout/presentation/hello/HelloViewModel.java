package com.example.budalajedna.nfctryout.presentation.hello;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.Editable;

public class HelloViewModel extends ViewModel {

    Callback callback;

    MutableLiveData<String> name;

    public HelloViewModel(){
        name = new MutableLiveData<>();
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    public void textChanged(Editable editable){
        if(name.getValue()!=null) {
            if (!name.getValue().equals(editable.toString())) {
                name.setValue(editable.toString());
            }
        }
        else {
            name.setValue(editable.toString());
        }
    }

    public LiveData<String> getName() {
        return name;
    }

    public void nextStep(){
        callback.nextStep(name.getValue());
    }

    public interface Callback{
        void nextStep(String contactName);
    }
}
