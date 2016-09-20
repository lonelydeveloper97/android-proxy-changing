package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_15_to_19;

import android.os.Build;
import android.support.test.rule.ActivityTestRule;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.SmallUtills;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_15_to_19.WifiConfigurationForApiFrom15To19;
import com.lonelydeveloper97.proxychanger.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


public class WifiConfigurationForApiFrom15To19Test {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void testGetProxyPropertiesContainer() throws Exception {
        if (Build.VERSION.SDK_INT > 15 && Build.VERSION.SDK_INT < 20) {
            if (!SmallUtills.getWifiNetworkInfo(mActivityRule.getActivity()).isConnected()) {
                expectedException.expect(NullWifiConfigurationException.class);
            }
            WifiConfigurationForApiFrom15To19 wifiConfigurationForApiFrom15To19
                    = new WifiConfigurationForApiFrom15To19(mActivityRule.getActivity());
            wifiConfigurationForApiFrom15To19.getProxyPropertiesContainer();
        }
    }

}