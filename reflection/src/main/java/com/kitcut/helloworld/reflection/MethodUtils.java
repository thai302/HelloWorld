package com.kitcut.helloworld.reflection;

import java.lang.reflect.Method;

public class MethodUtils {

    public static Method getMethod(Class<?> clazz, String name) {
        Method method = null;
        while (clazz != null && method == null) {
            try {
                method = clazz.getDeclaredMethod(name);
            } catch (Exception ex) {
            }
            clazz = clazz.getSuperclass();
        }
        return method;
    }

    public static void invokeMethod(Class<?> clazz, String name) {
        try {
            Object object = clazz.newInstance();
            invokeMethod(object, clazz, name);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void invokeMethod(Object object, Class<?> clazz, String name) {
        try {
            Method method = getMethod(clazz, name);
            method.setAccessible(true);
            method.invoke(object);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
