package com.example.budalajedna.nfctryout.connection.wifi;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Looper;

import com.example.budalajedna.nfctryout.presentation.main.MainCallback;

import java.util.Collection;

public class WifiManager implements WifiBroadcastReceiver.Callback, SendReceive.Callback{

    private boolean initialisation;
    private boolean registred;
    private boolean connected;
    private String deviceAdress;
    private Context context;
    private WifiP2pManager.Channel channel;
    private IntentFilter intentFilter;
    private WifiP2pManager manager;
    private WifiBroadcastReceiver receiver;
    private SendReceive sendReceive;
    private MainCallback mainCallback;
    private Callback callback;

    public WifiManager(Context context, MainCallback mainCallback, Callback callback) {
        this.mainCallback = mainCallback;
        this.callback = callback;
        prepareWifi(context);
    }

    public WifiBroadcastReceiver getReceiver(){
        return receiver;
    }

    private void prepareWifi(Context context) {
        this.context = context;
        manager = (WifiP2pManager) context.getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(context, Looper.getMainLooper(), null);
        receiver = new WifiBroadcastReceiver(manager, channel, this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
        context.registerReceiver(receiver, intentFilter);
        registred = true;
        searchForPeers();
    }

    private void searchForPeers(){
        manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(int reason) {

            }
        });
    }

    public void disconnect(){
        if(connected) {
            manager.removeGroup(channel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailure(int reason) {

                }
            });
        }
    }

    public boolean isRegistred() {
        return registred;
    }

    public void setRegistred(boolean registred){
        this.registred = registred;
    }

    public void connect(String deviceAddress) {

        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = deviceAddress;
        manager.connect(channel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                initialisation = true;
                connected = true;
                WifiManager.this.callback.onInvitationSend(true);
            }

            @Override
            public void onFailure(int i) {
                WifiManager.this.callback.onInvitationSend(false);
            }
        });
    }

    public void unregisterReceiver(){
        context.unregisterReceiver(receiver);
    }

    public String getDeviceAdress(){
        return deviceAdress;
    }

    @Override
    public void onConnected(boolean groupOwner) {

    }

    @Override
    public void onDeviceUpdated(WifiP2pDevice wifiP2pDevice) {

    }

    @Override
    public void onDevicesChanged(Collection<WifiP2pDevice> collection) {

    }

    @Override
    public void onSendReceiveReady(SendReceive sendReceive) {
        this.sendReceive = sendReceive;
        sendReceive.setCallback(this);
        this.sendReceive.write(mainCallback.getUser().send().getBytes());
    }

    @Override
    public void onWifiP2pStateChanged(boolean z) {

    }

    @Override
    public void onReceiveNFC(String deviceAdress) {
        this.deviceAdress = deviceAdress;
    }

    @Override
    public void onReceiveWIFI(String info) {
        callback.onUserReceived(info);
        initialisation = false;
    }

    public interface Callback{
        void onInvitationSend(boolean sent);
        void onUserReceived(String info);
    }
}
