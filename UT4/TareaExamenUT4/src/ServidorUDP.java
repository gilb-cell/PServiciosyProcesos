import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class ServidorUDP {

    private static final int puertoUDP = 12345;

    // Mapa para almacenar respuestas por zona
    private static final Map<String, List<String>> respuestas = Collections.synchronizedMap(new HashMap<>());


    public static void main(String[] args) {

        try (DatagramSocket socketUDP = new DatagramSocket(puertoUDP);) {
            System.out.println("Iniciando servidor UDP en el puerto: " + puertoUDP);

            //bucle infinito del servidor
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packetUDP = new DatagramPacket(buffer, buffer.length);

                System.out.println("Esperando datagrama del cliente");
                socketUDP.receive(packetUDP);

                String datagrama = new String(packetUDP.getData(), 0, packetUDP.getLength());
                System.out.println("Mensaje recibido: " + datagrama);

                //Creamos un hilo para procesar el datagrama
                new Thread(() -> procesarMensaje(socketUDP, packetUDP, datagrama)).start();
            }


        } catch (Exception e) {
        }
    }

    //   MÉTODO DE PROCESAMIENTO QUE SE EJECUTA EN CADA HILO
    private static void procesarMensaje(DatagramSocket socketUDP, DatagramPacket packet, String datagrama) {

        String respuesta;
        if (datagrama.startsWith("@resp#")) {
            respuesta = procesarRespuesta(datagrama);
        } else if (datagrama.startsWith("@fin#")) {
            respuesta = procesarFinZona(datagrama);
        } else if (datagrama.equals("@resultados@")) {
            respuesta = generarResumenGlobal();
        } else {
            respuesta = "Error: el formato no es válido. Formatos válidos:\\n\" +\n" +
                    "                        \"@resp#zona#respuesta@\\n\" +\n" +
                    "                        \"@fin#zona@\\n\" +\n" +
                    "                        \"@resultados@\";"
        }

        enviarRespuesta(socketUDP,packet, respuesta);

    }
}

