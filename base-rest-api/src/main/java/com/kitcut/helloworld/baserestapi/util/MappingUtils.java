package com.kitcut.helloworld.baserestapi.util;

import com.kitcut.helloworld.baserestapi.dto.response.employee.BasePageResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.data.domain.Page;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static <O, D> D mappingObject(O origin, D dest) {
        if (origin == null || dest == null)
            return null;
        else {
            try {
                BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
                BeanUtils.copyProperties(dest, origin);
            } catch (IllegalAccessException e) {
//                e.printStackTrace();
            } catch (InvocationTargetException e) {
//                e.printStackTrace();
            }

            return dest;
        }
    }

    public static <O, D> D mappingObject(O origin, Class<D> clazzDest) {
        try {
            D dest = clazzDest.newInstance();
            return mappingObject(origin, dest);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <O, D> List<D> mappingListObject(List<O> listOrigin, Class<D> clazzDest) {
        if (listOrigin == null)
            return null;
        else {
            Function<O, D> function = origin -> MappingUtils.mappingObject(origin, clazzDest);
            List<D> listDest = listOrigin.stream().map(function).collect(Collectors.toList());
//            List<D> listDest = new ArrayList<>();
//            for (O origin : listOrigin) {
//                D dest = mappingObject(origin, clazzDest);
//                listDest.add(dest);
//            }
            return listDest;
        }
    }

    public static <O, D> Page<D> mappingPage(Page<O> pageOrigin, Class<D> clazzDest){
        Function<O, D> function = origin -> MappingUtils.mappingObject(origin, clazzDest);
        return pageOrigin.map(function);
    }

    public static <O, D> BasePageResponse<D> mappingPageCustom(Page<O> pageOrigin, Class<D> clazzDest){
        BasePageResponse pageDest = mappingObject(pageOrigin, BasePageResponse.class);
        List<D> content = mappingListObject(pageDest.getContent(), clazzDest);
        pageDest.setContent(content);

        return pageDest;
    }
}
