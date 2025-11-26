
/**
 * @author Gilberto
 * @since 21/10/2025
 * @dato TA2 UT3
 */

// -- CLASE PRINCIPAL --
public class MultihiloRunnable {
    public static void main(String[] args) {

        int n = 8; // El numero de hilos que queremos crear

        System.out.println("Hilo principal iniciando. Creando :" + n + "hilos.");

        for (int i = 0 ; i < n ; i++) { //Bucle for para crear e inicar los hilos

            //Paso1, creamos un objeto de nuestra clase Runnable
            //Esto NO es el hilo, es solo la tarea (el trabajo) que queremos ejecutar
            MultihiloRunnableDemo tarea = new MultihiloRunnableDemo();

            //Paso2 Creamos un objeto Thread
            //Le pasamos nuestra Tarea (el objeto Runnable) a su constructor
            //Le estamos diciendo al trabajador "Cuando te inicies ejecuta esta tarea"
            Thread hilo = new Thread(tarea);

            //Paso3 iniciamos el hilo
            hilo.start();
        }
        System.out.println("Hilo principal ha terminado de lanzar todos los hilos");
    }
}
