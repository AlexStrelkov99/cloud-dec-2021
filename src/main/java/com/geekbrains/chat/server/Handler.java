package com.geekbrains.chat.server;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Handler implements Runnable {

    private boolean running;
    private final byte[] buf;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Socket socket;

    public Handler(Socket socket) throws IOException {
        running = true;
        buf = new byte[8192];
        this.socket = socket;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }

    public void stop(){
        running = false;
    }

    @Override
    public void run() {
        try {
            while (running) {
                int read = inputStream.read(buf);
                String message = new String(buf, 0, read)
                        .trim();
                if(message.equals("quit")){
                    outputStream.write("Client disconnected\n".getBytes(StandardCharsets.UTF_8));
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                    break;
                }
                System.out.println("Received: " + message  );
                outputStream.write((message + "\n").getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
