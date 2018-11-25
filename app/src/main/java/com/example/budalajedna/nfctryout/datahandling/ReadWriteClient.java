package com.example.budalajedna.nfctryout.datahandling;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ReadWriteClient {
    private Context context;
    private static final String filename = "ConnectClientInfo.cnt";

    public ReadWriteClient(Context context){
        this.context = context;
    }

    public String read(){
        FileInputStream fileInputStream;
        String out = "";
        try {

            fileInputStream = context.openFileInput(filename);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();


            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }

            out = stringBuilder.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return out;
    }

    public void save(String info){

        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(info.getBytes());
            outputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
