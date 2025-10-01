import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Descripcion: Ejemplo para lanzar un proceso
 * @author Gilber
 * @version 1.0
 * @since 30/09/2025
 */

public class Proceso {
    public static void main(String[] args) {

        try {
            //1ºCreamos el proceso
            ProcessBuilder pb = new ProcessBuilder("ping", "8.8.8.8");
          //2ºInicializamos el proceso
            Process p = pb.start();

            //3ºCreamos BufferedReader
          BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

          //4ºLeemos linea a linea
          String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        }catch(IOException e){
            throw new RuntimeException();
        }
    }
}
