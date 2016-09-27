package com.lonelydeveloper97.proxychanger;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyChanger;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.ApiNotSupportedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;

import java.lang.reflect.InvocationTargetException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeProxySettings("myhost.com", 12345);
    }

    void changeProxySettings(String host, int port) {
        try {
            WifiProxyChanger.changeWifiStaticProxySettings(host, port, this);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | NoSuchFieldException | IllegalAccessException | NullWifiConfigurationException | ApiNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

