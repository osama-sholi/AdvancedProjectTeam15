package edu.najah.cap.compress;

public class CompressFactory {
   public static ICompress getCompressType(CompressType type) {
       ICompress compressor = null;
       if (type.equals(CompressType.ZIP)) {
           compressor = new CompressZIP();
       }
       return compressor;
   }
    }
