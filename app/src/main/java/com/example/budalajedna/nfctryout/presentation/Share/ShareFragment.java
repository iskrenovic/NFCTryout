package com.example.budalajedna.nfctryout.presentation.share;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budalajedna.nfctryout.R;
import com.example.budalajedna.nfctryout.databinding.FragmentShareBinding;

public class ShareFragment extends Fragment implements ShareViewModel.Callback{

    private FragmentShareBinding binding;
    private ShareViewModel shareViewModel;

    private Callback callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_share,container,false);

        View view  = this.binding.getRoot();

        binding.setLifecycleOwner(this);

        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);

        shareViewModel.setFragment(this);

        binding.setVm(this.shareViewModel);

        return view;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void proceed(boolean[] mediaToShare) {
        callback.nextShare(mediaToShare);
    }

    public interface Callback{
        void nextShare(boolean[] mediaToShare);
    }
}
