package com.example.budalajedna.nfctryout.presentation.input;

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
import com.example.budalajedna.nfctryout.databinding.FragmentSkypeBinding;
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

public class InputSkypeFragment extends Fragment implements InputSkypeViewModel.Callback{

    private FragmentSkypeBinding binding;
    private InputSkypeViewModel viewModel;

    private Callback callback;
    private MainCallback mainCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_skype,container,false);
        View view = this.binding.getRoot();
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(InputSkypeViewModel.class);
        viewModel.setCallback(this);
        binding.setVm(this.viewModel);
        return view;
    }

    public void setCallback(Callback callback, MainCallback mainCallback) {
        this.callback = callback;
        this.mainCallback = mainCallback;
    }

    @Override
    public void nextFragment(String username) {
        mainCallback.getUser().setSkypeId(username);
        callback.nextFragment();
    }

    public interface Callback{
        void nextFragment();
    }
}
