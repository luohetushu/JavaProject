package com.project.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理设计
 */
public class ProxyHandle implements InvocationHandler {

    private Object target;  //代理对象，保存真实业务对象

    /**
     * 动态代理设计的动态代理对象是由 JVM 底层实现的，依据 java.lang.reflect.Proxy 类方法获得
     * 代理类 java.lang.reflect.Proxy 类 JDK 1.3
     *       获得动态代理对象方法：public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h){}
     *                          --ClassLoader loader: 获取当前真实主体类的 ClassLoader
     *                          Class<?>[] interfaces: 获取当前真实主体类的所有接口
     *                          InvocationHandler h:代理处理的类，一般都是实现该接口的类
     */

    /**
     * 进行真实业务对象与代理对象的绑定
     * @param target 真实业务对象
     * @return java.lang.reflect.Proxy 类生成的业务代理对象
     */
    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * 代理方法调用，代理设计里最终执行的方法
     * @param obj 代理的对象
     * @param method  要执行的接口方法
     * @param args 传递的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {

        // TODO: 2020/5/8 调用真实业务主体对象方法之前可能会有的预处理操作

        Object retObj = null;
        try {
            //调用目标方法
            retObj = method.invoke(this.target, args);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        // TODO: 2020/5/8 调用真实业务主体对象方法之后可能会有的收尾操作

        return retObj;
    }
}
