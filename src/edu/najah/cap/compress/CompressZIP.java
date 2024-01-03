package edu.najah.cap.compress;

import edu.najah.cap.exceptions.FileDeletionException;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static edu.najah.cap.logs.MyLogging.log;

public class CompressZIP implements ICompress {

        @Override
        public void compress(String path, List<String> files) throws IOException {
            log(Level.INFO, "Compressing to ZIP", "CompressZIP", "compress");
            String outputPath = path + "UserData.zip";
            try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(outputPath))) {
                for (String f : files) {
                    try {
                        File file = new File(f);
                        ZipEntry entry = new ZipEntry(file.getName());
                        zipOutputStream.putNextEntry(entry);
                        if (!file.exists()) {
                            throw new FileNotFoundException("File not found: " + f);
                        }
                        try (FileInputStream fileInputStream = new FileInputStream(file)) {
                            int len;
                            byte[] data = new byte[1024];
                            while ((len = fileInputStream.read(data)) != -1) {
                                zipOutputStream.write(data, 0, len);
                            }
                        } catch (IOException e) {
                            log(Level.SEVERE, e.getMessage(), "CompressZIP", "compress");
                        } finally {
                            zipOutputStream.closeEntry();
                        }
                        try {
                            if (!file.delete()) {
                                throw new FileDeletionException("File Deletion Failed");
                            }
                        } catch (FileDeletionException e) {
                            log(Level.SEVERE, e.getMessage(), "CompressZIP", "compress");
                        }
                    }catch (NullPointerException e){
                    }
                }
                log(Level.INFO, "Compressing Completed", "CompressZIP", "compress");
            }
        }
    }