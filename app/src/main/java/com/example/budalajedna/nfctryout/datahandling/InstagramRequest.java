package com.example.budalajedna.nfctryout.datahandling;

import android.app.Activity;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class InstagramRequest extends AsyncTask<Void,String,String> {

    private String token;
    private SetInstagramUserCallback setInstagramUserCallback;
    public void setToken(String token){
        this.token=token;
    }
    public void setInstagramUserCallback(SetInstagramUserCallback setInstagramUserCallback){
        this.setInstagramUserCallback=setInstagramUserCallback;
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
        if(response!=null){
            try{
                JSONObject jsonObject=new JSONObject(response);
                JSONObject jsonData=jsonObject.getJSONObject("data");
                String userName=jsonData.getString("username");
                setInstagramUserCallback.setUsername(userName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
    public interface SetInstagramUserCallback{
        void setUsername(String username);
    }
}
