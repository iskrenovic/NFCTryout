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

    private MutableLiveData<Drawable> srcSkypeOn;
    private MutableLiveData<Drawable> srcSkypeOff;
    private MutableLiveData<Boolean> skype;

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

        srcSkypeOn = new MutableLiveData<>();
        srcSkypeOff = new MutableLiveData<>();
        skype = new MutableLiveData<>();
        skype.setValue(false);
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
        return srcTwitterOn;
    }

    public LiveData<Drawable> getSrcTwitterOff() {
        return srcTwitterOff;
    }

    public LiveData<Boolean> getTwitter() {
        return twitter;
    }


    /*WhatsApp*/

    public LiveData<Drawable> getSrcWhatsAppOn() {
        return srcWhatsAppOn;
    }

    public LiveData<Drawable> getSrcWhatsAppOff() {
        return srcWhatsAppOff;
    }

    public LiveData<Boolean> getWhatsApp() {
        return whatsApp;
    }

    /*Skype*/

    public LiveData<Drawable> getSrcSkypeOn() {
        return srcSkypeOn;
    }

    public LiveData<Drawable> getSrcSkypeOff() {
        return srcSkypeOff;
    }

    public LiveData<Boolean> getSkype() {
        return skype;
    }

    public void setFragment(ShareFragment fragment) {
        this.fragment = fragment;
        callback = fragment;
        prepareIcons();
    }

    private void prepareIcons(){

        btOn.setValue(fragment.getResources().getDrawable(R.drawable.custom_button_click));
        btOff.setValue(fragment.getResources().getDrawable(R.drawable.custom_button));

        srcFacebookOn.setValue(fragment.getResources().getDrawable(R.mipmap.round_facebook));
        srcFacebookOff.setValue(fragment.getResources().getDrawable(R.mipmap.round_facebook_gray));

        srcInstagramOn.setValue(fragment.getResources().getDrawable(R.mipmap.round_instagram));
        srcInstagramOff.setValue(fragment.getResources().getDrawable(R.mipmap.round_instagram_gray));

        srcContactOn.setValue(fragment.getResources().getDrawable(R.mipmap.round_call));
        srcContactOff.setValue(fragment.getResources().getDrawable(R.mipmap.round_call_grey));

        srcEmailOn.setValue(fragment.getResources().getDrawable(R.mipmap.round_email));
        srcEmailOff.setValue(fragment.getResources().getDrawable(R.mipmap.round_email_grey));

        srcTwitterOn.setValue(fragment.getResources().getDrawable(R.mipmap.round_twitter));
        srcTwitterOff.setValue(fragment.getResources().getDrawable(R.mipmap.round_twitter_gray));

        srcWhatsAppOn.setValue(fragment.getResources().getDrawable(R.mipmap.round_whatsapp));
        srcWhatsAppOff.setValue(fragment.getResources().getDrawable(R.mipmap.round_whatsapp_gray));

        srcSkypeOn.setValue(fragment.getResources().getDrawable(R.mipmap.round_skype));
        srcSkypeOff.setValue(fragment.getResources().getDrawable(R.mipmap.round_skype_gray));
    }

    public void facebookClick(){
        facebook.setValue(!facebook.getValue());
        callback.facebookClick();
    }

    public void instagramClick(){
        instagram.setValue(!instagram.getValue());
        callback.instagramClick();
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

    public void whatsAppClick(){
        whatsApp.setValue(!whatsApp.getValue());
        callback.whatsAppClick();
    }

    public void skypeClick(){
        skype.setValue(!skype.getValue());
        callback.skypeClick();
    }

    public boolean[] getMediaToShare(){
        return new boolean[]{contact.getValue(), email.getValue(), skype.getValue(), whatsApp.getValue(), twitter.getValue(), facebook.getValue(), instagram.getValue()};
    }

    public void next(){
        callback.proceed(getMediaToShare());
    }

    public interface Callback{
        void proceed(boolean[] mediaToShare);
        void phoneClick();
        void emailClick();
        void facebookClick();
        void twitterClick();
        void instagramClick();
        void skypeClick();
        void whatsAppClick();
    }
}
