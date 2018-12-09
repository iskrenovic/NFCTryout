package com.example.budalajedna.nfctryout.connection.wifi;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerClass extends Thread implements SendReceive.Callback{

    private static final String TAG = ServerClass.class.getSimpleName();
    private Callback callback;

    public ServerClass(Callback callback) {
        this.callback = callback;
    }

    public void run() {
        super.run();
        try {
            SendReceive sendReceive = new SendReceive(new ServerSocket(8888).accept(), this);
            callback.onSendReceiveReady(sendReceive);
            sendReceive.start();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onReceive(String string) {
        callback.onMessageReceive(string);
    }

    public interface Callback {

        void onSendReceiveReady(SendReceive sendReceive);
        void onMessageReceive(String string);
    }
}
