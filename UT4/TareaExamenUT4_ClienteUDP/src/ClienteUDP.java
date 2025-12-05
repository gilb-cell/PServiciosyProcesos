
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;
//Gilberto

public class ClienteUDP {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            DatagramSocket socketUDP = new DatagramSocket();

            InetAddress direccion = InetAddress.getByName("localhost");
            int puerto = 12345;

            boolean salir = false;

            while (!salir) {

                //MENÚ PRINCIPAL
                System.out.println("  MENÚ DEL CLIENTE ");
                System.out.println("1. Enviar mensaje tipo respuesta");
                System.out.println("2. Enviar mensaje cerrar zona y ver resumen FIN");
                System.out.println("3. Enviar mensaje manual");
                System.out.println("4. Solicitar resultados globales");
                System.out.println("0. Salir");
                System.out.print("Selecciona una opción: ");
                String opcion = sc.nextLine();

                switch (opcion) {

                    case "1":
                        // RESP = mensaje estándar
                        System.out.print("Introduce zona: ");
                        String zona = sc.nextLine(); //Guarda la respuesta en esta variable
                        System.out.print("Introduce texto a enviar: ");
                        String texto = sc.nextLine();

                        String resp = "@resp#" + zona + "#" + texto + "@";
                        enviarMensaje(socketUDP, direccion, puerto, resp);
                        break;

                    case "2":
                        // FIN = cierre de zona
                        System.out.print("Introduce zona a cerrar: ");
                        String zonaFin = sc.nextLine();

                        String fin = "@fin#" + zonaFin + "@";
                        enviarMensaje(socketUDP, direccion, puerto, fin);
                        break;

                    case "3":
                        // Manual (cualquier cadena)
                        System.out.print("Introduce mensaje completo: ");
                        String manual = sc.nextLine();

                        enviarMensaje(socketUDP, direccion, puerto, manual);
                        break;

                    case "4":

                        // SOLICITAR RESULTADOS
                        String estadisticas = "@resultados@";
                        enviarMensaje(socketUDP, direccion, puerto, estadisticas);
                        break;

                    case "0":
                        salir = true;
                        break;

                    default:
                        System.out.println("Opción no válida. Intente otra vez.");
                        break;
                }
            }

            socketUDP.close();
            System.out.println("Cliente cerrado correctamente.");

        } catch (InputMismatchException e) {
            System.err.println("Error, el numero debe estar entre 0 y 4: " + e.getMessage());
        }catch(SocketException e){
            System.err.println("Error al hacer la conexion.");
        }catch (Exception ex ) {
            System.err.println("Error general cliente: " + ex.getMessage());
        }
    }


    // MÉTODO ENVIAR MENSAJE
    private static void enviarMensaje(DatagramSocket socket, InetAddress direccion, int puerto, String mensaje) {

        try {
            System.out.println("CLIENTE ENVÍA: " + mensaje);

            byte[] buffer = mensaje.getBytes(StandardCharsets.UTF_8);
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, puerto);

            socket.send(paquete);

            byte[] bufferRespuesta = new byte[1024];
            DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);

            socket.receive(paqueteRespuesta);

            String recibido = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength(), StandardCharsets.UTF_8);
            System.out.println("SERVIDOR RESPONDE: " + recibido);

        } catch (Exception e) {
            System.err.println("Error enviando mensaje: " + e.getMessage());
        }
    }
}
