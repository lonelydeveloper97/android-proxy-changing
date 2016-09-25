package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.ApiChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyChanger;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyInfo;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxySettings;
import com.lonelydeveloper97.proxychanger.MainActivity;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WifiProxyInfoTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    Context context;


    @Before
    public void prepareAndPresetProxy() throws Exception {
        context = mActivityRule.getActivity();

        ExceptionsPreparer.prepareExceptions(expectedException, context);

        if (ApiChecker.isSupportedApi()) {
            WifiProxyChanger.clearProxySettings(context);
            WifiProxyChanger.changeWifiStaticProxySettings("localhost", 3030, context);
        }
    }

    @Test
    public void testGetHost() throws Exception {
        assertEquals("localhost", WifiProxyInfo.getHost(context));
    }

    @Test
    public void testGetPort() throws Exception {
        assertEquals(3030, WifiProxyInfo.getPort(context));
    }

    @Test
    public void testGetProxySettings() throws Exception {
        assertEquals(ProxySettings.STATIC, WifiProxyInfo.getProxySettings(context));
    }

    @After
    public void сlearSettings() throws Exception {
        if (NetworkHelper.isWifiConnected(context) && ApiChecker.isSupportedApi())
            WifiProxyChanger.clearProxySettings(context);
    }

}