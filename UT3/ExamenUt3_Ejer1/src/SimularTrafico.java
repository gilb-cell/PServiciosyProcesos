
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

  public class SimularTrafico {

     public static void main(String[] args) {
         List<String> metaCompartida = Collections.synchronizedList(new ArrayList<>());

         // Latch con 1 permiso (una cuenta regresiva)
         CountDownLatch salida = new CountDownLatch(1);

         Thread moto1 = new Thread(new Moto("Moto 1", metaCompartida, salida));
         Thread moto2 = new Thread(new Moto("Moto 2", metaCompartida, salida));
         Thread coche1 = new Thread(new Coche("Coche 1", metaCompartida, salida));
         Thread coche2 = new Thread(new Coche("Coche 2", metaCompartida, salida));


         moto1.start();
         moto2.start();
         coche1.start();
         coche2.start();
        // Seguimiento de los estados de los hilos

         try {
                 Thread.sleep(200); // Pausa para evitar demasiados mensajes
             } catch (InterruptedException e) {
                 System.err.println("Seguimiento de estados interrumpido.");
             }


         try {
             Thread.sleep(1000); // Simulamos la preparación antes de la salida
             System.out.println("¡¡Preparados... Listos... YA!!");
             salida.countDown(); // Da la señal de salida:
         } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
         }

         // Esperamos a que todos terminen
         try {
             moto1.join();
             moto2.join();
             coche1.join();
             coche2.join();

         } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
         }

         System.out.println("\n Orden de llegada:");
         for (String vehiculos : metaCompartida) {
             System.out.println(vehiculos);
         }
     }
 }








