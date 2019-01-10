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
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;
import com.example.budalajedna.nfctryout.presentation.main.MediaType;

public class ShareFragment extends Fragment implements ShareViewModel.Callback{

    private FragmentShareBinding binding;
    private ShareViewModel shareViewModel;


    private MainCallback mainCallback;
    private Callback callback;

    private boolean[] buttonStates;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_share,container,false);

        View view  = this.binding.getRoot();

        int i = 0;

        binding.setLifecycleOwner(this);

        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);

        shareViewModel.setFragment(this);

        binding.setVm(this.shareViewModel);

        if(buttonStates!=null) setButtonClicked();

        return view;
    }

    public void setCallback(Callback callback, MainCallback mainCallback) {
        this.mainCallback = mainCallback;
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
    public void phoneClick() {
        mainCallback.getUser().clickPhone();
    }

    @Override
    public void emailClick() {
        mainCallback.getUser().clickEmail();
    }

    @Override
    public void facebookClick() {
        mainCallback.getUser().clickFacebook();
    }

    @Override
    public void twitterClick() {
        mainCallback.getUser().clickTwitter();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("FRAGMENT", "onResultCalled");
    }


    public interface Callback{
        void nextShare(boolean[] mediaToShare);
    }

}
