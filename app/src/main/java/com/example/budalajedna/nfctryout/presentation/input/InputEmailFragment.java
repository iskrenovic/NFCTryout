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
import com.example.budalajedna.nfctryout.databinding.FragmentIemailBinding;
import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

public class InputEmailFragment extends Fragment implements InputEmailViewModel.callback {

    private FragmentIemailBinding binding;
    private InputEmailViewModel viewModel;

    private boolean edit;

    private MainCallback mainCallback;
    private callback callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_iemail,container,false);

        View view = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(InputEmailViewModel.class);

        viewModel.setCallback(this);

        if(edit) viewModel.setEmail(mainCallback.getUser().getEmail());

        binding.setVm(this.viewModel);

        return view;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setCallbacks(MainCallback userCallback, callback callback){
        this.mainCallback = userCallback;
        this.callback = callback;
    }

    @Override
    public void nextFragment(String email) {
        mainCallback.getUser().setEmail(email);
        callback.nextFragment(edit);

    }
    public interface callback{
        void nextFragment(boolean edit);
    }
}
