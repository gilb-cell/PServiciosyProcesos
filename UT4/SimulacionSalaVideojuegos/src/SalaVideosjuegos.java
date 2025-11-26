import java.util.concurrent.Semaphore;

/**
 * @author Gilberto
 * @since 04/11/2025
 * @dato TA6 UT3 ServiciosYProcesos
 */

public class SalaVideosjuegos {

    //Creamos un semaforo con un maximo de 4 permisos, es decir, 4 personas
    private static final int SPACES = 4;
    private final Semaphore salaDisponible = new Semaphore(SPACES);

    //Hilo principal
    public void main(String[] args) {
        //sE crean 10 hilos que seran 10 jugadores que intentan entrar a la sala
        for (int i = 1; i < 10; i++) {
            Thread jugador =new Thread(new Jugador (" Jugador " + i));
                    jugador.start(); //Inicia cada hilo
        }

    }

    private class Jugador implements Runnable {
        //Atributo para crear luego a los 4 jugadores
        String nombre;

        //constructor
        public Jugador(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {

            try {

                // Cada jugador intenta adquirir un permiso del semaforo
                //Si no hay opermisos disponibles, el hilo se bloquea hasta q otro lo libere

                System.out.println("Jugador " + nombre +  " esta intentando entrar en la sala ");

                salaDisponible.acquire(); //intenta adquirir permiso para entrar en la sala

                System.out.println(nombre + " ha entrado en la sala ");

                //Simulamos tiempo que el jugador esta en la sala
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nombre + " fue interrumpido");

            } finally {

                //Importante, se libera el permiso del semaforo
                //Esto permite que otro jugador pueda entrar, por eso el .release

                System.out.println(nombre + " ha salido de la sala");
                salaDisponible.release();
            }

        }

    }
}
