package com.example.budalajedna.nfctryout.presentation.share;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentShareBinding;
import com.example.budalajedna.nfctryout.presentation.main.MediaType;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class ShareFragment extends Fragment implements ShareViewModel.Callback{

    private FragmentShareBinding binding;
    private ShareViewModel shareViewModel;

    private CallbackManager callbackManager;
    private static final String EMAIL = "email";

    private Callback callback;

    private boolean[] buttonStates;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_share,container,false);

        View view  = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);

        shareViewModel.setFragment(this);

        binding.setVm(this.shareViewModel);

        if(buttonStates!=null) setButtonClicked();



        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                callback.setAccesToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        return view;





    }

    public void setCallback(Callback callback) {

        this.callback = callback;
    }

    public void setButtonStates(boolean[] buttonStates){
        this.buttonStates = buttonStates;
    }

    public void setButtonClicked(){
        if(buttonStates[MediaType.phoneNumber.getValue()]) shareViewModel.contactClick();
        if(buttonStates[MediaType.email.getValue()]) shareViewModel.emailClick();
    }

    private boolean hasChanged(boolean[] mediaToShare){
        int l = mediaToShare.length;
        for (int i = 0; i < l; i++) {
            if(mediaToShare[i] != buttonStates[i]) return true;
        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void proceed(boolean[] mediaToShare) {
        if(buttonStates==null) callback.nextShare(mediaToShare);
        else if(hasChanged(mediaToShare)) callback.nextShare(mediaToShare);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d("FRAGMENT", "onResultCalled");
    }



    public interface Callback{
        void nextShare(boolean[] mediaToShare);
        void setAccesToken(AccessToken accesToken);

    }
}
