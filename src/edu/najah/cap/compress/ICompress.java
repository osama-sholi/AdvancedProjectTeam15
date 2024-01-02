package edu.najah.cap.compress;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ICompress {
     public void  compress(String path,String ...files)throws FileNotFoundException, IOException;

}
