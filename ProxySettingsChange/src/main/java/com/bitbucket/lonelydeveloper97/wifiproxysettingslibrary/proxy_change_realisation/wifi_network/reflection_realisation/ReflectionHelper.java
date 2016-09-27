package com.bitbucket.lonelydeveloper97.wifiproxysettingslibrary.proxy_change_realisation.wifi_network.reflection_realisation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public abstract class ReflectionHelper {

    /**
     * Uses for getting public fields with @hide annotation
     */
    public static Object getField(Object obj, String name)
            throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field f = obj.getClass().getField(name);
        return f.get(obj);
    }

    /**
     * Uses for getting private fields
     */
    public static Object getDeclaredField(Object obj, String name)
            throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field f = obj.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(obj);
    }

    /**
     * Uses for setting private fields
     */
    public static void setDeclaredField(Object obj, String name, Object value)
            throws NoSuchFieldException, IllegalAccessException {
        Field httpProxyField = obj.getClass().getDeclaredField(name);
        httpProxyField.setAccessible(true);
        httpProxyField.set(obj, value);
    }

    /**
     * Uses for setting Enum fields
     */
    public static void setEnumField(Object obj, String value, String name)
            throws SecurityException, NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field f = obj.getClass().getField(name);
        f.set(obj, Enum.valueOf((Class<Enum>) f.getType(), value));
    }

    /**
     * Uses for simplifying process of invoking private method
     * Automatically detects args types and founds method to get and invoke
     */
    public static Object getMethodAndInvokeIt(Object obj, String methodName, Object... args)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes(args));
        method.setAccessible(true);
        return method.invoke(obj, args);
    }

    private static Class[] parameterTypes(Object... args) {
        ArrayList<Class> classes = new ArrayList<>();
        for (Object arg : args) {
            classes.add(arg.getClass());
        }
        return classes.toArray(new Class[args.length]);
    }

}
