package com.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerIo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8189);
        System.out.println("Server started ...");
        try {
        while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("Client connected ...");
                new Thread(new Handler(socket)).start();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
