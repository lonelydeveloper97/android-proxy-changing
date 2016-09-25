package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.content.Context;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.CurrentProxyChangerGetter;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.ApiChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.ApiNotSupportedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.WifiProxyNotSettedException;

import org.junit.rules.ExpectedException;


public abstract class ExceptionsPreparer {

    public static void prepareExceptions(ExpectedException expectedException, Context context) throws Exception {
        if (!ApiChecker.isSupportedApi()) {
            expectedException.expect(ApiNotSupportedException.class);
        } else if (!NetworkHelper.isWifiConnected(context)) {
            expectedException.expect(NullWifiConfigurationException.class);
        } else if (!CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context).isProxySetted()) {
            expectedException.expect(WifiProxyNotSettedException.class);
        }
    }

}
