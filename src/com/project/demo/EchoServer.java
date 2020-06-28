package com.project.demo;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Echo 程序 服务器端：单线程
 */
public class EchoServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        Scanner scanner = null;
        PrintStream print = null;
        try {
            //设置服务器端接听端口
            server = new ServerSocket(8888);
            System.out.println("服务器端Socket创建成功");
            System.out.println("等待客户端的连接请求");
            // 有客户端连接
            client = server.accept();
            //接收并处理客户端发送的信息，再将处理后的信息发送回客户端
            scanner = new Scanner(client.getInputStream());  //客户端输入流
            scanner.useDelimiter("\n");  //设置分隔符
            print = new PrintStream(client.getOutputStream()); //客户端输出流
            boolean flag = true;
            while (flag){
                if (scanner.hasNext()){
                    String con = scanner.next().trim();
                    if (!con.equalsIgnoreCase("bye")){
                        print.println("Echo Server: " + con);
                    } else {
                        print.println("Echo Server End!");
                        flag = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null){
                    server.close();
                }
                if (client != null){
                    client.close();
                }
                if (scanner != null){
                    scanner.close();
                }
                if (print != null){
                    print.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
