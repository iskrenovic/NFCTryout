package com.example.budalajedna.nfctryout.connection.wifi;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendReceive extends Thread {

    private static final String TAG = SendReceive.class.getSimpleName();

    private InputStream inputStream;
    private OutputStream outputStream;
    private Callback receiver;
    private Socket socket;

    public SendReceive(Socket socket, Callback receiver) {
        this.socket = socket;
        this.receiver = receiver;
        try {
            inputStream = this.socket.getInputStream();
            outputStream = this.socket.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public void run() {
        while (!socket.isClosed()) {
            try {
                byte[] buffer = new byte[1024];
                int bytes = 0;
                if (!socket.isClosed()) {
                    bytes = inputStream.read(buffer);
                }
                if (bytes > 0 && receiver != null) {
                    String message = new String(buffer);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < message.length(); i++) {
                        if (message.charAt(i) != '\u0000') {
                            stringBuilder.append(message.charAt(i));
                        }
                    }
                    messageFound(stringBuilder);
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        Log.i(TAG, "Closed");
    }

    void messageFound(StringBuilder stringBuilder) {
        receiver.onReceive(stringBuilder.toString());
    }


    public void write(byte[] bytes) {
        if (!this.socket.isClosed()) {
            try {
                this.outputStream.write(bytes);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    public void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public boolean isSocketClosed() {
        return this.socket.isClosed();
    }

    public interface Callback {
        void onReceive(String string);
    }
}
