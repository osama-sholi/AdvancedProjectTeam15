package edu.najah.cap.compress;

public class CompressFactory {
   public static CompressZIP getCompressType(CompressType type) {
       CompressZIP compressZIP = null;
       if (type.equals(CompressType.ZIP)) {
           compressZIP = new CompressZIP();
       }
       return compressZIP;
   }
    }
