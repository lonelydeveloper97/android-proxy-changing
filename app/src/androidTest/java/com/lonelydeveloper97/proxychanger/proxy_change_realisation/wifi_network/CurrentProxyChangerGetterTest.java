package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.test.rule.ActivityTestRule;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.CurrentProxyChangerGetter;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.SDKChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.SdkNotSupportedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.WifiProxyInfoNotSettedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxyChanger;
import com.lonelydeveloper97.proxychanger.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Valeriy on 24.09.2016.
 */
public class CurrentProxyChangerGetterTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    Context context;

    @Before
    public void prepare() throws Exception {
        context = mActivityRule.getActivity();
        ExceptionsPreparer.prepareExceptions(expectedException,context);
    }


    @org.junit.Test
    public void testChooseProxyChangerForCurrentApi() throws Exception {

        ProxyChanger proxyChanger = CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context);
        final WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        assertEquals(manager.getConnectionInfo().getNetworkId(),proxyChanger.getWifiConfiguration().networkId);
    }
}