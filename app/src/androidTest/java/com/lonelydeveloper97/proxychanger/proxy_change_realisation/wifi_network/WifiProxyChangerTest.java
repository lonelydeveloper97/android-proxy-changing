package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.ApiChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.CurrentProxyChangerGetter;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyChanger;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyInfo;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxySettings;
import com.lonelydeveloper97.proxychanger.MainActivity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class WifiProxyChangerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    Context context;


    @Before
    public void prepare() throws Exception {
        context = mActivityRule.getActivity();
        ExceptionsPreparer.prepareExceptions(expectedException, context);
    }

    @Test
    public void testChangeWifiStaticProxySettings() throws Exception {
        String testIp = RandomValuesGenerator.randomIp();
        int testPort = RandomValuesGenerator.randomPort();

        WifiProxyChanger.changeWifiStaticProxySettings(testIp, testPort, context);

        assertEquals(testIp, WifiProxyInfo.getHost(context));
        assertEquals(testPort, WifiProxyInfo.getPort(context));
    }

    @Test
    public void testProxySettingsClear() throws Exception {
        String testIp = RandomValuesGenerator.randomIp();
        int testPort = RandomValuesGenerator.randomPort();

        WifiProxyChanger.changeWifiStaticProxySettings(testIp, testPort, context);
        WifiProxyChanger.clearProxySettings(context);

        assertEquals(ProxySettings.NONE, CurrentProxyChangerGetter
                .chooseProxyChangerForCurrentApi(context)
                .getProxySettings());
    }

    @After
    public void сlearSettings() throws Exception {
        if (NetworkHelper.isWifiConnected(context) && ApiChecker.isSupportedApi())
            WifiProxyChanger.clearProxySettings(context);
    }

}