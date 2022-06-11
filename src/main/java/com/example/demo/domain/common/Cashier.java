package com.example.demo.domain.common;

import java.io.*;
import java.util.Date;

public class Cashier {
    private String fileName;
    private String path;
    private BufferedWriter writer;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public void openFile() throws IOException{
        File targetDir = new File(path);
        System.out.println(targetDir);
        if (!targetDir.exists()){
            targetDir.mkdir();
        }
        File checkoutFile = new File(path, fileName+".txt");
        if (!checkoutFile.exists()){
            checkoutFile.createNewFile();
        }
        System.out.println(checkoutFile.getAbsolutePath());
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(checkoutFile,true)));
    }

    public void checkout(ShoppingCart cart) throws  IOException{
        writer.write(new Date()+"\t"+ cart.getItems()+"\r\n");
        writer.flush();
        System.out.println("Open file");
    }

    public void closeFile() throws IOException {
        writer.close();
        System.out.println("Close file");
    }
}
