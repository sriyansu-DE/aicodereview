package com.codereviewer.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class FileUtils {
  private FileUtils(){

  }

  public static String readFile(Path path)
    throws IOException{

      if(!Files.exists(path)){

        throw new IOException(

          "File does not exist: "
                + path);
        

      }

      if(!path.toString()
              .endsWith(".java")){
            
            
        throw new IOException(

          "Only .java files are supported."
        );  
      }

      return Files.readString(
        path,
        StandardCharsets.UTF_8
      );
    
  }
}
