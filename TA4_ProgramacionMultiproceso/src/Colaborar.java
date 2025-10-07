import java.util.ArrayList;
import java.util.List;
/**
 * @author Gilberto
 * @version 1.0
 * @since 07/10/2025
 */


public class Colaborar {
    public static void main(String[] args) {
        String fichero = "miFicheroDeLenguaje.txt";
        int numLetrasPorLinea= 10;
        int numProcesos = 10;
        int incrementoLineas = 10;

//Iniciamos una lista para almacenar los hilos

      List<Thread> hilos = new ArrayList<>();

        //Creamos y lanzamos los hilos
        for(int i=0; i<numProcesos; i++){
            //Numero de lineas por hacer en cada iteracion
            int lineasPorHacer = i * incrementoLineas;

            //Creamos el proceso (o tarea) del objeto lenguaje
            Lenguaje miLenguaje = new Lenguaje(fichero, lineasPorHacer, numLetrasPorLinea);

            //Creamos el hilo para ejecutar la tarea
            Thread thread = new Thread(miLenguaje);
           hilos.add(thread);
            thread.start();

        }

        //Esperamos a que todos los hilos terminen
        for (Thread thread : hilos){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Error al esperar el hilo: " + e.getMessage());
            }
        }
    }
}
