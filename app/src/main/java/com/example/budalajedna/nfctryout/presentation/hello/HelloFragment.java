package com.example.budalajedna.nfctryout.presentation.hello;

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
import com.example.budalajedna.nfctryout.databinding.FragmentHelloBinding;
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;


public class HelloFragment extends Fragment implements HelloViewModel.Callback{

    private FragmentHelloBinding binding;
    private HelloViewModel viewModel;

    private Callback callback;
    private MainCallback mainCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_hello,container,false);

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(HelloViewModel.class);

        viewModel.setCallback(this);

        binding.setVm(this.viewModel);

        return this.binding.getRoot();
    }

    public void setCallbacks(Callback callback, MainCallback mainCallback){
        this.callback = callback;
        this.mainCallback = mainCallback;
    }

    @Override
    public void nextStep(String contactName) {
        mainCallback.getUser().setContactName(contactName);
        callback.nextHello();
    }

    public interface Callback{
        void nextHello();
    }
}
