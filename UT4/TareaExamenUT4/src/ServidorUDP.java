import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;

public class ServidorUDP {

        public static void main(String[] args) {

            final int puertoUDP= 12345;

            resultadosEncuesta resultados = new resultadosEncuesta();

            try (DatagramSocket socket = new DatagramSocket(puertoUDP)) {
                System.out.println("Servidor UDP escuchando en el puerto " + puertoUDP);

                while (true) {
                    // Recibir datagrama
                    byte[] buffer = new byte[1024];
                    DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
                    socket.receive(paquete);

                    // Crear un hilo para atenderlo
                    new HiloDatagrama(paquete, socket, resultados).start();
                }

            } catch (Exception e) {
                System.err.println("Error en servidor: " + e.getMessage());
            }
        }
    }



