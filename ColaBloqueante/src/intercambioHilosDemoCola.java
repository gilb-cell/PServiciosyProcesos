
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.Random;

/**
 * @author Gilberto
 * @since 21/10/2025
 * @dato TA4 UT3
 */
public class intercambioHilosDemoCola {

    public static void main(String[] args) {

        // 1 Creamos la cola compartida con capacidad de 1
        BlockingQueue<Integer> cola = new ArrayBlockingQueue<>(1);

        // 2 Creamos los hilos  y objetos (productor y consumidor)
        Thread productor = new Thread(new Productor(cola));
        Thread consumidor = new Thread(new Consumidor(cola));

        // 3 Iniciamos los hilos
        productor.start();
        consumidor.start();
    }
}

// Clase Productor
class Productor implements Runnable {

    private final BlockingQueue<Integer> cola;
    private final Random random = new Random();

    //Constructor de cla clase
    public Productor(BlockingQueue<Integer> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int numero = random.nextInt(100) + 1; // numero aleatorio 1–100
                cola.put(numero); // pone el numero en la cola
                System.out.println(" Productor: generé el número " + numero);
                Thread.sleep(500); // simula tiempo de producción
            }
        } catch (InterruptedException e) {
            System.out.println(" El hilo productor fue interrumpido.");
        }
    }
}

// Clase Consumidor
class Consumidor implements Runnable {

    //Variable cola
    private final BlockingQueue<Integer> cola;

    //Constructor
    public Consumidor(BlockingQueue<Integer> cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int numero = cola.take(); // obtiene el número de la cola
                System.out.println(" Consumidor: procesé el número " + numero);
                Thread.sleep(3000); // simula tiempo de procesamiento
            }
        } catch (InterruptedException e) {
            System.out.println(" El hilo consumidor fue interrumpido.");
        }
    }
}

