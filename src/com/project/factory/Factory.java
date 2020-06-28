package com.project.factory;

import com.project.impel.UserImpel;
import com.project.impel.VoteImpel;
import com.project.proxy.UserProxy;
import com.project.service.IUserService;
import com.project.service.IVoteService;

import java.lang.reflect.InvocationTargetException;

/**
 * 实现接口的类，需要使用工厂获取实例化对象
 */
public class Factory {

    private Factory(){}

    /**
     * 获取接口实例化对象
     * @param className  接口的子类
     * @param clazz 接口类型
     * @param <T>
     * @return 返回指定接口子类实例化对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String className, Class<T> clazz){
        T instance = null;
        try {
            //使用反射实例化
            instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();
            //instance = (T) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }

    //用户登录实例化
    @SuppressWarnings("unchecked")
    public static <T> T getUserInstance(Class<T> clazz){
        try {
            return (T) new UserProxy(clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //投票实例化
    public static IVoteService getVoInstance(String className){
        if (className.equalsIgnoreCase("VoteImpel")){
            return new VoteImpel();
        }
        return null;
    }
    //投票实例化 使用反射实例化
    public static IVoteService getVoteInstance(String className){
        IVoteService instance = null;
        try {
            //使用反射实例化
            instance = (IVoteService) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
        }
        return instance;
    }


}
