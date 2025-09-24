public class ejecucionProceso {
    public static void main(String[] args) {
        try {
            //ejecutar el bloc de notas en Windows
            Process proceso = Runtime.getRuntime().exec("notepad.exe");

            //Esperar a que el usuario cierre el bloc de notas
            proceso.waitFor();
            System.out.println("El bloc de notas se cerro.Codigo de salida.." + proceso.exitValue());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}