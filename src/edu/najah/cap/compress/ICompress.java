package edu.najah.cap.compress;

import java.io.IOException;
import java.util.List;

public interface ICompress {
     public void  compress(String path, List<String> files) throws IOException;

}
