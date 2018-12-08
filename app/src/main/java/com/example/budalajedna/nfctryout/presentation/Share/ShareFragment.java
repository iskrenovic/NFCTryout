package com.example.budalajedna.nfctryout.presentation.share;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentShareBinding;
import com.example.budalajedna.nfctryout.presentation.MediaType;

public class ShareFragment extends Fragment implements ShareViewModel.Callback{

    private FragmentShareBinding binding;
    private ShareViewModel shareViewModel;

    private Callback callback;

    private boolean[] buttonStates;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_share,container,false);

        View view  = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);

        shareViewModel.setFragment(this);

        binding.setVm(this.shareViewModel);

        if(buttonStates!=null) setButtonClicked();

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
        if(hasChanged(mediaToShare)) callback.nextShare(mediaToShare);
    }

    public interface Callback{
        void nextShare(boolean[] mediaToShare);
    }
}
