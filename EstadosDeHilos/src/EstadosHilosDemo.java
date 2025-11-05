
/**
 * @author Gilberto
 * @since 21/10/2025
 * @dato TA3 UT3
 */


public class EstadosHilosDemo {
    public static void main(String[] args) {

        //Crear los hilos
      Thread hilo1;
      Thread hilo2;

      hilo1 =  new Thread( new MiTarea("Hilo 1"));
      hilo2 =  new Thread( new MiTarea("Hilo 2"));

        //Imprimimos los estados de los hilos antes de iniciarlo
        System.out.println(hilo1.getName() + " estado " + hilo1.getState());
        System.out.println(hilo2.getName() + " estado " +hilo2.getState());

        //Iniciamos los hilos
        hilo1.start();
        hilo2.start();

        //Monitorizamos los hilos mientras estan en ejecucion
        while(hilo1.getState() != Thread.State.TERMINATED || hilo2.getState() != Thread.State.TERMINATED) {
            System.out.println(hilo1.getName() + " estado " + hilo1.getState());
            System.out.println(hilo2.getName() + " estado " + hilo2.getState());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Imprimimos los estados finales
        System.out.println(hilo1.getName() + " estado " + hilo1.getState());
        System.out.println(hilo2.getName() + " estado " +hilo2.getState());
    }
}




//Creamos la clase del hilo que implementa Runnable
class MiTarea implements Runnable {
    private String nombre;

    public MiTarea(String nombre) { //Constructor de clase
        this.nombre = nombre;
    }
    @Override
    public void run() {

        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println( nombre + " ejecutando: "  + i);
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}