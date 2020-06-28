package com.project.basic;

import java.io.*;

public class BasicSerialize {

    /**
     * 对象序列化：将内存中保存的对象以二进制数据流的形式进行处理，以实现对象的保存或网络传输
     * 序列化情况：
     *      * a）当把堆内存中的对象状态保存到一个文件中或者数据库中时候；
     *      * b）当用套接字在网络上传送对象的时候；
     *      * c）当通过RMI传输对象的时候
     */

    private static final String BASE_PATH = "/Users/murongyunge/Desktop/IntelliJ/JavaProject";

    public static void main(String[] args) {

        //创建文件
        String path = BASE_PATH.replaceAll("/", File.separator);

        File serializeFile = new File(path, File.separator + "res" + File.separator + "Rider.rider");

        //序列化对象
        Rider rider = new Rider("Driver", 27, "2015-2016");
        serializeObject(serializeFile, rider);

        //反序列化对象
        //System.out.println((Rider) deserializeObject(serializeFile));

    }

    /**
     * 序列化：java.io.ObjectOutputStream:
     *            public class ObjectOutputStream extends OutputStream implements ObjectOutput, ObjectStreamConstants{}
     *            构造方法：public ObjectOutputStream(OutputStream out) throws IOException{}
     *            序列化对象输出方法：public final void writeObject(Object obj) throws IOException{}
     */
    //序列化对象
    private static void serializeObject(File file, Object obj){
        if (file == null || obj == null){
            return;
        }
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            os = new FileOutputStream(file);
            oos = new ObjectOutputStream(os);
            //序列化对象
            oos.writeObject(obj);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (os != null){
                    os.close();
                }
                if (oos != null){
                    oos.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    /**
     * 反序列化：java.io.ObjectInputStream:
     *             public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants{}
     *             构造方法：public ObjectInputStream(InputStream in) throws IOException{}
     *             反序列化对象读取：public final Object readObject() throws IOException, ClassNotFoundException{}
     */
    //反序列化对象
    private static Object deserializeObject(File file){
        if (file == null || !file.exists()){
            return null;
        }
        InputStream is = null;
        ObjectInputStream ois = null;
        try {
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //todo 不用进行文件是否存在的判断，序列化会自动创建文件
            is = new FileInputStream(file);
            ois = new ObjectInputStream(is);
            //反序列化对象
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            try {
                if (is != null){
                    is.close();
                }
                if (ois != null){
                    ois.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    //序列化对象
    static class Rider implements Serializable{
        String name;
        int age;
        String year;

        public Rider(String name, int age, String year) {
            this.name = name;
            this.age = age;
            this.year = year;
        }

        @Override
        public String toString() {
            return "Rider{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", year='" + year + '\'' +
                    '}';
        }
    }

}
