package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.support.test.rule.ActivityTestRule;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyChanger;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyInfo;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxySettings;
import com.lonelydeveloper97.proxychanger.MainActivity;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.SmallUtills;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.WifiProxyInfoNotSettedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_21_to_22.WifiConfigurationForApiFrom21To22;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WifiProxyInfoTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Before
    public void presetProxySettings() throws Exception{
        if (!SmallUtills.getWifiNetworkInfo(mActivityRule.getActivity()).isConnected()) {
            expectedException.expect(NullWifiConfigurationException.class);
        }
        WifiProxyChanger.clearProxySettings( mActivityRule.getActivity());
        WifiProxyChanger.changeWifiProxySettings("localhost", 3030, mActivityRule.getActivity());
    }


    @Test
    public void testGetHost() throws Exception {
        if (!SmallUtills.getWifiNetworkInfo(mActivityRule.getActivity()).isConnected()) {
            expectedException.expect(NullWifiConfigurationException.class);
        }
        if (WifiProxyChanger.chooseProxyChangerForCurrentApi(mActivityRule.getActivity()).isProxySetted()) {
            expectedException.expect(WifiProxyInfoNotSettedException.class);
        }
        assertEquals("localhost",WifiProxyInfo.getHost(mActivityRule.getActivity()));
    }

    @Test
    public void testGetPort() throws Exception {
        if (!SmallUtills.getWifiNetworkInfo(mActivityRule.getActivity()).isConnected()) {
            expectedException.expect(NullWifiConfigurationException.class);
        }
        if (WifiProxyChanger.chooseProxyChangerForCurrentApi(mActivityRule.getActivity()).isProxySetted()) {
            expectedException.expect(WifiProxyInfoNotSettedException.class);
        }
        assertEquals(3030,WifiProxyInfo.getPort(mActivityRule.getActivity()));
    }

    @Test
    public void testGetProxySettings() throws Exception {
        if (!SmallUtills.getWifiNetworkInfo(mActivityRule.getActivity()).isConnected()) {
            expectedException.expect(NullWifiConfigurationException.class);
        }
        assertEquals(ProxySettings.STATIC,WifiProxyInfo.getProxySettings(mActivityRule.getActivity()));
    }
}