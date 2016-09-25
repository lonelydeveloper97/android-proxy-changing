package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.CurrentProxyChangerGetter;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.ApiChecker;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.ProxyChanger;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_15_to_19.WifiConfigurationForApiFrom15To19;
import com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_21_to_22.WifiConfigurationForApiFrom21To22;
import com.lonelydeveloper97.proxychanger.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
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
        ExceptionsPreparer.prepareExceptions(expectedException, context);
    }

    @Test
    public void testChooseProxyChangerForCurrentApi() throws Exception {
        ProxyChanger proxyChanger = CurrentProxyChangerGetter.chooseProxyChangerForCurrentApi(context);
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        assertEquals(manager.getConnectionInfo().getNetworkId(), proxyChanger.getWifiConfiguration().networkId);

        if (ApiChecker.isJellyBeanOrKitkat()) {
            assertTrue(proxyChanger instanceof WifiConfigurationForApiFrom15To19);
        } else if (ApiChecker.isLolipop()) {
            assertTrue(proxyChanger instanceof WifiConfigurationForApiFrom21To22);
        }
    }

}