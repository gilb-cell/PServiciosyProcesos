import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gilberto
 * @since 29/10/2025
 * @dato TA5 UT3
 * @asignatura Servicios y Procesos
 */

public class LikesSimulacion {

    // AtomicInteger nos permite realizar operaciones atómicas sobre la variable 'contador'
    private static AtomicInteger contadorLikes = new AtomicInteger(0);

    public static void main(String[] args) {
        //Crear usuarios (hilos) que incrementaran el contador
        Thread usuario1 = new Thread(new Usuario("Gilber")); //añadimos parametros xq el constructor lleva pero podemos hacer constructor sin parametros
        Thread usuario2 = new Thread(new Usuario("Pepe"));
        Thread usuario3 = new Thread(new Usuario("Jacinta"));

        //Tendremos que iniciar a esos usuarios (hilos)
        usuario1.start();
        usuario2.start();
        usuario3.start();

        //Esperar a que los hilos terminen con join
        try{
            usuario1.join();
            usuario2.join();
            usuario3.join();
        }catch(InterruptedException e){
            System.err.println("Error al esperar qla finalizacion de los hilos");
        }

        //Motrar el resultado final
        System.out.println("El total de likes es: " + contadorLikes.get());

    }


   static class Usuario extends Thread {

        private final String nombre;
        public Usuario(String nombre) {
            this.nombre = nombre;

        }
        //metodo run implementa un bluco que incrementa el contador de Likes usando el
        //metodo incrementAndGet
        @Override
        public void run() {
            //Cada usuario de esta clase representa 100 likes
            for (int i = 0; i < 100; i++) {

               int likesActuales = contadorLikes.incrementAndGet();
               System.out.println(nombre + " dio un like. Total de Likes: " + likesActuales);
            }

            //Opcional este try simulacion de retardo aleatorio entre likes
            try {
              Thread.sleep((long) (Math.random() * 100)); //con random conseguimos que se ejecuten aleatoriamente
                Thread.sleep(100); // forma de hacerlo sin random
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nombre +  " fue interrumpido ");
            }
        }

    }
}