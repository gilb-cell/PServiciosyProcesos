import java.io.*;
import java.net.Socket;

public class clienteChat {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // sustituir con la IP del servidor
        final int puerto = 12345; //definimos mismo puerto que en el ServerSocket

        //Creo un socket de cliente y nos conectamos al servidor
        //Instanciamos un objeto de la clase Buffered para leer las respuestas q envia el servidor
        //Instanciamos un objeto de la clase PrintWritter para enviar mensajes al servidor

        try (Socket clienteSocket = new Socket(host, puerto);
             BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
             PrintWriter salidaServidor = new PrintWriter(clienteSocket.getOutputStream(), true);
             //Intancio un objeto de la clase Buffered para leer lo que el usuario escribe x consola
             BufferedReader entradaConsola = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado al servidor en: " + host + "en el puerto: " + puerto);

            while (true) {
                //Solicitamos al usuario que escriba un mensaje por consola
                System.out.println("Mensaje Usuario: ");

                //El metodo readline() lee la linea completa que el usuario escribe por consola
                String mensaje = entradaConsola.readLine();
                //Enviamos un mensaje al servidor utilizando println y llamadno al objeto salida
                salidaServidor.println(mensaje);

                //Leemos la respuesta del servidor con readLine()
                String respuestaServidor = entradaServidor.readLine();



                if (respuestaServidor != null) {
                    System.out.println("Servidor: " + respuestaServidor);

                } else {
                    System.out.println("Servidor cerro la conexion");


                }
            }
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor" + host + ":" + puerto);
        }
    }
}
