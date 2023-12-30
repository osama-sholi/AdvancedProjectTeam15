package edu.najah.cap.compress;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.logging.*;

public class CompressZIP implements ICompress {


        private static final Logger logger = Logger.getLogger(CompressZIP.class.getName());

        @Override
        public void compress(String path, String... files) {
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(path));
                for (String f : files) {
                    File file = new File(f);
                    if (!file.exists()) {
                        logger.warning("File not found: " + f);
                        continue;
                    }

                    ZipEntry entry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(entry);

                    try (FileInputStream fileInputStream = new FileInputStream(file)) {
                        int len;
                        byte[] data = new byte[1024];
                        while ((len = fileInputStream.read(data)) != -1) {
                            zipOutputStream.write(data, 0, len);
                        }
                    } catch (IOException e) {
                        logger.severe("Error reading file: " + f);
                        e.printStackTrace();
                    } finally {
                        zipOutputStream.closeEntry();
                    }
                }
                zipOutputStream.close();
                logger.info("Finished Compressing Files");
            } catch (FileNotFoundException e) {
                logger.severe("Error creating the zip file at path: " + path);
                e.printStackTrace();
            } catch (IOException e) {
                logger.severe("Error compressing files to the zip file");
                e.printStackTrace();
            }
        }
    }