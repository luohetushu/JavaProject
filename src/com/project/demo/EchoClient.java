package com.project.demo;

import com.project.utils.InputUtils;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Echo 程序 客户端
 */
public class EchoClient {
    public static void main(String[] args) {
        Socket client = null;
        Scanner scanner = null;
        PrintStream print = null;
        try {
            //客户端连接服务器端
            client = new Socket("localhost", 8888);
            scanner = new Scanner(client.getInputStream());  //接收服务器端的输入内容
            scanner.useDelimiter("\n");
            print = new PrintStream(client.getOutputStream());  //向服务器发送内容
            boolean flag = true;
            while (flag){
                String content = InputUtils.getInputString("请输入发送给服务器端的内容：").trim();
                print.println(content);
                if (scanner.hasNext()){  //如果服务器端有回应，有数据回送
                    System.out.println(scanner.next());
                }
                if (content.equalsIgnoreCase("bye")){
                    flag = false;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
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
