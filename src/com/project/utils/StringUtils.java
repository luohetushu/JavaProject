package com.project.utils;

public class StringUtils {
    private StringUtils(){}

    /**
     * 实现首字母大写
     * @return
     */
    public static String toUpper(String str){
        if (str == null || str.equals("")){
            return str;
        }
        if (str.length() == 1){
            return str.toUpperCase();
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

}
