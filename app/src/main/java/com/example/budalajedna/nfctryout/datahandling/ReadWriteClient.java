package com.example.budalajedna.nfctryout.datahandling;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadWriteClient {

    File file;
    Context context;
    private static final String filename = "ConnectClientInfo";

    public ReadWriteClient(){

    }

    public void readClient(){
        FileInputStream fileInputStream;
        try {
            fileInputStream = context.openFileInput(filename);
//            char[] fileContent = new char[fileInputStream.read()];
            while(fileInputStream.read()!=-1){

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeClient(String info){

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
