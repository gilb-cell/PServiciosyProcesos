import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.net.*;


/**
 * Gilberto
 */

public class ListNetworkInterfacesPartB {
    public static void main(String[] args) throws SocketException {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            if (interfaces == null || !interfaces.hasMoreElements()) {
                System.out.println("No se encontraron interfaces de red.");
                return;
            }

            while (interfaces.hasMoreElements()) {

                NetworkInterface ni = interfaces.nextElement();

                System.out.println("Interfaz: " + ni.getDisplayName());

                // Mostrar todas las direcciones IP asociadas
                Enumeration<InetAddress> direcciones = ni.getInetAddresses();

                while (direcciones.hasMoreElements()) {
                    InetAddress direccion = direcciones.nextElement();

                    if (direccion instanceof Inet4Address) {
                        System.out.println(" IPv4: " + direccion.getHostAddress());
                    } else if (direccion instanceof Inet6Address) {
                        System.out.println(" IPv6: " + direccion.getHostAddress());
                    }
                }

                // Obtener máscaras y broadcast
                for (InterfaceAddress ia : ni.getInterfaceAddresses()) {

                    if (ia.getAddress() instanceof Inet4Address) {

                        short mascara = ia.getNetworkPrefixLength();
                        String broadcast = (ia.getBroadcast() != null) ? ia.getBroadcast().getHostAddress() : "N/A";

                        System.out.println(" Máscara de red: " + mascara);
                        System.out.println(" Broadcast: " + broadcast);
                    }
                }
            }

        } catch (SocketException e) {
            System.out.println("Error al obtener interfaces: " + e.getMessage());
        }
    }
}

