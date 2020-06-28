package com.project.factory;

import com.project.utils.BeanUtils;

public class ClassInstanceFactory {

    private ClassInstanceFactory(){}

    /**
     * 创建实例化对象，根据传入的内容，如"属性：内容|属性：内容"，给对象属性赋值
     * @param clazz 需要进行反射实例化的 Class 类
     * @param content 赋值给对象属性的内容
     * @param <T>
     * @return 返回属性赋值完成的类的实例化对象
     */
    public static <T> T create(Class<?> clazz, String content){
        try {
            //类中需要有无参构造方法
            Object obj = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.setValue(obj, content); //通过反射设置类属性内容
            return (T) obj;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
