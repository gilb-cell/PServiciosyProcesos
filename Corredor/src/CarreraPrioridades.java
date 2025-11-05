import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Thread corredor1 = new Thread(new Corredor("Corrdor 1 ", metaCompartida));
        corredor1.setPriority(Thread.MIN_PRIORITY);

        Thread corredor2 = new Thread(new Corredor("Corrdor 2 ", metaCompartida));
        corredor1.setPriority(Thread.MAX_PRIORITY);

        Thread corredor3 = new Thread(new Corredor("Corrdor 3 ", metaCompartida));
        corredor1.setPriority(Thread.NORM_PRIORITY);

        Thread corredor4 = new Thread(new Corredor("Corrdor 4 ", metaCompartida));
        corredor1.setPriority(Thread.MIN_PRIORITY);

        Thread corredor5 = new Thread(new Corredor("Corrdor 5 ", metaCompartida));
        corredor1.setPriority(Thread.MAX_PRIORITY);

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
        System.out.println("\n Orden de llegada");

        //Recorremos la lista compartida para imprimir
        for

    }
}
