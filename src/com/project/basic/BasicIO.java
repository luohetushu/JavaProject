package com.project.basic;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BasicIO {

    /**
     * 数据流    在 Java 中所有数据都是使用流读写的
     * 数据流相关的类都封装在 java.io 包中，且每个数据流都是一个对象
     *
     * 数据流是 Java 进行 I/O 操作的对象，按照不同的标准分为不同的类别：
     *  1、按照流的方向主要分为输入流和输出流两大类。
     *     输入流：InputStream(字节输入流)、Reader(字符输入流)
     *     输出流：OutputStream(字节输入流)、Writer(字符输入流)
     *         区别：1、OutputStream 如果没有使用 close() 方法，数据内容依然可以覆盖或追加；而 Writer 必须使用 close() 方法，
     *                 因为Writer 使用了缓冲区，此时可以使用 flush() 方法强制将缓冲区的内容写入输出流
     *  2、数据流按照数据单位的不同分为字节流和字符流。
     *    字节流：字节流按 8 位传输以字节为单位输入输出数据
     *    字符流：字符流按 16 位传输以字符为单位输入输出数据
     *    区别：1、字节流在处理时不会使用缓冲区，而字符流会使用缓冲区
     *         2、使用字符流更适合中文数据的处理
     *  3、按照功能可以划分为节点流和处理流
     *
     *  BIO：Block IO 同步阻塞式 IO，即平常使用的传统 IO，特点是模式简单使用方便，并发处理能力低。
     *  NIO：New IO 同步非阻塞 IO，是传统 IO 的升级，客户端和服务器端通过 Channel（通道）通讯，实现了多路复用。
     *  AIO：Asynchronous IO 是 NIO 的升级，也叫 NIO2，实现了异步非堵塞 IO ，异步 IO 的操作基于事件和回调机制
     */

    /**
     * java.io.OutputStream: 字节输出流  JDK 1.0
     *     public abstract class OutputStream implements Closeable, Flushable{}
     *     注：抽象类的实例化需要通过子类的向上转型完成
     * 输出方法：
     * 1、public abstract void write(int var1) throws IOException;  //输出单个字节数据
     * 2、public void write(byte[] b) throws IOException{}          //输出一组字节
     * 3、public void write(byte[] b, int off, int len) throws IOException{}    //输出部分字节数据
     */

    /**
     * java.io.InputStream: 字节输入流  JDK 1.0
     *     public abstract class InputStream implements Closeable{}
     * 读取方法：
     * 1、public abstract int read() throws IOException;  //读取单个字节数据，如果遇到输入流的结尾返回 -1
     * 2、public int read(byte[] b) throws IOException{}  //读取一组字节数据，返回数据的个数；如果没有数据，返回-1
     * 3、public int read(byte[] b, int off, int len) throws IOException{}  //读取一组字节部分数据
     * JDK 1.9 之后，新增的读取方法：
     *     public byte[] readAllBytes() throws IOException{} //建议读取内容小于 10K，避免缓存区的创建
     * 4、public long transferTo(OutputStream out) throws IOException{}  //全部读取
     */

    /**
     * java.io.Writer 类：字符输出流 JDK 1.1
     *     public abstract class Writer implements Appendable, Closeable, Flushable{}
     * 输出方法：
     * 1、public void write(char[] cbuf) throws IOException{}  //输出字符数组
     * 2、public void write(String str) throws IOException{}   //输出字符串
     */

    /**
     * java.io.Reader 类：字符输入流 JDK 1.1
     *     public abstract class Reader implements Readable, Closeable{}
     * 读取方法：
     * 1、public int read(char[] cbuf) throws IOException{}  //读取字符数组
     */

    /**
     * 流的转换：实现字符流与字节流功能的转换，将接收到的字节流对象转换成字符流对象
     * java.io.OutputStreamWriter 类：JDK 1.1
     *     public class OutputStreamWriter extends Writer{}
     *     构造方法：
     *         public OutputStreamWriter(OutputStream out){}
     * java.io.InputStreamReader 类： JDK 1.1
     *     public class InputStreamReader extends Reader{}
     *     构造方法：
     *         public InputStreamReader(InputStream in)
     * */

    private static final String BASE_PATH = "/Users/murongyunge/Desktop/IntelliJ/JavaDemo";

    public static void main(String[] args) {

        //创建文件
        String path = BASE_PATH.replaceAll("/", File.separator);
        File file = new File(path, File.separator + "res" + File.separator + "read_me.txt");
        if (!file.getParentFile().exists()){ //父路径是否存在
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        //输入内容至指定文件  //手动关闭资源
        //outputFileAutoClose(file);

        //输入内容至指定文件  //手动关闭资源
        //writerFileWithClose(file);

        //读取文件内容 //手动关闭资源
        //inputFileAutoClose(file);

        //读取文件内容 //手动关闭资源
        readerFileWithClose(file);

        /**
         * 打印出本机所有属性
         * 1、文件路径分隔符：file.separator=/
         * 2、文件默认编码：file.encoding=UTF-8
         */
        //System.getProperties().list(System.out);

        //内存操作流
        memoryOperate();

        //管道流：主要功能是实现两个线程之间的 IO 操作
        try {
            SendThread srcThead = new SendThread();
            ReceiveThread snkThread = new ReceiveThread();
            srcThead.getSrc().connect(snkThread.getSnk());  //进行管道链接
            //new Thread(srcThead, "发送线程").start();
            //new Thread(snkThread, "接收线程").start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //大文件操作流，
        File bigFile = new File(path, File.separator + "res" + File.separator + "BigFile.txt");
        //bigFileOperate(bigFile);

        //使用打印流输出各种类型数据
        //printData(bigFile);

        //实现键盘数据输入
        //keyboardInput();

        //实现键盘数据输入
        //keyboardScannerInput();
        //文件读取
        scannerFile(bigFile);


    }

    /**
     * java.io.FileOutputStream 类：文件字节输出流
     *     public class FileOutputStream extends OutputStream{}
     * 构造方法：
     *     1、public FileOutputStream(File file) throws FileNotFoundException {
     *           this(file, false);
     *       }   //创建一个文件输出流，参数 file 指定目标文件，将数据覆盖目标文件的内容
     *     2、public FileOutputStream(File file, boolean append) throws FileNotFoundException{}
     *           //是否将数据添加到目标文件的内容末尾，如果为 true，则在末尾添加；如果为 false，则覆盖原有内容；其默认值为 false
     */
    //输入内容至指定文件  //手动关闭资源
    private static void outputFileWithClose(File file){
        if (file == null){
            return;
        }
        try {
            //输入内容至指定文件
            OutputStream os = new FileOutputStream(file); //向上转型
            String content = "复习查看：\n1、关键文件：BaseInterface、BaseKeyword、BasicTool；\n" +
                    "2、关键代码：BasicFile.java、BasicIO.java、BasicTool.java\n";
            //在写入的时候，如果文件不存在，会自动创建
            os.write(content.getBytes()); //将字符串转为字节数组 写入数据
            os.close();  //手动关闭资源
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //输入内容至指定文件  //自动关闭资源
    private static void outputFileAutoClose(File file){
        if (file == null){
            return;
        }
        //向上转型
        try(OutputStream os = new FileOutputStream(file)) {
            String content = "复习查看：\n1、关键文件：BaseInterface、BaseKeyword、BasicTool；\r\n" +
                    "2、关键代码：BasicFile.java、BasicIO.java、BasicTool.java\n";
            //在写入的时候，如果文件不存在，会自动创建
            os.write(content.getBytes(StandardCharsets.UTF_8)); //设置编码，将字符串转为字节数组 写入数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * java.io.FileInputStream 类：文件字节输入流
     *     public class FileInputStream extends InputStream {}
     * 构造方法：
     *    1、public FileInputStream(File file) throws FileNotFoundException{}  //读取指定文件内容
     *
     */
    //读取文件内容 //手动关闭资源
    private static void inputFileWithClose(File file){
        if (file == null){
            return;
        }
        try {
            InputStream is = new FileInputStream(file);
            byte[] result = new byte[1024];  //开辟一缓冲区存储读取的数据
            int length = is.read(result);
            System.out.println(new String(result, 0, length));
            is.close(); //手动关闭资源
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //读取文件内容 //自动关闭资源
    private static void inputFileAutoClose(File file){
        if (file == null){
            return;
        }
        try(InputStream is = new FileInputStream(file)) {
            byte[] result = new byte[1024];  //开辟一缓冲区存储读取的数据
            int length = is.read(result);
            System.out.println(new String(result, 0, length));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * java.io.FileWriter 类：文件字符输出流
     *     public class OutputStreamWriter extends Writer{}
     *         public class FileWriter extends OutputStreamWriter{}
     * 构造方法：
     *     1、public FileWriter(File file) throws IOException {
     *         super(new FileOutputStream(file));
     *       }   //创建一个文件字符输出流，参数 file 指定目标文件，将数据覆盖目标文件的内容
     *     2、public FileWriter(File file, boolean append) throws IOException {
     *         super(new FileOutputStream(file, append));
     *       }  //是否将数据添加到目标文件的内容末尾，如果为 true，则在末尾添加；如果为 false，则覆盖原有内容；其默认值为 false
     */
    //输入内容至指定文件  //手动关闭资源
    private static void writerFileWithClose(File file){
        if (file == null){
            return;
        }
        try {
            Writer writer = new FileWriter(file, true);
            writer.write("3、关键内容：多线程、比较器、设计模式，等等\r\n");
            writer.append("4、要根据关键内容想起关键代码啊，加油啊\n");   //追加数据
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * java.io.FileReader 类：文件字符输入流
     *     public class InputStreamReader extends Reader{}
     *         public class FileReader extends InputStreamReader{}
     * 构造方法：
     *     1、public FileReader(File file) throws FileNotFoundException {
     *         super(new FileInputStream(file));
     *       }   //创建一个文件字符输入流，参数 file 指定目标文件，将数据覆盖目标文件的内容
     *
     */
    //读取文件内容 //手动关闭资源
    private static void readerFileWithClose(File file){
        if (file == null){
            return;
        }
        try {
            Reader reader = new FileReader(file);
            char[] result = new char[1024];  //开辟一缓冲区存储读取的数据
            int length = reader.read(result);
            System.out.println(new String(result, 0, length));
            reader.close(); //手动关闭资源
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 大文件操作流：java.io.RandomAccessFile，实现对大文件的跳跃式读取，即可读取中间部分的内容，前提是大文件的数据保存位数明确
     *             public class RandomAccessFile implements DataOutput, DataInput, Closeable{}
     *             构造方法：public RandomAccessFile(String name, String mode) throws FileNotFoundException{}
     *                     文件处理模式：mode 为 r（可读）、rw（可读可写）
     * 因为文件保存的前提，所有的数据按固定长度保存，所以 java.io.RandomAccessFile 在数据读取上可以进行跳字节读取：
     *            public int skipBytes(int n) throws IOException {}   //向下跳过多少字节
     *            public void seek(long pos) throws IOException {}   //往回跳 其中 seek(0) 为跳回原点
     */
    //大文件操作流，
    private static void bigFileOperate(File file){
        if (file == null){
            return;
        }
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //大文件操作流
        RandomAccessFile raf = null;
        try {
            if (!file.exists()){
                file.createNewFile();
            }

            //大文件操作流
            raf = new RandomAccessFile(file, "rw");
            //大文件的数据保存位数明确
            String[] names = new String[]{"ZhangSan", "WangWu  ", "LiSi    "};
            int[] ages = new int[]{20, 25, 28};
            //写入数据
            for (int i = 0; i < names.length; i++) {
                raf.write((names[i] + " is " + ages[i] + " years old.\n").getBytes());
            }
            //读取数据
            raf.seek(0); //回到原点
            raf.skipBytes(26);  //跳过多少字节
            byte[] data = new byte[26];
            int len = raf.read(data);
            System.out.println(len);
            System.out.println(new String(data, 0 , len));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null){
                    raf.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 内存操作流：需要实现 IO 操作，但又不能产生文件
     * 1、字节内存操作流：java.io.ByteArrayOutputStream: JDK 1.0
     *                     public class ByteArrayOutputStream extends OutputStream{}
     *                     构造方法：public ByteArrayOutputStream() { this(32); }
     *                 java.io.ByteArrayInputStream:  JDK 1.0
     *                     public class ByteArrayInputStream extends InputStream{}
     *                     构造方法：public ByteArrayInputStream(byte[] buf){}
     *                     获取内存中所有数据的方法：
     *                         1、public synchronized byte[] toByteArray();
     *                         2、public synchronized String toString();
     * 2、字符内存操作流：java.io.CharArrayWriter:  JDK 1.1
     *                     public class CharArrayWriter extends Writer
     *                 java.io.CharArrayReader:  JDK 1.1
     *                     public class CharArrayReader extends Reader
     */
    private static void memoryOperate(){
        String str = "www.separator.com";
        InputStream is = new ByteArrayInputStream(str.getBytes()); //将数据保存在内存
        //OutputStream os = new ByteArrayOutputStream();  //读取内存中的数据
        ByteArrayOutputStream os = new ByteArrayOutputStream();  //读取内存中的数据
        int data = 0;
        try {
            while ((data = is.read()) != -1) {
                //os.write(Character.toUpperCase((char)data));  //将数据转为大写，此时数据还在内存中
                os.write(data);
            }
            //is.transferTo(os); //全部读取
            byte[] result = os.toByteArray();  //使用子类调用自己的扩展方法
            System.out.println(new String(result));  //WWW.SEPARATOR.COM
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 管道流：主要功能是实现两个线程之间的 IO 操作
     * 1、字节管道流：java.io.PipedOutputStream  JDK 1.0
     *                  public class PipedOutputStream extends OutputStream{}
     *                  构造方法：public PipedOutputStream() {}
     *                          public PipedOutputStream(PipedInputStream snk) throws IOException {this.connect(snk);}
     *              java.io.PipedInputStream  JDK 1.0
     *                  public class PipedInputStream extends InputStream{}
     *                  构造方法：public PipedInputStream() {this.in = -1;this.out = 0;this.initPipe(1024);}
     *                          public PipedInputStream(PipedOutputStream src) throws IOException {this(src, 1024);}
     * 2、字符管道流:java.io.PipedWriter  JDK 1.1
     *                 public class PipedWriter extends Writer
     *                 构造方法：public PipedWriter() {}
     *                          public PipedWriter(PipedReader snk) throws IOException {this.connect(snk);}
     *              java.io.PipedReader  JDK 1.1
     *                 public class PipedReader extends Reader {}
     *                 构造方法：public PipedReader() {this.in = -1;this.out = 0;this.initPipe(1024);}
     *                          public PipedReader(PipedWriter src) throws IOException {this(src, 1024);}
     */
    //实现两个线程，发送线程和接收线程
    static class SendThread implements Runnable{  //发送线程

        PipedOutputStream src;  //管道输出流

        public SendThread() {
            this.src = new PipedOutputStream();
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    this.src.write(("第" + (i + 1) + "次发送信息：该信息来自发送线程，即=="
                            + Thread.currentThread().getName()+ "\n").getBytes());
                }
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                try {
                    this.src.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        public PipedOutputStream getSrc() {
            return src;
        }
    }
    static class ReceiveThread implements Runnable{  //接收线程

        PipedInputStream snk;  //管道输入流

        public ReceiveThread() {
            this.snk = new PipedInputStream();
        }

        @Override
        public void run() {
            ByteArrayOutputStream baos = null;
            try {
                baos = new ByteArrayOutputStream(); //将所有数据保存到内存输出流
                byte[] data = new byte[1024];
                int length;
                while ((length = this.snk.read(data)) != -1){
                    baos.write(data, 0, length); //将数据保存到内存输出流
                }
                System.out.println("接收线程，即==" + Thread.currentThread().getName() + "，接收消息："
                        + baos.toString());
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                try {
                    if (baos != null){
                        baos.close();
                    }
                    this.snk.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        public PipedInputStream getSnk() {
            return snk;
        }
    }

    /**
     * 打印流：1、java.io.PrintStream：JDK 1.0
     *           public class FilterOutputStream extends OutputStream{}
     *              public class PrintStream extends FilterOutputStream implements Appendable, Closeable{}
     *              构造方法：public PrintStream(OutputStream out) { this(out, false); }
     *        2、java.io.PrintWriter：JDK 1.1
     *           public class PrintWriter extends Writer{}
     *           构造方法：1、public PrintWriter(Writer out) { this(out, false); }
     *                    2、public PrintWriter(OutputStream out) { this(out, false); }
     *                    3、public PrintWriter(String fileName, String csn)
     *                      throws FileNotFoundException, UnsupportedEncodingException { this(toCharset(csn), new File(fileName)); }
     *                      // 指定文件路径，与编码
     *           JDK 1.5 之后，支持格式化输出：
     *                    public PrintWriter printf(String format, Object... args) { return this.format(format, args); }
     */
    //使用打印流输出各种类型数据
    private static void printData(File file){
        if (file == null){
            return;
        }
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //打印流
        PrintWriter pw = null;
        PrintStream ps = null;
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            //打印流
            pw = new PrintWriter(new FileOutputStream(file, true));  //拼接
            //在文件中打印输出各种类型数据
            pw.print("ZhaoLiu  is ");
            pw.print(24);
            pw.println(" years old.");
            //格式化输出
            String name = "ZhongBa ";
            int age = 27;
            pw.printf("%s is %d years old.", name, age);
            ps = new PrintStream(new FileOutputStream(file, true));  //拼接
            ps.print("QianQi   is ");
            ps.print(26);
            ps.println(" years old.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ps != null){
                ps.close();
            }
            if (pw != null){
                pw.close();
            }
        }
    }

    /**
     * 缓冲流：需要实现 IO 操作，但又不能产生文件
     * 1、字节缓冲流：java.io.BufferedOutputStream: JDK 1.0
     *                  public class FilterOutputStream extends OutputStream {}
     *                     public class BufferedOutputStream extends FilterOutputStream{}
     *                     构造方法：public BufferedOutputStream(OutputStream out) { this(out, 8192); }
     *              java.io.BufferedInputStream:  JDK 1.0
     *                  public class FilterInputStream extends InputStream{}
     *                     public class BufferedInputStream extends FilterInputStream{}
     *                     构造方法：public BufferedInputStream(InputStream in) { this(in, DEFAULT_BUFFER_SIZE); }
     * 2、字符缓冲流：java.io.BufferedWriter:  JDK 1.1
     *                     public class BufferedWriter extends Writer{}
     *              java.io.BufferedReader:  JDK 1.1
     *                     public class BufferedReader extends Reader{}
     *              JDK 1.5 之前常用类，读取一行数据：
     *                     public String readLine() throws IOException { return this.readLine(false); }
     */
    //实现键盘数据输入
    private static void keyboardInput(){
        try {
            //键盘输入
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入任意数据：");
            String result = br.readLine();
            System.out.println("键盘输入的数据为：" + result);
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 扫描流:java.util.Scanner: JDK 1.5  BufferReader 替代功能类
     *       public final class Scanner implements Iterator<String>, Closeable{}
     *       构造方法：public Scanner(InputStream source){}
     *       方法：判断是否有数据：public boolean hasNext()
     *            取出数据：public String next()
     *                     public int nextInt()
     *            设置分隔符：public Scanner useDelimiter(Pattern pattern) {}
     */
    //实现键盘数据输入
    private static void keyboardScannerInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入任意整数：");
        if (scanner.hasNextInt()){
            int data = scanner.nextInt();
            System.out.println("输入的整数为" + data);
        } else {
            System.out.println("请输入整数");
        }
        System.out.print("请输入yyyy-MM-dd样式：");
        String regex = "\\d{4}-\\d{2}-\\d{2}";
        if (scanner.hasNext(regex)) { //配合正则验证
            String data = scanner.next(regex);
            try {
                System.out.println("输入的样式为" + new SimpleDateFormat("yyyy-MM-dd").parse(data));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("请输入正确样式");
        }
        scanner.close();
    }
    //文件读取
    private static void scannerFile(File file){
        if (file == null || !file.exists()){
            return;
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            scanner.useDelimiter("\n");  //设置换行符
            while (scanner.hasNext()){  //读取文件全部内容
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null){
                scanner.close();
            }
        }

    }

}
