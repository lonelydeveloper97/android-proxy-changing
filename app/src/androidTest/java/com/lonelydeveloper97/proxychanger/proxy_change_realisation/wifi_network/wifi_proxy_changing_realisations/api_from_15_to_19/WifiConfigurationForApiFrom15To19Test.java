package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_15_to_19;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.NetworkHelper;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.CurrentProxyChangerGetter;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.ApiChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.NullWifiConfigurationException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.exceptions.WifiProxyNotSettedException;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_15_to_19.ProxyPropertiesContainer;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_15_to_19.WifiConfigurationForApiFrom15To19;
import com.lonelydeveloper97.proxychanger.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class WifiConfigurationForApiFrom15To19Test {
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
    public void testGetProxyPropertiesContainer() throws Exception {
        if (!ApiChecker.isJellyBeanOrKitkat())
            return;
        WifiConfigurationForApiFrom15To19 wifiConfigurationForApiFrom15To19
                = new WifiConfigurationForApiFrom15To19(context);
        ProxyPropertiesContainer proxyPropertiesContainer = wifiConfigurationForApiFrom15To19.getProxyPropertiesContainer();
    }

}