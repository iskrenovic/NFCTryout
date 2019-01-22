package com.example.budalajedna.nfctryout.presentation.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.datahandling.PictureTransform;
import com.example.budalajedna.nfctryout.datahandling.User;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<Drawable> picture;
    private MutableLiveData<String> phoneNumber;
    private MutableLiveData<String> email;
    private MutableLiveData<String> skypeId;
    private MutableLiveData<String> twitterId;
    private MutableLiveData<String> facebookId;
    private MutableLiveData<String> instagramId;

    private Callback callback;

    public ProfileViewModel(){
        name = new MutableLiveData<>();
        picture = new MutableLiveData<>();
        phoneNumber = new MutableLiveData<>();
        email = new MutableLiveData<>();
        skypeId = new MutableLiveData<>();
        twitterId = new MutableLiveData<>();
        facebookId = new MutableLiveData<>();
        instagramId = new MutableLiveData<>();
    }

    public void init(Resources resources, User user, PictureTransform pictureTransform){
        name.setValue(user.getName());
        if(user.getProfilePicture().equals(""))
            picture.setValue(resources.getDrawable(R.drawable.ic_profile));
        else
            picture.setValue(new BitmapDrawable(resources,pictureTransform.decodeBitmapString(user.getProfilePicture())));
        phoneNumber.setValue(user.getPhoneNumber());
        email.setValue(user.getEmail());
        skypeId.setValue(user.getSkypeId());
        twitterId.setValue(user.getTwitterId());
        facebookId.setValue(user.getFacebookId());
        instagramId.setValue("@" + user.getInstagramUsername());
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setPicture(Drawable picture) {
        this.picture.setValue(picture);
    }

    public void emailClick(){
        callback.emailClick();
    }

    public void numberClick(){
        callback.numberClick();
    }

    public void skypeClick(){
        callback.skypeClick();
    }

    public void instagramClick(){ callback.instagramClick();}

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

    public void selectProfilePicture(){
        callback.changePicture();
    }

    public LiveData<Drawable> getPicture() {
        return picture;
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getFacebookId() {
        return facebookId;
    }

    public LiveData<String> getInstagramId() {
        return instagramId;
    }

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public LiveData<String> getSkypeId() {
        return skypeId;
    }

    public LiveData<String> getTwitterId() {
        return twitterId;
    }

    public interface Callback{
        void changePicture();
        void emailClick();
        void numberClick();
        void skypeClick();
        void instagramClick();
    }

}