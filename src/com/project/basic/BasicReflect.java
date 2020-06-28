package com.project.basic;


import com.project.custom.NetAnnotation;
import com.project.custom.NetClassLoader;
import com.project.factory.ClassInstanceFactory;
import com.project.impel.HeiRiderImpel;
import com.project.impel.VoteStudent;
import sun.misc.Unsafe;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 反射 java.lang.reflect 包
 */
public class BasicReflect {

    /**
     * 反射机制：解决传统编程方式下的大量重复性（类实例化，setter 赋值）工作
     *   首先了解"正"与"反"的概念，"正"：程序开发中，1、导入所用类的包 2、根据类进行实例化对象 3、再根据实例化对象调用方法属性
     *                          "反"：根据实例化对象获取其所在类的方法属性以及继承实现关系
     *   要想实现"反"的操作，需要使用 java.lang.Object 类的方法：
     *          获取 class 对象信息：@HotSpotIntrinsicCandidate
     *                             public final native Class<?> getClass();
     */

    /**
     * 反射机制所有操作都是通过 java.lang.Class 类对象展开
     * java.lang.Class 类: JDK 1.0
     *     public final class Class<T> implements Serializable, GenericDeclaration, Type, AnnotatedElement{}
     *     三种实例化 Class 对象方法：1、【Object 类支持】使用 java.lang.Object 类中 getClass() 方法；
     *                                 注：该方法需要进行类的实例化，以及类所在包的导入
     *                             2、【JVM 支持】直接使用 类.class 方式实例化 Class 对象
     *                                 注：该方法需要导入类所在的包
     *                             3、【Class 类支持】使用 java.lang.Class 类中 static 方法：
     *                                 @CallerSensitive
     *                                 public static Class<?> forName(String className) throws ClassNotFoundException {}
     *     反射实例化方法(代替关键字 new)：
     *            JDK 1.9 之前：public T newInstance() throws InstantiationException, IllegalAccessException{}
     *            JDK 1.9 之后：public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException{}
     *                         public T newInstance(Object... initargs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{}
     *
     *     反射调用构造方法 Constructor：
     *            获取本类所有构造方法：public Constructor<?>[] getDeclaredConstructors() throws SecurityException{}
     *            获取本类指定构造方法：public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException{}
     *            获取所有构造方法：public Constructor<?>[] getConstructors() throws SecurityException{}
     *            获取指定构造方法：public Constructor<T> getConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException{}
     *
     *     反射调用普通方法 Method：
     *            获取本类所有普通方法：public Method[] getDeclaredMethods() throws SecurityException {}
     *            获取本类指定普通方法：public Method getDeclaredMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException{}
     *            获取所有普通方法（包含父类中的方法）：public Method[] getMethods() throws SecurityException {}
     *            获取指定普通方法（包含父类中的方法）：public Method getMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {}
     *
     *     反射调用属性 Field：
     *            获取本类所有属性：public Field[] getDeclaredFields() throws SecurityException{}
     *            获取本类指定属性：public Field getDeclaredField(String name) throws NoSuchFieldException, SecurityException{}
     *            获取所有父类中的 public 属性：public Field[] getFields() throws SecurityException{}
     *            获取指定父类中的 public 属性：public Field getField(String name) throws NoSuchFieldException, SecurityException{}
     *
     *
     */

    /**
     *       java.lang.reflect.AccessibleObject   JDK 1.2
     *              public class AccessibleObject implements AnnotatedElement{}
     *       java.lang.reflect.Executable   JDK 1.8
     *              public abstract class Executable extends AccessibleObject implements Member, GenericDeclaration{}
     * 构造类
     *       java.lang.reflect.Constructor<T> JDK 1.1
     *              public final class Constructor<T> extends Executable {}
     */

    /**
     *       java.lang.reflect.AccessibleObject   JDK 1.2
     *              public class AccessibleObject implements AnnotatedElement{}
     *       java.lang.reflect.Executable   JDK 1.8
     *              public abstract class Executable extends AccessibleObject implements Member, GenericDeclaration{}
     * 方法类
     *       java.lang.reflect.Method JDK 1.1
     *              public final class Method extends Executable {}
     *              执行被指定的方法：public Object invoke(Object obj, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{}
     */

    /**
     *       java.lang.reflect.AccessibleObject   JDK 1.2
     *              public class AccessibleObject implements AnnotatedElement{}
     * 属性
     *       java.lang.reflect.Field JDK 1.1
     *                    public final class Field extends AccessibleObject implements Member {}
     *                    设置属性内容：public void set(Object obj, Object value) throws IllegalArgumentException, IllegalAccessException{}
     *                    获取属性内容：public Object get(Object obj) throws IllegalArgumentException, IllegalAccessException{}
     *                    解除属性封装(true)：public void setAccessible(boolean flag) {}
     *                    获取属性类型：public Class<?> getType(){}
     *
     */

    /**
     * sun.misc.Unsafe 类：利用反射类获取对象，可以用 Unsafe 类对象来获取私有构造方法的类的对象，比如单例
     *     public final class Unsafe{}
     *     构造方法：private Unsafe() {}
     *     获取 Unsafe 类对象：public static Unsafe getUnsafe() {}
     *     获取指定类的实例化对象：public Object allocateInstance(Class<?> cls) throws InstantiationException{}
     */

    /**
     * 类加载器：java.lang.ClassLoader
     *        底端：应用程序类加载器：AppClassLoader
     *        中端：平台类加载器（JDK 1.8及以下为扩展类加载器, ExtClassLoader）: PlatformClassLoader JDK 1.9及以上
     *        高端：系统类加载器（程序中无法获取）：Bootstrap
     *        ClassLoader 类对象一般通过 Class 类获取：public ClassLoader getClassLoader(){}
     *        ClassLoader 类对象获取父类加载器对象：public final ClassLoader getParent(){}
     *        通过 ClassLoader 类取得反射的 Class：public Class<?> loadClass(String name) throws ClassNotFoundException{}
     *        将 类.class 字节码文件中获取的字节内容转成 Class 类：
     *                protected final Class<?> defineClass(String name, byte[] b, int off, int len) throws ClassFormatError
     *        将资源文件转换成输入流：
     *            1、java.lang.Class<T>: public InputStream getResourceAsStream(String name){}
     *               //name 不是以 / 开头，那么资源文件路径相对于当前类所在路径而言
     *                    如：//要求资源文件 dbconfig.properties 与当前类在同一目录下
     *                       //InputStream fis = JdbcUtils.class.getResourceAsStream("dbconfig.properties");
     *               //name 是以 / 开头，那么资源文件路径相对于 /classes （程序编译后产生的）而言
     *                    如：//要求资源文件 dbconfig.properties 在 /classes 下
     *                       //InputStream fis = JdbcUtils.class.getResourceAsStream("/dbconfig.properties");
     *            2、java.lang.ClassLoader：public InputStream getResourceAsStream(String name){}
     *               //资源文件路径相对于 /classes  （程序编译后产生的）而言
     */

    /**
     * java.lang.reflect.AccessibleObject   JDK 1.2
     *      获取全部 Annotation（注解） ：public Annotation[] getAnnotations(){}
     *      获取指定 Annotation：public <T extends Annotation> T getAnnotation(Class<T> annotationClass){}
     *
     *      注：不同的 Annotation 有不同的生效状态，即有些 Annotation 在程序运行时获取不到
     * Annotation 的生效：
     *      public enum RetentionPolicy {
     *         SOURCE,   //.java 源代码生效，即编译的时候
     *         CLASS,    //.class 字节码文件中生效
     *         RUNTIME;  //运行时生效，即可以在程序运行时获取到
     *         private RetentionPolicy() {}
     *      }
     *
     */

    /**
     * 内省类：java.beans.Introspector    //依赖反射实现，比反射操作容易
     *            public class Introspector {}
     *            获取 JavaBean 的信息类 BeanInfo：public static BeanInfo getBeanInfo(Class<?> beanClass) throws IntrospectionException{}
     * JavaBean 的信息类：java.beans.BeanInfo:
     *                       public interface BeanInfo {}
     *                       获取属性描述符对象：PropertyDescriptor[] getPropertyDescriptors();
     *                       获取方法描述符对象：MethodDescriptor[] getMethodDescriptors();
     * 属性描述符对象：java.beans.PropertyDescriptor
     *                  public class PropertyDescriptor extends FeatureDescriptor {}
     *                  获取读方法：public synchronized Method getReadMethod() {}  //对应 JavaBean 类中属性的 get 方法
     *                  获取写方法：public synchronized Method getWriteMethod() {} //对应 JavaBean 类中属性的 set 方法
     */


    public static void main(String[] args)  {
        Date date = new Date(); //public class Date implements Serializable, Cloneable, Comparable<Date>{}
        Class<? extends Date> cla = date.getClass();
        System.out.println(cla); //class java.util.Date
        System.out.println(cla.getName()); //java.util.Date
        //获取类所在包的定义
        Package pack = cla.getPackage();
        System.out.println(pack.getName()); //java.util
        //获取父类
        Class<?> superCla = cla.getSuperclass();
        System.out.println(superCla.getName());  // 完整类名称 包.类： java.lang.Object
        System.out.println(superCla.getSimpleName());  // 类名称 Object
        System.out.println(superCla.getSuperclass());  //null
        //获取接口
        Class<?>[] clazz = cla.getInterfaces();
        for (Class<?> inCla : clazz) {
            System.out.println(inCla.getName());
        }

        Class<? extends Math> cla2 = Math.class;
        System.out.println(cla2.getName());  //java.lang.Math

        try {
            Class<?> cla3 = Class.forName("java.util.UUID");
            System.out.println(cla3.getName()); //java.util.UUID
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //通过内省类获取 JavaBean 对象
            BeanInfo beanInfo = Introspector.getBeanInfo(VoteStudent.class);
        } catch (IntrospectionException e){
            e.printStackTrace();
        }



        try {
            //反射
            Class<?> voteClass = Class.forName("com.project.impel.VoteStudent");
            //反射实例化 JDK 1.9 之前
            //Object obj = voteClass.newInstance(); //只能"隐式"调用无参构造方法
            //反射调用指定参数构造方法
            Constructor<?> con = voteClass.getDeclaredConstructor(long.class, String.class, int.class);  //指定参数类型
            //反射实例化 JDK 1.9 之后
            Object obj = con.newInstance(6, "钟八", 0);  //"隐式"调用有参构造方法
            //获取指定方法，需要确认方法名称
            String setMethodName = "setVotes";
            Method setMethod = voteClass.getDeclaredMethod(setMethodName, int.class);  //获取指定方法
            //进行指定方法的调用
            int votes = 5; //赋值
            setMethod.invoke(obj, votes); //等价于：VoteStudent 类实例化对象.setVotes(votes)
            System.out.println(obj);  //6: 钟八[5]
            String getMethodName = "getName";
            Method getMethod = voteClass.getDeclaredMethod(getMethodName);
            System.out.println(getMethod.invoke(obj));  //等价于：VoteStudent 类实例化对象.getName()
            //获取类中指定属性
            Field fieldVote = voteClass.getDeclaredField("votes");
            fieldVote.setAccessible(true); //解除属性封装
            //如果没有上面的封装解除，以下的 set 与 get 方法都会报错
            fieldVote.set(obj, 8); //等价于 VoteStudent 类实例化对象.votes = 8;
            System.out.println(fieldVote.get(obj)); //等价于 VoteStudent 类实例化对象.votes;
            System.out.println(fieldVote.getType().getName()); //int
            System.out.println(fieldVote.getType().getSimpleName()); //int
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            Field field = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            //Unsafe 类绕过了 JVM 管理，可以获取私有构造的类的实例化对象
            Singleton instance = (Singleton) unsafe.allocateInstance(Singleton.class);
            instance.print(""); //******Singleton print 方法*******
        } catch (InstantiationException | ClassNotFoundException
                | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Class<?> classN = Class.forName("com.project.impel.HeiRiderImpel");
            String content = "name:Drive|age:27|year:2015-2016|birthday:2015-09-01|rider.period:Kuuga～Zi-o";
            HeiRiderImpel obj = ClassInstanceFactory.create(classN, content);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //使用自定义类加载器通过 类.class 文件获取类对象
            NetClassLoader ncl = new NetClassLoader();
            Class<?> nc = ncl.loadFileClass("com.project.impel.HeiRiderImpel");
            Object obj = nc.getDeclaredConstructor().newInstance();
            Method toStringMethod = nc.getDeclaredMethod("toString");
            System.out.println(toStringMethod.invoke(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            Class<?> sinCla = Singleton.class;

            Method printMethod = sinCla.getDeclaredMethod("print", String.class);
            //获取 自定义 Annotation
            NetAnnotation anno = printMethod.getAnnotation(NetAnnotation.class);
            String print = anno.content() + " (" + anno.isNeed() + ") " + anno.content();
            printMethod.invoke(sinCla.getDeclaredConstructor().newInstance(), print);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static class Singleton{
        private Singleton(){
            System.out.println("******Singleton 私有构造方法*******");
        }

        @NetAnnotation(clazz = Singleton.class)
        public void print(String content){
            System.out.println("******Singleton print 方法*******");
            System.out.println("print: " + content);
        }

    }


}
