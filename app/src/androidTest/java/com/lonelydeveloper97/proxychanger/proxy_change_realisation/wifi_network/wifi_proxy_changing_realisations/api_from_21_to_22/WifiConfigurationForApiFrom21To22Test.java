package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_21_to_22;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.CurrentProxyChangerGetter;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.ApiChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.WifiProxyNotSettedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_21_to_22.IpAssignment;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_21_to_22.WifiConfigurationForApiFrom21To22;
import com.lonelydeveloper97.proxychanger.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


public class WifiConfigurationForApiFrom21To22Test {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    Context context;

    @Before
    public void prepare() throws Exception {
        context = mActivityRule.getActivity();

        if (!NetworkHelper.isWifiConnected(context)) {
            expectedException.expect(NullWifiConfigurationException.class);
        }
        if (!CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context).isProxySetted()) {
            expectedException.expect(WifiProxyNotSettedException.class);
        }
    }


    @Test
    public void testSetIpAssignment() throws Exception {
        if (!ApiChecker.isLolipop())
            return;
        WifiConfigurationForApiFrom21To22 wifiConfiguration = WifiConfigurationForApiFrom21To22.createFromCurrentContext(context);
        wifiConfiguration.setIpAssignment(IpAssignment.UNASSIGNED);
        assertEquals(IpAssignment.UNASSIGNED, wifiConfiguration.getIpAssignment());
        wifiConfiguration.setIpAssignment(IpAssignment.STATIC);
        assertEquals(IpAssignment.STATIC, wifiConfiguration.getIpAssignment());
        wifiConfiguration.setIpAssignment(IpAssignment.DHCP);
        assertEquals(IpAssignment.DHCP, wifiConfiguration.getIpAssignment());
    }
}