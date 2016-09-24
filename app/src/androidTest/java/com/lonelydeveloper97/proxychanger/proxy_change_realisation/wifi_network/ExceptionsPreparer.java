package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.content.Context;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.CurrentProxyChangerGetter;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.SDKChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.SdkNotSupportedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.WifiProxyInfoNotSettedException;

import org.junit.rules.ExpectedException;

/**
 * Created by Valeriy on 25.09.2016.
 */
public abstract class ExceptionsPreparer {

    public static void prepareExceptions(ExpectedException expectedException, Context context) throws Exception {
        if (!NetworkHelper.isWifiConnected(context)) {
            expectedException.expect(NullWifiConfigurationException.class);
        }
        if (!SDKChecker.isSupportedSDK()) {
            expectedException.expect(SdkNotSupportedException.class);
        }
        if (CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context).isProxySetted()) {
            expectedException.expect(WifiProxyInfoNotSettedException.class);
        }
    }

}
