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
    private MutableLiveData<Boolean> whatsApp;
    private MutableLiveData<String> skypeId;
    private MutableLiveData<Boolean> twitter;
    private MutableLiveData<String> facebookId;
    private MutableLiveData<String> instagramId;

    private MutableLiveData<String> ACTVIE;
    private MutableLiveData<String> NOT_ACTIVE;

    private Callback callback;

    public ProfileViewModel(){
        name = new MutableLiveData<>();
        picture = new MutableLiveData<>();
        phoneNumber = new MutableLiveData<>();
        email = new MutableLiveData<>();
        skypeId = new MutableLiveData<>();
        twitter = new MutableLiveData<>();
        facebookId = new MutableLiveData<>();
        instagramId = new MutableLiveData<>();
        whatsApp = new MutableLiveData<>();
        ACTVIE = new MutableLiveData<>();
        NOT_ACTIVE = new MutableLiveData<>();
        ACTVIE.setValue("AKTIVNO");
        NOT_ACTIVE.setValue("NIJE_AKTIVNO");
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
        whatsApp.setValue(user.getWhatsApp());
        twitter.setValue(user.getTwitter());
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

    public void whatsAppClick(){
        whatsApp.setValue(!whatsApp.getValue());
        callback.whatsAppClick();
    }

    public void twitterClick(){
        twitter.setValue(!twitter.getValue());
        callback.twitterClick();
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

    public LiveData<Boolean> getTwitter() {
        return twitter;
    }

    public LiveData<Boolean> getWhatsApp(){
        return whatsApp;
    }

    public LiveData<String> getActive(){
        return ACTVIE;
    }

    public LiveData<String> getNotActive(){
        return NOT_ACTIVE;
    }

    public interface Callback{
        void changePicture();
        void emailClick();
        void numberClick();
        void skypeClick();
        void instagramClick();
        void whatsAppClick();
        void twitterClick();
    }

}