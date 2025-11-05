
class Panaderia{
    //La rdyanteria solo tiene hueco para poner una barra de pan
    private int pan; // Identificamos el pan como un entero (el pan numero 1, el numero 2 ....)
    private boolean disponible = false; //Si disponible es falso, es que no hay una barra de oan disponible

    public synchronized void productoPan(int valor){
        //Cuando hay una barra en la estanteria
        while(disponible){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        pan = valor;
        disponible = true;
        System.out.println("Producido el pan numero: " + valor );
        notify();
    }
    public synchronized int consumirPan(){
        while(!disponible){
            try {
                wait(); //Espera a que el panadero produzca un pan
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        disponible = false; //El cliente ha comprado el pan y ya no hay ningun pan disponible

        try{ //Simulamos que tarde un cliente 3s en llegar y comprar el pan
            Thread.sleep(3000);
        }catch(Exception e){
            Thread.currentThread().interrupt();
        }
        System.out.println("Consumido el pan numero: " + pan );
        notify(); // Se notifica al panadero que puede producir otro pan
        return pan;
    }
}



public class ComunicacionEntreHilos{

    public static void main(String[] args) {
        Panaderia panaderiaPan = new Panaderia(); // Creamos el objeto panaderia

        //Hilo productor : el panadero puede hacer un maximo de 5 panes
        Thread panadero = new Thread(() -> {
            for(int i = 0; i < 5; i++){
                panaderiaPan.productoPan(i);
            }
        });

        //Hilo consumidor, el cliente
        Thread cliente = new Thread(() -> {
            for(int i = 0; i < 5; i++){
                panaderiaPan.consumirPan();
            }
        });
        panadero.start(); // Comenzamos el hilo del panadero
        cliente.start(); //Comenzamos el hilo del cliente

    }
}