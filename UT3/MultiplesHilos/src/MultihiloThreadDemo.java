
/**
 * @author Gilberto
 * @since 21/10/2025
 * @dato TA2 UT3
 */

public class MultihiloThreadDemo  extends Thread{

    //Al heredar de thread debemos sobreescribir el metodo run ()
    @Override
    public void run() {

        try {
            System.out.println("Hilo (Heredado con thread) con ID" + Thread.currentThread().getId()
                    + "se esta ejecutando" );

        }
        catch (Exception e) {
         System.err.println("Se capturo una excepcion en el hilo");

        }
    }

}


