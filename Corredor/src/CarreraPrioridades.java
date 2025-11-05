import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Gilberto
 * @since 04/11/2025
 * @asignatura Servicios y Procesos
 */

//Definimos la clase principal que simulara la carrera
public class CarreraPrioridades {

    public static void main(String[] args) {

        //Creamos el recurso compartido
        //Creamos la linea de meta. Esta lista sera compartida por todos los hilos (corredores)
        //Usamos Collections para envolver nuesta Linkendlist
        //Esto la convierte en thread-safe eviate condiciones de carrera si dos hilos
        //intentan añadir su nombre a la lista exactamente al mismo tiempo

        List<String> metaCompartida = Collections.synchronizedList(new ArrayList<String>());

        //Creamos los hilos (corredores) con sus prioridades
        //Creamos el primer hilo corredor1. Le pasamos la lista compartida para que sepa donde anotar su llegada
        Thread corredor1 = new Thread(new Corredor("Corredor 1 ", metaCompartida));
        corredor1.setPriority(Thread.MIN_PRIORITY);

        Thread corredor2 = new Thread(new Corredor("Corredor 2 ", metaCompartida));
        corredor2.setPriority(Thread.NORM_PRIORITY);

        Thread corredor3 = new Thread(new Corredor("Corredor 3 ", metaCompartida));
        corredor3.setPriority(Thread.MAX_PRIORITY);

        Thread corredor4 = new Thread(new Corredor("Corredor 4 ", metaCompartida));
        corredor4.setPriority(Thread.NORM_PRIORITY);

        Thread corredor5 = new Thread(new Corredor("Corredor 5 ", metaCompartida));
        corredor5.setPriority(Thread.MIN_PRIORITY);

        //Iniciar la ejecucion de cada corredor
        corredor1.start();
        corredor2.start();
        corredor3.start();
        corredor4.start();
        corredor5.start();

        //Esperamos a que todos terminen
        try{

            corredor1.join();
            corredor2.join();
            corredor3.join();
            corredor4.join();
            corredor5.join();

        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        //Mostramos los resultados
        System.out.println("\nOrden de llegada");

        //Recorremos la lista compartida para imprimir
        for(String corredor : metaCompartida){
            System.out.println(corredor);

        }

    }
}
