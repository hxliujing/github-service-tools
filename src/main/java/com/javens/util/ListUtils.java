package com.javens.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by john on 17-2-22.
 */
public class ListUtils {
   /* public final static List<String> commonProperty = Arrays.asList("addTime", "addUserId", "modifyTime", "modifyUserId", "isDelete", "isDeleted");

    public static String[] getIgnoreProperties(boolean bCommon, String... ignoreProperties){
        List<String> result = new ArrayList<>();
        if (bCommon) result.addAll(commonProperty);
        Collections.addAll(result, ignoreProperties);
        return (String[]) result.toArray(new String[result.size()]);
    }*/

    public static <T> List<T> copyProperties(List<T> obj, Class<T> cls, String... ignoreProperties){
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < obj.size(); i++) {
            T t = null;
            try {
                t = cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                return result;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return result;
            }
            BeanUtils.copyProperties(obj.get(i), t, ignoreProperties);
            result.add(t);
        }

        return result;
    }

    public static <T> List<T> subListByPage(final List<T> obj, String pageNo, String pageSize){
        Integer fromIndex = getStartByPageNo(pageNo, pageSize);
        Integer toIndex = null;
        if (fromIndex == null) return obj;
        if (obj.size() < fromIndex){
            return new ArrayList<T>();
        }else if (obj.size() >= (fromIndex + Integer.valueOf(pageSize))) {
            toIndex = fromIndex + Integer.valueOf(pageSize);
        }else {
            toIndex = obj.size();
        }
        return obj.subList(fromIndex, toIndex);
    }

    public static Integer getStartByPageNo(String pageNo, String pageSize){
        if (!VerifyUtil.isInteger(pageNo) || !VerifyUtil.isInteger(pageSize)) return null;

        if (pageNo.equals("0") || pageSize.equals("0")) return null;

        if (pageNo.equals("1")) return 0;

        return (Integer.valueOf(pageNo) - 1) * 10;
    }

}
