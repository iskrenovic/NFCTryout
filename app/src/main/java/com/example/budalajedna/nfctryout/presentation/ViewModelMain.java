package com.example.budalajedna.nfctryout.presentation;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.drawable.Drawable;

import com.example.budalajedna.nfctryout.R;


public class ViewModelMain extends ViewModel {

    private Activity activity;

    private MutableLiveData<Drawable> btOn;
    private MutableLiveData<Drawable> btOff;

    private MutableLiveData<Drawable> srcFacebookOn;
    private MutableLiveData<Drawable> srcFacebookOff;

    private MutableLiveData<Boolean> facebook;

    public ViewModelMain(){

        btOn = new MutableLiveData<>();
        btOff = new MutableLiveData<>();
        
        srcFacebookOn = new MutableLiveData<>();
        srcFacebookOff = new MutableLiveData<>();

        facebook = new MutableLiveData<>();
        facebook.setValue(false);
    }

    public LiveData<Drawable> getBtOn(){
        return btOn;
    }

    public LiveData<Drawable> getBtOff(){
        return btOff;
    }

    public LiveData<Boolean> getFacebook(){
        return facebook;
    }

    public LiveData<Drawable> getSrcFacebookOff(){
        return srcFacebookOff;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        prepareIcons();
    }

    private void prepareIcons(){

        btOn.setValue(activity.getResources().getDrawable(R.drawable.custom_button_click));
        btOff.setValue(activity.getResources().getDrawable(R.drawable.custom_button));

        srcFacebookOn.setValue(activity.getResources().getDrawable(R.drawable.ic_flogo_color));
        srcFacebookOff.setValue(activity.getResources().getDrawable(R.drawable.ic_flogo_white));
    }

    public LiveData<Drawable> getSrcFacebookOn() {
        return srcFacebookOn;
    }

    public void setSrcFacebookOn(Drawable drawable){
        srcFacebookOn.setValue(drawable);
    }

    public void facebookClick(){
        /*facebook = !facebook;
        if(facebook){
            test.setValue(0);
            setBtFacebook(getButtonClick());
            setSrcFacebookOn(facebookSrc);
        }
        else{
            test.setValue(1);
            btFacebook.setValue(getButton());
            setSrcFacebookOn(activity.getResources().getDrawable(R.drawable.ic_flogo_white));
        }*/
        if(facebook.getValue()){
            facebook.setValue(false);
        }
        else {
            facebook.setValue(true);
        }

    }

    public interface Callback{
        void startClick();
        void sendClick();
    }

}
