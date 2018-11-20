package com.example.budalajedna.nfctryout.background;

import android.app.Application;
import android.content.Intent;

public class MiniApp extends Application {
    @Override
    public void onCreate() {

        super.onCreate();

        startService(new Intent(this,AlwaysOnService.class));
    }
}
