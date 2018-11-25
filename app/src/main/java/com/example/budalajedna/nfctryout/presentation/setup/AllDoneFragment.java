package com.example.budalajedna.nfctryout.presentation.setup;

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
import com.example.budalajedna.nfctryout.databinding.FragmentAlldoneBinding;

public class AllDoneFragment extends Fragment implements AllDoneViewModel.callback{

    FragmentAlldoneBinding binding;
    AllDoneViewModel viewModel;

    Callback Callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_alldone,container,false);

        View view = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this).get(AllDoneViewModel.class);

        binding.setVm(this.viewModel);

        return view;
    }

    public void setCallbacks(Callback Callback){
        this.Callback = Callback;
    }

    @Override
    public void onClickButton() {
        Callback.nextAllDone();
    }

    public interface Callback {
        void nextAllDone();
    }
}
