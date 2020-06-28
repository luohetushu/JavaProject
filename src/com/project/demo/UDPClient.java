package com.project.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP 程序 客户端，用于接收数据信息
 */
public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket client = null;
        try {
            client = new DatagramSocket(8888); //连接指定端口
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);  //接收信息内容的数据包，保存在 data 中
            System.out.println("客户端等待接收消息....");
            client.receive(packet); //数据保存在 data 中
            System.out.println(new String(data, 0, packet.getLength()));
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
