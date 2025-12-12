import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FTPLocalHost {
    public static void main(String[] args) {
        //Esta URL intenta acceder a un servidor FTP publico de prueba
        //Formato: ftp://usuario:contraseña@servidor/ruta/archivo
        //String urlString = "ftp://anonymous:password@ftp.example.com/archivo.txt";


        //SERVIDOR DE PRUEBA: test.rebex.net - pruebas para desarrollo
        //Podria conectarme con otra ip como servidor a otro ordenador, y cambiando el resto de variables
        //Y asi poder leer otro archivos
        String servidor = "localhost";
        String usuario = "alumno"; //La contraseña y el usuario son del archivo del visualStudio (docker-compose)
        String password = "1234";
        String archivo = "soyfeo.txt";


        String urlString = String.format("ftp://%s:%s@%S/%s", usuario, password, servidor, archivo);


        try {
            URL url = new URL(urlString);


            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                System.out.println("---CONTENIDO DEL ARCHIVO---\n");
                String line;
                while ((line = reader.readLine()) != null){
                    System.out.println(line);
                }
                System.out.println("\n---FIN DEL ARCHIVO---");


            } catch (MalformedURLException e) {
                System.err.println("ERROR: La URL no tiene un formato válido.");
                System.err.println("Detalles: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("ERROR: Error al conectar o leer desde el servidor.");
                System.err.println("Detalles: " + e.getMessage());
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

