import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Gilberto
 */

public class ListNetworkInterfacesPartA {

    public static void main(String[] args) {

        try {
            //1) NetworkInterface.getNetworkInterfaces() devuelve un Enumeration
            //que contiene todas las interfaces de red disponible en le sistema
            //(tarjetas fisicas, adaptadores virtuales, loopback, etc)

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if(!interfaces.hasMoreElements()){
                System.out.println("No interfaces");
                return;
            }

            //2) Iteramos sobre cada interfaz de red encontrada
            while(interfaces.hasMoreElements()){
                //a) Dentro del bucle while nextElement() obtiene la siguiente interfaz de la enumeracion

                NetworkInterface networkInterface = interfaces.nextElement();

                //b) getName() devuelve el nombre de la interfaz del sistema (ej: eth0, wlan0, lo)
                //Es el identificador corto usado internamente en el SO
                String name = networkInterface.getName();

                //c) getDisplayName() devuelve un nombre descriptivo mas legible
                //En Windows suele ser el nombre completo del adaptador
                String displayName = networkInterface.getDisplayName();

                //d) isUp() comprueba si la interfaz esta actualmente activa/operativa
                //Devuelve tru si esta habilitada y funcionando , false en caso contrario
                 boolean activa = networkInterface.isUp();





                //Mostramos la informacion basica de la interfaz

                System.out.println("Interfaz: " + name + " Display: " + displayName + " Activa: " + (activa ? "SI" : "NO"));

            }

        }catch (SocketException e){
            System.out.println("Error al intentar listar las interfaces");
        }
        }
    }
