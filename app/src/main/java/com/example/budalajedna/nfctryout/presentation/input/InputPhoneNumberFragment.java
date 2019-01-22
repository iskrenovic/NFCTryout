package com.example.budalajedna.nfctryout.presentation.input;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentIcontactBinding;
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;


public class InputPhoneNumberFragment extends Fragment implements InputPhoneNumberViewModel.Callback{

    private FragmentIcontactBinding binding;
    private InputPhoneNumberViewModel viewModel;

    private Callback callback;
    private MainCallback mainCallback;

    private boolean edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_icontact,container,false);

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(InputPhoneNumberViewModel.class);

        viewModel.setCallback(this);

        if(edit) viewModel.setPhoneNumber(mainCallback.getUser().getPhoneNumber());

        binding.setVm(this.viewModel);

        return this.binding.getRoot();
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setCallbacks(Callback callback, MainCallback mainCallback){
        this.mainCallback = mainCallback;
        this.callback = callback;
    }

    @Override
    public void nextFragment(String phoneNumber) {
        if(phoneNumber!=null) {
            mainCallback.getUser().setPhoneNumber(phoneNumber);
            callback.nextFragment(edit);
        }
    }

    public interface Callback{
        void nextFragment(boolean edit);
    }
}
