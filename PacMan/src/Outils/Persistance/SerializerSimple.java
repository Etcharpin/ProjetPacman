package Outils.Persistance;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SerializerSimple {



    public  static <T extends Serializable> String serialize(T obj) {
        try(
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        ){
            outputStream.writeObject(obj);

            return new String(Base64.getEncoder().encode(byteArrayOutputStream.toByteArray()), StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static  <T extends Serializable> T deseririalize(String s) {

        byte[] data = Base64.getDecoder().decode(s);

        try(
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
                ){
            return (T) inputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }
}
