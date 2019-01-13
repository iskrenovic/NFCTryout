package com.example.budalajedna.nfctryout.presentation.share;

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
import com.example.budalajedna.nfctryout.databinding.FragmentShareNewBinding;

public class NewShareFragment extends Fragment {

    FragmentShareNewBinding binding;
    NewShareViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_share_new,container,false);

        View view  = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(NewShareViewModel.class);

        binding.setVm(this.viewModel);

        return view;
    }
}
