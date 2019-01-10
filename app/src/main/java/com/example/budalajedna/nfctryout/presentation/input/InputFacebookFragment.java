package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentFacebookBinding;
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

public class InputFacebookFragment extends Fragment{

    private FragmentFacebookBinding binding;
    private InputFacebookViewModel viewModel;

    private CallbackManager callbackManager;
    private MainCallback mainCallback;
    private Callback callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(mainCallback.getActivity());
        callbackManager = CallbackManager.Factory.create();

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_facebook,container,false);

        View view = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(InputFacebookViewModel.class);

        binding.setVm(this.viewModel);

        return view;
    }

    public void setCallback(MainCallback mainCallback, Callback callback){
        this.mainCallback = mainCallback;
        this.callback = callback;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public interface Callback{
        void setAccesToken(AccessToken accesToken);
    }

}
