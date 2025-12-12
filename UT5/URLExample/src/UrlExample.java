import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlExample {
    public static void main(String[] args) {


        try {
            // Crear un objeto URL que apunte a un recurso HTTP
            URL url = new URL("http://api.open-notify.org/astros.json");


            //Abrir conexion y obtener un flujo de entrada inputStream
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                System.out.println("Respuesta del servidor" + "\n");

                //Leer el contenido linea x linea
                //readline() devuelve null cuando no hay mas lineas q leer
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

                System.out.println("FIN RESPUESTA DEL SERVIDOR");
            } catch (MalformedURLException e) {
                //Excepcion para la URL si no tiene un formato valido
                System.out.println("Error de URL: La URL no tiene un formato correcto");
                System.out.println("Detalles:" + e.getMessage());

            } catch (IOException e) {
                System.out.println("Error al conectar o leer desde el servidor");
                System.out.println("Detalles:" + e.getMessage());
            } }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
