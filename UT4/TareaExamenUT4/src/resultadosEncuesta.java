import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class resultadosEncuesta {

    // Mapa zona, lista de respuestas
    private final Map<String, List<String>> datos = new HashMap<>();

    //Agregar respuesta de forma segura
    public synchronized void agregarRespuesta(String zona, String respuesta) {
        datos.putIfAbsent(zona, new ArrayList<>());
        datos.get(zona).add(respuesta);
    }

    //Obtener resumen de una zona
    public synchronized String getResumenZona(String zona) {
        if (!datos.containsKey(zona)) {
            return "No hay respuestas registradas para la zona: " + zona;
        }

        StringBuilder resumen = new StringBuilder("Resumen de " + zona + ":\n");
        int i = 1;
        for (String r : datos.get(zona)) {
            resumen.append(i++).append(") ").append(r).append("\n");
        }
        return resumen.toString();
    }

    //Resumen global
    public synchronized String getResumenGlobal() {
        System.out.println("RESUMEN GLOBAL");
        StringBuilder sb = new StringBuilder();

        for (String zona : datos.keySet()) {
            sb.append(getResumenZona(zona)).append("\n");
        }

        return sb.toString();
    }
}
