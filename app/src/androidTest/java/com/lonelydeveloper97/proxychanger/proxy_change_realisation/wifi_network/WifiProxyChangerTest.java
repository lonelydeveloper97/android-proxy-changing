package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.system.ErrnoException;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.SmallUtills;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyChanger;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.WifiProxyInfo;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.SdkNotSupportedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxySettings;
import com.lonelydeveloper97.proxychanger.MainActivity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;



@RunWith(AndroidJUnit4.class)
@SmallTest
public class WifiProxyChangerTest {


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testChangeWifiProxySettings() throws Exception {
        if (!SmallUtills.getWifiNetworkInfo(mActivityRule.getActivity()).isConnected()) {
            expectedException.expect(NullWifiConfigurationException.class);
        }
        String testIp = RandomValuesGenerator.randomIp();
        int testPort = RandomValuesGenerator.randomPort();

        WifiProxyChanger.changeWifiProxySettings(testIp, testPort, mActivityRule.getActivity());

        assertEquals("Ip changed", testIp, WifiProxyInfo.getHost(mActivityRule.getActivity()));
        assertEquals("Port changed", testPort, WifiProxyInfo.getPort(mActivityRule.getActivity()));
    }



    @Test
    public void testProxySettingsClear() throws Exception {
        if (!SmallUtills.getWifiNetworkInfo(mActivityRule.getActivity()).isConnected()) {
            expectedException.expect(NullWifiConfigurationException.class);
        }
        String testIp = RandomValuesGenerator.randomIp();
        int testPort = RandomValuesGenerator.randomPort();
        WifiProxyChanger.changeWifiProxySettings(testIp, testPort, mActivityRule.getActivity());
        WifiProxyChanger.clearProxySettings(mActivityRule.getActivity());
        assertEquals(ProxySettings.NONE, WifiProxyChanger
                .chooseProxyChangerForCurrentApi(mActivityRule.getActivity())
                .getProxySettings());
    }

    @After
    public void turnWifiOnAndClearSettings() throws IllegalAccessException, SdkNotSupportedException, NoSuchFieldException, NullWifiConfigurationException {
        if (SmallUtills.getWifiNetworkInfo(mActivityRule.getActivity()).isConnected())
            WifiProxyChanger.clearProxySettings(mActivityRule.getActivity());
        SmallUtills.getWifiManager(mActivityRule.getActivity()).setWifiEnabled(true);
    }

}