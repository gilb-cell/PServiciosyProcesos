import java.util.List;

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

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }

            //Al llegar a la meta , se ha de registrar que ha llegado y estan en orden
            synchronized (metaCompartida) {
                metaCompartida.add(nombre);
            }
            System.out.println(nombre + "ha cruzado la meta");
        }

    }
}
