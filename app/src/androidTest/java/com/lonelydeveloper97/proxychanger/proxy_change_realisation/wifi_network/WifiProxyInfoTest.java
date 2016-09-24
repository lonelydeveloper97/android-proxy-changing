package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.CurrentProxyChangerGetter;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.SDKChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyChanger;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyInfo;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.SdkNotSupportedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxySettings;
import com.lonelydeveloper97.proxychanger.MainActivity;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.WifiProxyInfoNotSettedException;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;

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

        ExceptionsPreparer.prepareExceptions(expectedException,context);

        WifiProxyChanger.clearProxySettings(context);
        WifiProxyChanger.changeWifiProxySettings("localhost", 3030, context);
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
    public void сlearSettings() throws IllegalAccessException, SdkNotSupportedException, NoSuchFieldException, NullWifiConfigurationException {
        if (NetworkHelper.isWifiConnected(context))
            WifiProxyChanger.clearProxySettings(context);
    }
}