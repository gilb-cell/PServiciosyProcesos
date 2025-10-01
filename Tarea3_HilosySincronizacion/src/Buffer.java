/**
 * Descripcion: Tarea3 UT2 Sistema de concurrencia con hilos
 * La clase Buffer implementa un monitor que permite la comunicación
 * sincronizada entre un productor (Suministrado) y un consumidor (Cliente).
 *@author Gilber
 * @version 1.0
 * @since 30/09/2025
 */

public class Buffer {
    /** Array que almacena los elementos del buffer. */
    private int[] buffer;
    private int count = 0; //Numero de elementos que hay en el buffer
    private int size; // Tamaño del buffer

    //Constructores
    public Buffer(int size) {
        this.size = size;
        buffer = new int[size];
    }

    //Metodo sincronizado para añadir un elementos por el suministrador
    //@Override faltaria la implementacion de la interfaz
    public synchronized void add (int value) throws InterruptedException {
        while (count == size) {
            wait(); //Esperar porque el buffer esta lleno

        }
        buffer[count] = value; //Añade un nuevo valor al buffer
        count++;
        System.out.println("Suministrador añadio: " + value);
        notifyAll();
    }

    //Metodo sincronizado para extraer un elemento por el cliente
    /**
     * Extrae un elemento del buffer de manera sincronizada.
     * Si el buffer está vacío, el hilo esperará hasta que haya elementos disponibles.
     * @return El valor entero extraído del buffer.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public synchronized int remove () throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int value = buffer[--count];
       // count--;
        System.out.println("Cliente extrajo: " + value);
        notifyAll();
        return value;

    }




}

//Clase suministrador
class Suministrado extends Thread {

    private Buffer buffer;

    public Suministrado(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                buffer.add(i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();}

    }
}

//Clase cliente

class Cliente extends Thread {

    private Buffer buffer;

    //Contructor
    public Cliente(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 100; i++) {
                buffer.remove();
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

//Clase MonitorExample (con main)

/*class MonitorExample{
    public static void main(String[] args) throws InterruptedException {
        //Creamos una instancia de cada clase
        Buffer buffer = new Buffer(5);
        Suministrado Paco = new Suministrado(buffer);
        Cliente Gilber = new Cliente(buffer);

        Paco.start();
        Gilber.start();

    }

}*/
