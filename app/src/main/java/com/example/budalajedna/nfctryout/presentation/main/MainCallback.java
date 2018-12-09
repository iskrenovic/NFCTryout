package com.example.budalajedna.nfctryout.presentation.main;


import android.app.Activity;

import com.example.budalajedna.nfctryout.datahandling.ReadWriteClient;
import com.example.budalajedna.nfctryout.datahandling.User;

public interface MainCallback {
    User getUser();
    Activity getActivity();
    ReadWriteClient getReadWriteClient();
}
