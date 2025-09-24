import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//Gilberto Gil Gandia//
public class NumRandom {
    public static void main(String[] args) {

        //1. -Creamos una instancia para usar los metodos de esta clase
        NumRandom numRandom = new NumRandom();

        //2.-Generamos 35 numeros aleatorios
        List<Integer> numeros = numRandom.generarNumeros();
//Metodo que muestra los numeros por consola
        numRandom.mostrarNumeros(numeros);

        //3.- Mostramos los numeros por la salida estandar

    }

        //DEFINIMOS LOS METODOS DE LA CLASE

    public List<Integer> generarNumeros() {

        //Instanciamos un generador de numeros aleatorios
        Random random = new Random();

        //Generamos la lista donde queremos almacenar los numeros aleatorios
        List<Integer> numeros = new ArrayList<>();

        //Bucle para generar 35 numeros aleatorios
        for (int i = 0; i <= 35; i++) {
            int resultado = random.nextInt(101);
            numeros.add(resultado);
        }
        //Devolvemos los numeros generados
        return numeros;
    }


    public void mostrarNumeros(List<Integer> numeros){
        for (int numero : numeros){
            System.out.println(numero + " ");
        }
        System.out.println();
    }
}
