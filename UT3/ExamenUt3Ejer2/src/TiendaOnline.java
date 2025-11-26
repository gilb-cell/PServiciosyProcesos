import java.util.concurrent.Semaphore;

public class TiendaOnline {
    public static void main(String[] args) {

        //Creamos objeto semaforo
        Semaphore semaphore = new Semaphore(2);

        Thread cliente1 = new Thread(new Clientes(semaphore, "Pepe"));
        Thread cliente2 = new Thread(new Clientes(semaphore, "Antonio"));
        Thread cliente3 = new Thread(new Clientes(semaphore, "Gilber"));
        Thread cliente4 = new Thread(new Clientes(semaphore, "Marta"));

        //Iniciamos los hilos
        cliente1.start();
        cliente2.start();
        cliente3.start();
        cliente4.start();

        try {

        } catch (InterruptedException e) {

        }

        //Creamos las prioridades para los distintos empleados


        //Iniciamos a los empleados





    }
}