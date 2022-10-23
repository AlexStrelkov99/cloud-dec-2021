package com.geekbrains.chat.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Example {
    public static void main(String[] args) {
        File file = new File("files/foto.jpg");
        byte[] buf = new byte[8192];
        try(FileInputStream is = new FileInputStream(file)){
            int read;
            try(FileOutputStream os = new FileOutputStream("files/copy.jpg")){
                while ((read = is.read(buf)) != -1){
                    os.write(buf, 0, read);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
