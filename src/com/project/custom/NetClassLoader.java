package com.project.custom;

import java.io.*;

public class NetClassLoader extends ClassLoader {

    private static final String CLASS_PATH = "/Users/murongyunge/Desktop/IntelliJ/JavaProject";

    /**
     * 进行指定类的加载
     * @param className 类完整名称，"包.类"
     * @return 返回指定类的对象
     */
    public Class<?> loadFileClass(String className) throws ClassNotFoundException{
        byte[] data = this.loadClassData(); //读取二进制文件
        if (data != null){
            return super.defineClass(className, data, 0, data.length);
        }
        return null;
    }

    /**
     * 通过文件进行类的加载
     * @return
     */
    private byte[] loadClassData() {
        String path = CLASS_PATH.replaceAll("/", File.separator);
        File file = new File(path, File.separator + "res" + File.separator + "HeiRiderImpel.class");
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(file);
            baos = new ByteArrayOutputStream();
            is.transferTo(baos);  //读取全部数据
            return baos.toByteArray();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (is != null){
                    is.close();
                }
                if (baos != null){
                    baos.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
