import java.io.File;
import java.io.IOException;

public class LogActividad3 {
    public static void main(String[] args) throws IOException {

        File archivo = new File("segurdidad_actvidad3.log");

        try {
            archivo.createNewFile();

        }catch(IOException e){}
    }
}
