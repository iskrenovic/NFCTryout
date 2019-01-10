package com.example.budalajedna.nfctryout.presentation.share;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import com.example.budalajedna.nfctryout.R;

public class ShareViewModel extends ViewModel {

    private ShareFragment fragment;
    private Callback callback;

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

    private MutableLiveData<Drawable> srcEmailOn;
    private MutableLiveData<Drawable> srcEmailOff;
    private MutableLiveData<Boolean> email;

    private MutableLiveData<Drawable> srcTwitterOn;
    private MutableLiveData<Drawable> srcTwitterOff;
    private MutableLiveData<Boolean> twitter;

    private MutableLiveData<Drawable> srcWhatsAppOn;
    private MutableLiveData<Drawable> srcWhatsAppOff;
    private MutableLiveData<Boolean> whatsApp;

    public ShareViewModel() {

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

        srcEmailOn = new MutableLiveData<>();
        srcEmailOff = new MutableLiveData<>();
        email = new MutableLiveData<>();
        email.setValue(false);

        srcTwitterOn = new MutableLiveData<>();
        srcTwitterOff = new MutableLiveData<>();
        twitter = new MutableLiveData<>();
        twitter.setValue(false);

        srcWhatsAppOn = new MutableLiveData<>();
        srcWhatsAppOff = new MutableLiveData<>();
        whatsApp = new MutableLiveData<>();
        whatsApp.setValue(false);
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


    /*EMAIL*/

    public LiveData<Drawable> getSrcEmailOn() {
        return srcEmailOn;
    }

    public LiveData<Drawable> getSrcEmailOff() {
        return srcEmailOff;
    }

    public LiveData<Boolean> getEmail() {
        return email;
    }

    /*TWITTER*/

    public LiveData<Drawable> getSrcTwitterOn() {
        return srcEmailOn;
    }

    public LiveData<Drawable> getSrcTwitterOff() {
        return srcEmailOff;
    }

    public LiveData<Boolean> getTwitter() {
        return email;
    }



    /*WhatsApp*/

    public LiveData<Drawable> getSrcWhatsAppOn() {
        return srcEmailOn;
    }

    public LiveData<Drawable> getSrcWhatsAppOff() {
        return srcEmailOff;
    }

    public LiveData<Boolean> getWhatsApp() {
        return email;
    }

    public void setFragment(ShareFragment fragment) {
        this.fragment = fragment;
        callback = fragment;
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

        srcEmailOn.setValue(fragment.getResources().getDrawable(R.drawable.ic_email_color));
        srcEmailOff.setValue(fragment.getResources().getDrawable(R.drawable.ic_email_white));

        srcTwitterOn.setValue(fragment.getResources().getDrawable(R.drawable.ic_twitterlogo_color));
        srcTwitterOff.setValue(fragment.getResources().getDrawable(R.drawable.ic_twitterlogo_white));

        srcWhatsAppOn.setValue(fragment.getResources().getDrawable(R.drawable.ic_whatsapplogo_color));
        srcWhatsAppOff.setValue(fragment.getResources().getDrawable(R.drawable.ic_whatsapplogo_white));
    }

    public void facebookClick(){facebook.setValue(!facebook.getValue()); }

    public void instagramClick(){
        instagram.setValue(!instagram.getValue());
    }

    public void contactClick(){

        contact.setValue(!contact.getValue());
        callback.phoneClick();
    }

    public void emailClick(){
        email.setValue(!email.getValue());
        callback.emailClick();
    }

    public void twitterClick(){
        twitter.setValue(!twitter.getValue());
        callback.twitterClick();
    }

    public void whatsAppClick(){whatsApp.setValue(!whatsApp.getValue());}

    public boolean[] getMediaToShare(){
        return new boolean[]{facebook.getValue(), instagram.getValue(), contact.getValue(), email.getValue(), whatsApp.getValue()};
    }

    public void next(){
        callback.proceed(getMediaToShare());
    }

    public interface Callback{
        void proceed(boolean[] mediaToShare);
        void phoneClick();
        void emailClick();
        void twitterClick();
    }


}
