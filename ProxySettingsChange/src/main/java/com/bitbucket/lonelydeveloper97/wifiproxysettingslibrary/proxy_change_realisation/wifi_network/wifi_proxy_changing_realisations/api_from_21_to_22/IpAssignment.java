package com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.wifi_proxy_changing_realisations.api_from_21_to_22;

import android.annotation.TargetApi;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public enum IpAssignment {

    /* Use statically configured IP settings. Configuration can be accessed
         * with staticIpConfiguration */
    STATIC("STATIC"),
    /* Use dynamically configured IP settigns */
    DHCP("DHCP"),
    /* no IP details are assigned, this is used to indicate
     * that any existing IP settings should be retained */
    UNASSIGNED("UNASSIGNED");

    String value = "";

    IpAssignment(String value) {
        this.value = value;
    }
}