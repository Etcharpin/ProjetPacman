package Outils.Persistance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtilSimple {

    public static void createIfDoesNotExist(String path) {

        try {
            File file = new File(path);
            if(file.exists()) return;

            file.getParentFile().mkdirs();
            file.createNewFile();
        }catch ( IOException e){
            e.printStackTrace();
        }
    }

    public  static String readFile(String path) {

        try{
            return Files.readString(Paths.get(path));
        }
        catch ( IOException e){
            e.printStackTrace();
        }
        return "";
    }


    public static void saveFile(String path, String content) {

        try{
            createIfDoesNotExist(path);

            Files.writeString(Paths.get(path), content);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
