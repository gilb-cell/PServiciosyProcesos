
/**
 * @author Gilberto
 * @since 21/10/2025
 * @dato TA2 UT3
 */

public class MultihiloThread {
    public static void main(String[] args) {

        //for: crea 8 MultihiloDemo y cuando creas los hilos, inicialos con
        //object.start();
        int n = 8; // El numero de hilos que queremos crear

        for (int i = 0 ; i < n ; i++) { //Bucle for para crear e inicar los hilos

            //Paso1, creamos un objeto de nuestra clase
            MultihiloThreadDemo hilo = new MultihiloThreadDemo();

            //Paso2 iniciamos el hilo
            hilo.start();
        }
        System.out.println("Hilo principal ha terminado de lanzar todos los hilos");


    }
}
