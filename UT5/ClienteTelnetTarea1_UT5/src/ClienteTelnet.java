import org.apache.commons.net.examples.util.IOUtil;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;

/**
 * Gilberto
 * Tarea 1 UT5
 * P.Servicos y Procesos
 */

public class ClienteTelnet {

    // Colores ANSI

    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";


    public static void main(String[] args) {

        //Comprobar que se han pasado los argumentos correctos
        if(args.length < 2){ //Los dos argumentos son el servidor y el puerto
            System.out.println(ROJO+"Error: Debe indicar servidor y puerto");
            //Terminamos el programa con codigo de error 1
            System.exit(1);
        }

        //Obtenemos el servidor y el puerto introducidos desde la linea de comandos
        String servidor = args[0];
        int puerto;

        try {//Manejamos excepcion para que el puerta solo puede ser un numero entero
             puerto = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println(ROJO + "Error: El puerto debe ser un número entero");
            System.exit(1);
            return; // Esto no es necesario, pero ayuda a la claridad
        }


        // Crear un objeto TelnetCliente de Apache Commons Net
        TelnetClient cliente = new TelnetClient();

        try{

            //Conectarse al servidor
            System.out.println(AMARILLO + "Conectando a" + servidor + "en el puerto " + puerto);

            //Método connect() establece la conexion TCP con el servidor
            cliente.connect(servidor, puerto);
            System.out.println(VERDE + "Conexion establecida correctamente.");
            System.out.println(AMARILLO+ "Escriba los comandas y presione Enter");

            //Este metodo se ejecuta en un bucle hasta que la conexion se cierra
            IOUtil.readWrite(cliente.getInputStream(), cliente.getOutputStream(), System.in, System.out);



        }catch(IOException e){
            System.out.println(ROJO + "Error: No se puede conectar com el servidor");
            //Terminamos el programa con codigo error 2
            System.exit(2);

        }finally{
        try{//Liberamos recursos
            cliente.disconnect();
            System.out.println(VERDE + "Conexión cerrada correctamente.");

        }catch (IOException e){
            System.out.println(ROJO + "Error: No se puede desconectar el servidor");
        }

        }


    }
}