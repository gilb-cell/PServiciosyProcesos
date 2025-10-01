/**
 * Descripcion Gilberto: Tarea3 UT2 Sistema de concurrencia con hilos
 * Clase principal que ejecuta el ejemplo del uso de un monitor
 * para la comunicación entre un productor (Suministrado) y un
 * consumidor (Cliente) usando un Buffer.
 * Este programa crea un buffer de tamaño fijo y lanza
 * dos hilos: uno que suministra elementos al buffer y otro
 * que los consume.
 *@author Gilber
 * @version 1.0
 * @since 30/09/2025
 */
public class MonitorExample{

  /*  public MonitorExample(int size) {
        super(size);
    }*/
    /**
     * Metodo principal que inicia el programa.
     * Crea las instancias del buffer, del productor y del consumidor,
     * y arranca los hilos correspondientes.
     * @throws InterruptedException si ocurre una interrupción en alguno de los hilos.
     */
    public static void main(String[] args) throws InterruptedException {
            //Creamos una instancia de cada clase

            Buffer buffer = new Buffer(5);
            //Creeamos el hilo Suministrador
            Suministrado Paco = new Suministrado(buffer);
            //Creamos el hilo Cliente
            Cliente Gilber = new Cliente(buffer);

            // iniciamos los hilos
            Paco.start();
            Gilber.start();

        }

    }

//Gilberto//