import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;


public class HiloDatagrama extends Thread {
    private final DatagramPacket paqueteRecibido;
    private final DatagramSocket socket;
    private final resultadosEncuesta resultados;

    //Constructor con parametros
    public HiloDatagrama(DatagramPacket paqueteRecibido, DatagramSocket socket, resultadosEncuesta resultados) {
        this.paqueteRecibido = paqueteRecibido;
        this.socket = socket;
        this.resultados = resultados;
    }


    @Override
    public void run() {
        try {
            String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

            mensaje = mensaje.trim(); //para limpiar cualquier residuo de byte

            System.out.println("Mensaje recibido: " + mensaje);

            String respuesta;

            //PROCESAR @resp#zona#respuesta@
            if (mensaje.matches("@resp#.+#.+@")) {
                String[] partes = mensaje.replace("@", "").split("#");
                String zona = partes[1];
                String respu = partes[2];

                resultados.agregarRespuesta(zona, respu);
                respuesta = "Respuesta guardada para " + zona + ": " + respu;
            }

            // PROCESAR @fin#zona@
            else if (mensaje.matches("@fin#.+@")) {
                String zona = mensaje.replace("@", "").split("#")[1];
                respuesta = resultados.getResumenZona(zona);
            }

            // PROCESAR @resultados@
            else if (mensaje.contains("@resultados@")) {
                respuesta = resultados.getResumenGlobal();
            }

            //MENSAJE DESCONOCIDO
            else {
                respuesta = """ 
                        ERROR: Formato no válido. Formatos permitidos:
                        @resp#zona#respuesta@
                        @fin#zona@
                        @resultados@""";
            }

            // Enviar respuesta
            byte[] buffer = respuesta.getBytes(StandardCharsets.UTF_8);
            DatagramPacket paqueteEnvio = new DatagramPacket(
                    buffer,
                    buffer.length,
                    paqueteRecibido.getAddress(),
                    paqueteRecibido.getPort()
            );

            socket.send(paqueteEnvio);

        } catch (Exception e) {
            System.err.println("Error al procesar datagrama: " + e.getMessage());
        }
    }
}
