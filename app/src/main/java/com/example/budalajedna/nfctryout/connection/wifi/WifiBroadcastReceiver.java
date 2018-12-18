package com.example.budalajedna.nfctryout.connection.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

public class WifiBroadcastReceiver extends BroadcastReceiver implements ClientClass.Callback, ServerClass.Callback {

    private Callback callback;
    private Channel channel;
    private boolean initialisation = true;
    private ConnectionInfoListener connectionInfoListener = new ConnectionInfoListener() {
        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
            if (wifiP2pInfo.groupFormed) {
                if (wifiP2pInfo.isGroupOwner) {
                    new ServerClass( WifiBroadcastReceiver.this).start();
                } else {
                    new ClientClass(wifiP2pInfo.groupOwnerAddress, WifiBroadcastReceiver.this).start();
                }
                WifiBroadcastReceiver.this.callback.onConnected(wifiP2pInfo.isGroupOwner);
            }
        }
    };
    private WifiP2pManager manager;
    private PeerListListener peerListListener = new PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
            
        }
    };

    public WifiBroadcastReceiver(WifiP2pManager manager, Channel channel, @NonNull Callback callback) {
        this.manager = manager;
        this.channel = channel;
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        WifiP2pDevice device = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
        if(device!=null)callback.onReceived(device.deviceAddress);
        if ("android.net.wifi.p2p.STATE_CHANGED".equals(action)) {
            callback.onWifiP2pStateChanged(intent.getIntExtra("wifi_p2p_state", -1) == 2);
        } else if ("android.net.wifi.p2p.PEERS_CHANGED".equals(action)) {
            if (manager != null) {
                manager.requestPeers(channel, peerListListener);
            }
        } else if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(action)) {
            if (manager != null) {
                if (((NetworkInfo) intent.getParcelableExtra("networkInfo")).isConnected()) {
                    manager.requestConnectionInfo(channel, connectionInfoListener);
                } else {
                    callback.onDevicesChanged(new ArrayList());
                }
            }
        } else if ("android.net.wifi.p2p.THIS_DEVICE_CHANGED".equals(action)) {
            callback.onDeviceUpdated((WifiP2pDevice) intent.getParcelableExtra("wifiP2pDevice"));
        }
    }
    public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
        if (wifiP2pInfo.groupFormed) {
            if (wifiP2pInfo.isGroupOwner) {
                new ServerClass(this);
            } else {
                new ClientClass(wifiP2pInfo.groupOwnerAddress, this);
            }
            callback.onConnected(wifiP2pInfo.isGroupOwner);
        }
    }

    @Override
    public void onSendReceiveReady(SendReceive sendReceive) {
        callback.onSendReceiveReady(sendReceive);
    }


    @Override
    public void onMessageReceive(String message) {

        this.callback.onMessageReceived(message);
        this.initialisation = false;
    }

    public interface Callback{
        void onConnected(boolean groupOwner);

        void onDeviceUpdated(WifiP2pDevice wifiP2pDevice);

        void onDevicesChanged(Collection<WifiP2pDevice> collection);

        void onMessageReceived(String str);

        void onSendReceiveReady(SendReceive sendReceive);

        void onWifiP2pStateChanged(boolean z);

        void onReceived(String deviceAdress);
    }
}
