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
import android.widget.Button;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentInstagramBinding;

public class InputInstagramFragment extends Fragment implements InputInstagramViewModel.InstagramFragmentCallback {

    private FragmentInstagramBinding binding;
    private InputInstagramViewModel viewModel;
    private Button instagramLogin;
    private InstagramCallback instagramCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_itwitter,container,false);

        View view = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(InputInstagramViewModel.class);
        viewModel.setInstagramFragmentCallback(this);

        binding.setVm(this.viewModel);

        return  view;
    }

    public void setInstagramCallback(InstagramCallback instagramCallback){
        this.instagramCallback=instagramCallback;
    }

    @Override
    public void onClickLogin() {
        InstagramAuthDialog authenticationDialog = new InstagramAuthDialog(instagramCallback.getMainActivity());
        authenticationDialog.setCancelable(true);
        authenticationDialog.show();
    }

    public interface InstagramCallback{
        Activity getMainActivity();
    }
}
