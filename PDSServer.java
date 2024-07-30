package com.test.sku.network.pds;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PDSServer 
{
    public static void main(String[] args) 
    {
        try (ServerSocket serverSocket = new ServerSocket(1234)) 
        {
            while (true) 
            {
                System.out.println("서버 대기중...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("클라이언트 접속됨");
                new ServerThread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

