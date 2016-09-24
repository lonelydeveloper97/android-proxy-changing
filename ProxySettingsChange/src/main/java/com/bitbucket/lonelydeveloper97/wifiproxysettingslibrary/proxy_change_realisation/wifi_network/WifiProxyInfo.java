package com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network;

import android.content.Context;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.SdkNotSupportedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxySettings;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_21_to_22.WifiConfigurationForApiFrom21To22;

import java.lang.reflect.InvocationTargetException;

public abstract class WifiProxyInfo {

    public static String getHost(Context context)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SdkNotSupportedException, NoSuchFieldException, NullWifiConfigurationException {
       return CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context).getProxyHost();
    }


    public static int getPort(Context context)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SdkNotSupportedException, NoSuchFieldException, NullWifiConfigurationException {
        return CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context).getProxyPort();
    }

    public static ProxySettings getProxySettings(Context context)
            throws SdkNotSupportedException, IllegalAccessException, NoSuchFieldException, NullWifiConfigurationException {
        return CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context).getProxySettings();
    }
}
