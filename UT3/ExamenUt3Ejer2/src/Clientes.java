import java.util.concurrent.Semaphore;

public class Clientes implements Runnable {
    private Semaphore semaphore;
    private String nombre;

    public Clientes(Semaphore semaphore, String nombre) {
        this.semaphore = semaphore;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        try {
        // Adquiere un permiso de semáforo
            semaphore.acquire();
        // Mensaje por consola de que el ‘Cliente X está realizando un pedido’
            System.out.println("El cliente " + this.nombre + " está realizando el pedido.");
        // Simula el tiempo de realizar el pedido (1 segundo)
            Thread.sleep(1000);
        // Mensaje por consola ‘Cliente X ha terminado su pedido’
            System.out.println("El cliente " + this.nombre + " ha terminado su pedido.");
        } catch (InterruptedException e) {
        //Mensaje por consola sobre la interrupción
            Thread.currentThread().interrupt();
            System.out.println("Error: Se ha interrumpido el pedido.");
        } finally {
        // Liberamos el permiso del semáforo para otros clientes
            semaphore.release();
        }
    }
}
