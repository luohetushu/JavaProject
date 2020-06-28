package com.project.demo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Echo 程序 服务器端：多线程，一个线程封装一个客户端（BIO 模型）
 */
public class EchoServerThreads {
    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            //设置服务器端接听端口
            server = new ServerSocket(8888);
            System.out.println("服务器端Socket创建成功");
            System.out.println("等待客户端的连接请求....");

            while (true){
                // 有客户端连接
                Socket client = server.accept();
                new Thread(new ClientThread(client), "客户端连接").start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null){
                    server.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    static class ClientThread implements Runnable{

        Socket client = null;
        Scanner scanner = null;
        PrintStream print = null;
        boolean flag;

        public ClientThread(Socket client){
            try {
                this.client = client;
                //接收并处理客户端发送的信息，再将处理后的信息发送回客户端
                this.scanner = new Scanner(client.getInputStream());  //客户端输入流
                this.scanner.useDelimiter("\n");  //设置分隔符
                this.print = new PrintStream(client.getOutputStream()); //客户端输出流
                this.flag = true;
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (this.flag){
                if (this.scanner.hasNext()){
                    String con = this.scanner.next().trim();
                    if (!con.equalsIgnoreCase("bye")){
                        this.print.println("Echo Server: " + con);
                    } else {
                        this.print.println("Echo Server End!");
                        this.flag = false;
                    }
                }
            }
            try {
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
