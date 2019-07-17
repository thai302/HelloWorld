package com.kitcut.helloworld.baserestapi.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

public class MappingUtils {
    //S: source
    //O: dest
//    public static <O, D> D toDTO(O orig) {
//        if (orig == null)
//            return null;
//        D dto = getDtoInstance();
//        try {
//            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
//            BeanUtils.copyProperties(dto, orig);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return dto;
//    }

    public static <O, D> D mapping(O orig, D dest) {
        if (orig == null || dest == null)
            return null;
        else {
            try {
                BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
                BeanUtils.copyProperties(dest, orig);
            } catch (IllegalAccessException e) {
//                e.printStackTrace();
            } catch (InvocationTargetException e) {
//                e.printStackTrace();
            }

            return dest;
        }
    }
}
