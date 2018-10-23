package com.example.budalajedna.nfctryout.presentation.share;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import com.example.budalajedna.nfctryout.R;


public class ShareViewModel extends ViewModel {

    private ShareFragment fragment;

    private MutableLiveData<Drawable> btOn;
    private MutableLiveData<Drawable> btOff;

    private MutableLiveData<Drawable> srcFacebookOn;
    private MutableLiveData<Drawable> srcFacebookOff;
    private MutableLiveData<Boolean> facebook;

    private MutableLiveData<Drawable> srcInstagramOn;
    private MutableLiveData<Drawable> srcInstagramOff;
    private MutableLiveData<Boolean>  instagram;

    private MutableLiveData<Drawable> srcContactOn;
    private MutableLiveData<Drawable> srcContactOff;
    private MutableLiveData<Boolean>  contact;

    public ShareViewModel(){

        btOn = new MutableLiveData<>();
        btOff = new MutableLiveData<>();
        
        srcFacebookOn = new MutableLiveData<>();
        srcFacebookOff = new MutableLiveData<>();
        facebook = new MutableLiveData<>();
        facebook.setValue(false);

        srcInstagramOn = new MutableLiveData<>();
        srcInstagramOff = new MutableLiveData<>();
        instagram = new MutableLiveData<>();
        instagram.setValue(false);

        srcContactOn = new MutableLiveData<>();
        srcContactOff = new MutableLiveData<>();
        contact = new MutableLiveData<>();
        contact.setValue(false);
    }

    public LiveData<Drawable> getBtOn(){
        return btOn;
    }

    public LiveData<Drawable> getBtOff(){
        return btOff;
    }

    /*FACEBOOK*/

    public LiveData<Boolean> getFacebook(){
        return facebook;
    }

    public LiveData<Drawable> getSrcFacebookOff(){
        return srcFacebookOff;
    }

    public LiveData<Drawable> getSrcFacebookOn() {
        return srcFacebookOn;
    }

    /*INSTAGRAM*/

    public LiveData<Drawable> getSrcInstagramOn() {
        return srcInstagramOn;
    }

    public LiveData<Drawable> getSrcInstagramOff() {
        return srcInstagramOff;
    }

    public LiveData<Boolean> getInstagram() {
        return instagram;
    }


    /*CONTACT*/

    public LiveData<Drawable> getSrcContactOn() {
        return srcContactOn;
    }

    public LiveData<Drawable> getSrcContactOff() {
        return srcContactOff;
    }

    public LiveData<Boolean> getContact() {
        return contact;
    }

    public void setFragment(ShareFragment fragment) {
        this.fragment = fragment;
        prepareIcons();
    }

    private void prepareIcons(){

        btOn.setValue(fragment.getResources().getDrawable(R.drawable.custom_button_click));
        btOff.setValue(fragment.getResources().getDrawable(R.drawable.custom_button));

        srcFacebookOn.setValue(fragment.getResources().getDrawable(R.drawable.ic_flogo_color));
        srcFacebookOff.setValue(fragment.getResources().getDrawable(R.drawable.ic_flogo_white));

        srcInstagramOn.setValue(fragment.getResources().getDrawable(R.drawable.ic_instagram_color));
        srcInstagramOff.setValue(fragment.getResources().getDrawable(R.drawable.ic_instagram_white));

        srcContactOn.setValue(fragment.getResources().getDrawable(R.drawable.ic_phone_color));
        srcContactOff.setValue(fragment.getResources().getDrawable(R.drawable.ic_phone_white));
    }

    public void facebookClick(){

        /*if(facebook.getValue()){
            facebook.setValue(false);
        }
        else {
            facebook.setValue(true);
        }*/

        facebook.setValue(!facebook.getValue());
    }

    public void instagramClick(){
        instagram.setValue(!instagram.getValue());
    }

    public void contactClick(){
        contact.setValue(!contact.getValue());
    }

    public interface Callback{
        void startClick();
        void sendClick();
    }

}
