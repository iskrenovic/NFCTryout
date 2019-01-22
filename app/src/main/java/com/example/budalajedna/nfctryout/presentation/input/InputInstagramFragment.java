package com.example.budalajedna.nfctryout.presentation.input;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentInstagramBinding;
import com.example.budalajedna.nfctryout.datahandling.Instagram;
import com.example.budalajedna.nfctryout.datahandling.InstagramRequest;
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

public class InputInstagramFragment extends Fragment implements InputInstagramViewModel.Callback,InstagramAuthDialog.AuthenticationListener {

    private FragmentInstagramBinding binding;
    private InputInstagramViewModel viewModel;
    private InstagramCallback callback;
    private Instagram instagram;

    private MainCallback mainCallback;

    private boolean edit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instagram,container,false);

        View view = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(InputInstagramViewModel.class);
        viewModel.setCallback(this);

        binding.setVm(this.viewModel);

        return  view;
    }

    public void setCallback(InstagramCallback callback, MainCallback mainCallback){
        this.callback = callback;
        this.mainCallback = mainCallback;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    @Override
    public void onClickLogin() {
        InstagramAuthDialog authenticationDialog = new InstagramAuthDialog(callback.getMainActivity(),this);
        authenticationDialog.setCancelable(true);
        authenticationDialog.show();
    }

    @Override
    public void nextFragment(String username) {
        mainCallback.getUser().setInstagramUsername(username);
        callback.nextFragment(edit);
    }

    @Override
    public void onTokenReceived(String auth_token) {
        instagram=new Instagram(callback.getMainActivity(), callback.getInstagramUserCallback());
        instagram.onTokenReceived(auth_token);
    }

    public Instagram getInstagram(){return this.instagram;}

    public interface InstagramCallback{
        Activity getMainActivity();
        InstagramRequest.SetInstagramUserCallback getInstagramUserCallback();
        void nextFragment(boolean edit);
    }
}
