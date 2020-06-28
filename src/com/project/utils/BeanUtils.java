package com.project.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanUtils {
    private BeanUtils(){};

    /**
     * 实现指定对象的属性赋值
     * @param obj 进行反射操作的实例化对象
     * @param content  赋值给对象属性的内容，如"属性：内容|属性：内容"
     */
    public static void setValue(Object obj, String content){
        String[] datas = content.split("\\|");  //数据拆分
        for (String data: datas) {
            String[] fieldValue = data.split(":"); //获取 属性名 与 内容
            try {
                if (fieldValue[0].contains(".")){
                    // TODO: 2020/5/7 级联类
                } else {
                    //获取成员属性
                    Field field = obj.getClass().getDeclaredField(fieldValue[0]);
                    //获取属性 setter 方法
                    Method setMethod = obj.getClass().getDeclaredMethod("set" + StringUtils.toUpper(fieldValue[0]),
                            field.getType());
                    setMethod.invoke(obj, convertAttributeValue(field.getType().getName(), fieldValue[1]));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    /**
     * 实现属性类型的转换
     * @param type 属性类型，通过 Field 获取
     * @param value 传入属性的内容，需将其转为指定的类型
     * @return
     */
    private static Object convertAttributeValue(String type, String value){
        if (type.equals("long") || type.equals("java.lang.Long")){
            return Long.parseLong(value);
        } else if (type.equals("int") || type.equals("java.lang.Integer")){
            return Integer.parseInt(value);
        } else if (type.equals("double") || type.equals("java.lang.Double")){
            return Double.parseDouble(value);
        } else if (type.equals("java.util.Date")){
            SimpleDateFormat sdf = null;
            if (value.matches("\\d{4}-\\d{2}-\\d{2}")){
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                return new Date();
            }
            try {
                return sdf.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            return value;
        }
        return null;
    }

}
