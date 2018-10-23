package com.example.budalajedna.nfctryout.presentation.Share;

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

public class ShareFragment extends Fragment {

    private ShareFragmentBinding binding;
    private ShareViewModel shareViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding=(ShareFragmentBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_share,container,false);
        View view  =this.binding.getRoot();
        this.binding.setLifecycleOwner(this);
        this.shareViewModel = (ShareViewModel) ViewModelProviders.of((Fragment) this).get(ShareViewModel.class);
        this.binding.setVm(this.shareViewModel);



        return view;

    }
}
