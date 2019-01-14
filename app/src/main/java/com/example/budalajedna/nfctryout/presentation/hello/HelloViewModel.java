package com.example.budalajedna.nfctryout.presentation.hello;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Editable;

import com.example.budalajedna.nfctryout.R;

public class HelloViewModel extends ViewModel {

    private Callback callback;

    private MutableLiveData<String> name;
    private MutableLiveData<Drawable> picture;

    public HelloViewModel(){
        name = new MutableLiveData<>();
        picture = new MutableLiveData<>();
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

    public LiveData<Drawable> getPicture() {
        return picture;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void setPicture(Drawable picture) {
        this.picture.setValue(picture);
    }

    public void selectProfilePicture(){
        callback.getProfilePicture();
    }

    public void nextStep(){
        if(name.getValue()!=null) callback.nextStep(name.getValue());
        else callback.emptyName();

    }

    public interface Callback{
        void nextStep(String contactName);
        void emptyName();
        void getProfilePicture();
    }
}
