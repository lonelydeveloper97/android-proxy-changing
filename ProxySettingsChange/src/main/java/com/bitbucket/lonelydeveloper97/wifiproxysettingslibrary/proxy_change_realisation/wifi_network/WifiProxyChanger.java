package com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.SdkNotSupportedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxyChanger;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxySettings;

import java.lang.reflect.InvocationTargetException;

public abstract class WifiProxyChanger {


    public static void changeWifiProxySettings(String host, int port, Context context)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException, NoSuchFieldException, SdkNotSupportedException, NullWifiConfigurationException {
        updateWifiWithNewConfiguration(getWifiConfigurationWithUpdatedProxySettings(host, port, context), context);
    }


    public static void clearProxySettings(Context context)
            throws IllegalAccessException, SdkNotSupportedException, NoSuchFieldException, NullWifiConfigurationException {
        updateWifiWithNewConfiguration(getWifiConfigurationWithNoneProxySettings(context), context);
    }

    private static WifiConfiguration getWifiConfigurationWithUpdatedProxySettings(String host, int port, Context context)
            throws IllegalAccessException, SdkNotSupportedException, NoSuchFieldException,
            ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, NullWifiConfigurationException {
        ProxyChanger proxyChanger = CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context);
        proxyChanger.setProxyHostAndPort(host, port);
        proxyChanger.setProxySettings(ProxySettings.STATIC);
        return proxyChanger.getWifiConfiguration();
    }

    private static WifiConfiguration getWifiConfigurationWithNoneProxySettings(Context context)
            throws NoSuchFieldException, IllegalAccessException, SdkNotSupportedException, NullWifiConfigurationException {
        ProxyChanger proxyChanger = CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context);
        proxyChanger.setProxySettings(ProxySettings.NONE);
        return proxyChanger.getWifiConfiguration();
    }

    private static void updateWifiWithNewConfiguration(WifiConfiguration wifiConfiguration, Context context) {
        WifiManager currentWifiManager = NetworkHelper.getWifiManager(context);
        currentWifiManager.updateNetwork(wifiConfiguration);
        currentWifiManager.saveConfiguration();
        currentWifiManager.reconnect();
    }
}
