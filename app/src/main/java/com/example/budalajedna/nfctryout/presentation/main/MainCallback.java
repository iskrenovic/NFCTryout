package com.example.budalajedna.nfctryout.presentation.main;


import com.example.budalajedna.nfctryout.datahandling.ReadWriteClient;
import com.example.budalajedna.nfctryout.datahandling.User;

public interface MainCallback {
    User getUser();
    String getDeviceAdress();
    AppActivity getActivity();
    ReadWriteClient getReadWriteClient();
}
