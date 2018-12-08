package com.example.budalajedna.nfctryout.presentation;


import com.example.budalajedna.nfctryout.datahandling.ReadWriteClient;
import com.example.budalajedna.nfctryout.datahandling.User;

public interface MainCallback {
    User getUser();
    ReadWriteClient getReadWriteClient();

}
