import java.util.List;
import java.util.concurrent.CountDownLatch;

class Moto extends Thread { //
    private String nombre;
    private List<String> metaCompartida;
    private CountDownLatch salida; // ← latch para sincronizar la salida

    public Moto(String nombre, List<String> metaCompartida, CountDownLatch salida) {
        this.nombre = nombre;
        this.metaCompartida = metaCompartida;
        this.salida = salida;
    }

    @Override
    public void run() {
        try {
            // Esperamos a que se dé la señal de salida
            System.out.println(nombre + " está lista en la línea de salida...");
            salida.await(); // Espera hasta que el latch llegue a 0
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Empiezan los puntos
        for (int i = 1; i <= 10; i++) {
            System.out.println(nombre + " ha avanzado al punto " + i);
            try {
                Thread.sleep(500); // solo para simular el avance
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        synchronized (metaCompartida) {
            metaCompartida.add(nombre);
        }
        System.out.println(nombre + " ha completado su recorrido.");
    }
}