package com.example.budalajedna.nfctryout.connection.wifi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;

public class WifiManager {

    private boolean connected;
    private Context context;
    private FieldClickCallback fieldClickCallback;
    private IWiFiCallbacks iWiFiCallbacks;
    public boolean isHost;
    private WifiP2pManager.Channel channel;
    private IntentFilter intentFilter;
    private WifiP2pManager manager;
    private WifiBroadcastReceiver receiver;
    private SendReceive sendReceive;

    public WifiManager(Context context) {
        prepareWifi(context);
    }

    private void prepareWifi(Context context) {
        this.context = context;
        manager = (WifiP2pManager) context.getSystemService("wifip2p");
        channel = manager.initialize(context, Looper.getMainLooper(), null);
        receiver = new WifiBroadcastReceiver(manager, channel, this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        context.registerReceiver(receiver, intentFilter);
    }
}
