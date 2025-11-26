import java.util.List;

/**
 * @author Gilberto
 * @since 04/11/2025
 * @asignatura Servicios y Procesos
 */
public class Corredor implements Runnable {

    //Atributos y constructor
    String nombre;
    List<String> metaCompartida;

    Corredor(String nombre, List<String> metaCompartida) {
        this.nombre = nombre;
        this.metaCompartida = metaCompartida;
    }

    @Override
    public void run() {
        //Carrera en 10 puntos intermedios de control
        for(int i=1; i<10; i++) {
            System.out.println(nombre + "ha avanzado al punto " + i);
        }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }

            //Al llegar a la meta , se ha de registrar que ha llegado y estan en orden
            synchronized (metaCompartida) {
                metaCompartida.add(nombre);
            }
            System.out.println(nombre + "ha cruzado la meta");
            // System.out.println(nombre + "ha cruzado la meta");
        //Lo imprimimos fuera del bucle porque si no,
        // cada corredor estaria “cruzando la meta” diez veces una por cada punto intermedio
    }
}
