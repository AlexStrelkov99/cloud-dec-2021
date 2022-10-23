package com.geekbrains.chat.client;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class IoNet implements Closeable {

    private final Callback callback;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final byte[] buf;

    public IoNet(Callback callback, Socket socket) throws IOException {
        this.callback = callback;
        this.socket = socket;
        inputStream = this.socket.getInputStream();
        outputStream = this.socket.getOutputStream();
        buf = new byte[8192];
        Thread readThread = new Thread(this::readMessage);
        readThread.setDaemon(true);
        readThread.start();
    }
   public void sendMsg(String msg) throws IOException {
        outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    private void readMessage()  {
       try{
           while (true) {
               int read = inputStream.read(buf);
               String msg = new String(buf, 0, read).trim();
               callback.onReceive(msg);
           }
       } catch (IOException e) {
           e.printStackTrace();
       }


    }
    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
