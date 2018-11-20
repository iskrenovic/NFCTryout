package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentIcontactBinding;

public class InputContactFragment extends Fragment {

    private FragmentIcontactBinding binding;
    private InputContactViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_icontact,container,false);

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(InputContactViewModel.class);

        viewModel.setFragment(this);

        binding.setVm(this.viewModel);

        return this.binding.getRoot();
    }
}
