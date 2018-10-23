package com.example.budalajedna.nfctryout.presentation.Welcome;

import android.arch.lifecycle.ViewModel;

public class ViewModelWelcome extends ViewModel {
    FragmentCallback fragmentCallback;

    public void nextClick(){fragmentCallback.nextStep();}

    public interface FragmentCallback{
        void nextStep();
    }
}
