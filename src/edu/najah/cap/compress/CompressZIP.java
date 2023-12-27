package edu.najah.cap.compress;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressZIP implements ICompress {

    @Override
    public  void compress(String path,String ...files) throws FileNotFoundException, IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path));
        for (String f :files) {
            File file = new File(f);
            ZipEntry entry = new ZipEntry(file.getName());
            zos.putNextEntry(entry);
            FileInputStream fis = new FileInputStream(file);
            int len;
            byte[] data = new byte[1024];
            while ((len = fis.read(data)) != -1) {
                zos.write(data, 0, len);
            }
            fis.close();
            zos.closeEntry();
        }
        zos.close();
        System.out.println("Finished Compress File");
    }



}

