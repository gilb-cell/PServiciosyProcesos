

/**
 * @author Gilberto
 * @since 21/10/2025
 * @dato TA2 UT3
 */

public class MultihiloRunnableDemo implements Runnable {
//El codigo que pongamos aqui dentro es EXACTAMENTE  lo que el nuevo hilo
// ejecutara donde lo iniciemos. Es el 2trabajo" del hilo

    @Override
    public void run() {
        try {
            //Thread.current es un metodo que nos da una refencia al hilo que se esta actuazlmente ejecutando este codigo
            //getId nos da un identificador unico
            System.out.println("Hilo (implementado con Runnable) con ID" + Thread.currentThread().getId()
                    + "se esta ejecutando" );

        }catch (Exception e){
            System.out.println("Se capturo una excepcion en el hilo");
        }
    }
}


