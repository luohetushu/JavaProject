package com.project.basic;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 网络编程 java.net 包
 */
public class BasicNetwork {

    /**
     * 网络编程：指多台主机之间的数据通讯操作，该操作分客户端与服务器端
     * 网络分类：按地理分类
     *     1、局域网（LocalArea Network）简称 LAN，是一种在小范围内实现的计算机网络，一般在一个建筑物内或者一个工厂、一个事业单位内部独有，范围较小。
     *     2、城域网（Metropolitan Area Network）简称为 MAN，一般是一个城市内部组建的计算机信息网络，提供全市的信息服务。
     *     3、广域网（Wide Area Network）简称为 WAN，范围很广，可以分布在一个省、一个国家或者几个国家。
     *     4、因特网（Internet）则是由无数的 LAN 和 WAN 组成的
     * 网络编程模型：
     *     1、客户端/服务器（Client/Server）模式，简称 C/S 模式；
     *     2、浏览器/服务器（Browser/Server）模式，简称 B/S 模式
     * 网络协议：
     *     1、IP 协议：Internet Protocol（网络之间互联的协议）
     *        命名 IP 地址的规定是 IPv4 协议，该协议规定每个 IP 地址由 4 个 0~255 的数字组成
     *     2、TCP/IP 协议：Transmission Control Protocol/Internet Protocol
     *        Internet 网络中采用的协议是 TCP/IP 协议
     *        TCP/IP 协议采用了 4 层的层级结构，分别是应用层、传输层、网络层和网络接口层
     *     3、TCP 与 UDP 协议：
     *        TCP/IP 协议的传输层同时存在 TCP（Transmission Control Protocol，传输控制协议）和 UDP（User Datagram Protocol，用户数据报协议）两个协议
     */

    /**
     * TCP 程序开发，网络编程程序最基本的开发模型
     *     服务器端：java.net.ServerSocket:
     *                 public class ServerSocket implements Closeable {}
     *                 构造方法：public ServerSocket(int port) throws IOException {}
     *     客户器端：java.net.Socket:
     **                 public class Socket implements Closeable {}
     *                  构造方法：public Socket(String host, int port) throws UnknownHostException, IOException {}
     */

    /**
     * UDP 程序开发：基于数据报端网络编程实现
     *     数据包：java.net.DatagramPacket
     *                public final class DatagramPacket {}
     *     网络发送与接收：java.net.DatagramSocket
     *                public class DatagramSocket implements Closeable {}
     *                构造方法：public DatagramSocket(int port) throws SocketException {} //连接指定端口
     */


}
