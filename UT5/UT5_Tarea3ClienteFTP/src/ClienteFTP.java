import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/***
 * Gilberto
 */

//Problemas de esta solucon, posibles optimizaciones

/*
No hay manejo de especificos ni validaciones. No cierra recursos adecuadamente
 */

public class ClienteFTP {

    public static void main(String[] args) {

        //Incluir los datos de prubea del Servidor de prueba: test.rebex.net
       /* String servidor = "test.rebex.net";
          String usuario = "demo";
          String password = "password";  */

        //Otro servidor
      /*  String servidor = "demo.wftpserver.com";
          String usuario = "demo";
          String password = "demo";  */

     /*   //Otro servidor
          String servidor = "cygwin.mirror.rafal.ca";
          String usuario = "ftp";
          String password = "gilbert23@gmail.com";   */

        //Otro servidor
        String servidor = "ftp.scene.org";
        String usuario = "ftp";
        String password = "gilbert23@gmail.com";


        FTPClient ftpClient = new FTPClient();
        FileOutputStream archivoLocal = null;

        //Bloque try -catch
        try {
            //Conectamos con el servidor y validamos la conexion
                ftpClient.connect(servidor);
                int codigoRespuesta = ftpClient.getReplyCode();
                if(!FTPReply.isPositiveCompletion(codigoRespuesta)){
                    System.out.println("Error: El servidor rechazo la conexion");
                    return;
                }
                System.out.println("Conectando con el servidor FTP");

                // Verificamos el login, haciendo uso de un if para validar el login
               boolean loginTrue =  ftpClient.login(usuario, password);
                if(!loginTrue){
                    System.out.println("Error: Usuario o contraseñas incorrecto");
                    return;
                }
                System.out.println("El login es correcto");

                //Modo passivo, muy importante para poder hacer bien la conexion
                ftpClient.enterLocalPassiveMode();


            //Listamos archivos disponibles
            System.out.println("Archivos disponibles en el servidor: ");
            String[] archivos = ftpClient.listNames();

            if(archivos!=null){
                for (String archivo : archivos) {
                    System.out.println("- " + archivo);
                }
            }else{
                System.out.println("No se pudo obtener el listo de los archivos");
            }


            //Descargamos el archivo
            String archivoRemoto = "welcome.msg";

            archivoLocal = new FileOutputStream("readme_descargado.txt");

            //Validamos la descarga del archivo
            boolean descargado = ftpClient.retrieveFile(archivoRemoto, archivoLocal);
            if(descargado){
                System.out.println("Archivo descargado");
            }else{
                System.out.println("Error: No existe el archivo");
            }

            //Manejo especifico de excepciones
        }catch (FileNotFoundException e){
            System.out.println("Error: No se pudo crear el archivo");
            System.out.println("Detalles: " + e.getMessage());

        }catch(IOException e) {
            System.out.println("Error: La comunicacion ha fallado con el servidor");
            System.out.println("Detalles: " + e.getMessage());

            //Cerramos los recursos con el bloque finally, y asi nos aseguramos
            //de que si ocurre algun error, se cierren igualmente
        }finally {
            try {
                if(archivoLocal!=null){
                    archivoLocal.close();
                }
                if(ftpClient.isConnected()){
                    ftpClient.logout();
                    ftpClient.disconnect();
                    System.out.println("La conexion ha sido cerrada correctamente");
                }
            }catch (IOException | NullPointerException e){
                System.out.println("Error al cerrar algun recurso");
            }
        }


    }
}
