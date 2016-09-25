package com.lonelydeveloper97.proxychanger.proxy_change_realisation.wifi_network;


import java.util.Random;

/**
 * Created by valeriy on 11.09.2016.
 */

public abstract class RandomValuesGenerator {

    public static String randomIp() {
        Random rnd = new Random();
        String randomIp = rnd.nextInt(256) + "." + rnd.nextInt(256) + "." + rnd.nextInt(256) + "." + rnd.nextInt(256);
        return randomIp;
    }

    public static int randomPort() {
        return new Random().nextInt(30000);
    }

}
