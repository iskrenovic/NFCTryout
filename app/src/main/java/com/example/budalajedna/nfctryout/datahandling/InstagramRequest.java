package com.example.budalajedna.nfctryout.datahandling;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class InstagramRequest extends AsyncTask<Void,String,String> {

    private String token;
    public void setToken(String token){
        this.token=token;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://api.instagram.com/v1/users/self/?access_token=" + token);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        String id=response;

    }
}
