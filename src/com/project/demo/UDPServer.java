package com.project.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP 程序 服务器端，用于发送数据信息
 */
public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(9999); //监听端口，连接指定端口
            String content = "I am Icon Man!";
            DatagramPacket packet = new DatagramPacket(content.getBytes(), 0, content.length(),
                    InetAddress.getByName("localhost"), 8888);  //包含发送内容的数据包 端口与客户端一致
            server.send(packet); //发送
            System.out.println("信息发送完毕....");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.close();
            }
        }
    }
}
