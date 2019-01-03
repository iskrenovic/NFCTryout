package com.example.budalajedna.nfctryout.connection.wifi;

import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientClass extends Thread{
    private static final String TAG = ClientClass.class.getSimpleName();
    private String hostAddress;
    private Callback callback;
    private Socket socket = new Socket();

    public ClientClass(InetAddress hostAddress, Callback callback) {
        this.callback = callback;
        this.hostAddress = hostAddress.getHostAddress();
    }

    public void run() {
        super.run();
        try {
            socket.connect(new InetSocketAddress(hostAddress, 8888), 500);
            SendReceive sendReceive = new SendReceive(socket);
            callback.onSendReceiveReady(sendReceive);
            sendReceive.start();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public interface Callback {
        void onSendReceiveReady(SendReceive sendReceive);
    }
}



