class hiloRunnable implements Runnable
{
    private final String nombre;
    hiloRunnable (String nombre)
    {
// TODO Auto-generated constructor stub
        this.nombre=nombre;
    }
    @Override
    public void run()
    {
        System.out.printf("Hola, soy el hilo: %s.\n", this.nombre);
                System.out.printf("Hilo %s terminado.\n", this.nombre);
    }
}
