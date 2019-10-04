package com.kitcut.helloworld.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldUtils {

    public static Field getField(Class<?> clazz, String name) {
        Field field = null;
        while (clazz != null && field == null) {
            try {
                field = clazz.getDeclaredField(name);
            } catch (Exception ex) {
            }
            clazz = clazz.getSuperclass();
        }
        return field;
    }

//    public static void getValue(Class<?> clazz, String name) {
//        try {
//            Object object = clazz.newInstance();
//            invokeMethod(object, clazz, name);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public static void getValue(Object object, Class<?> clazz, String name) {
//        try {
//            Method method = getMethod(clazz, name);
//            method.setAccessible(true);
//            method.invoke(object);
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
}
