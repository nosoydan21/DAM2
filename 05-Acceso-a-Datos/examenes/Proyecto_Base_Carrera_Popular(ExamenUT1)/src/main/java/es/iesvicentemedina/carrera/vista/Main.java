package es.iesvicentemedina.carrera.vista;

import es.iesvicentemedina.carrera.dao.ParticipacionDAOBinario;
import es.iesvicentemedina.carrera.dao.ParticipacionDAOCSV;
import es.iesvicentemedina.carrera.dao.ParticipacionDAOJSON;
import es.iesvicentemedina.carrera.modelo.Participacion;
import java.util.ArrayList;

public class Main {
    public static final String RUTA_CSV = "datos/resultados.csv";
    public static final String RUTA_BINARIO = "datos/resultados.dat";
    public static final String RUTA_JSON = "datos/finalistas.json";

    public static void main(String[] args) {
        // TODO: implementar la integración del apartado 4 y gestionar las excepciones.
        System.out.println("Proyecto base de la carrera popular. Implementación pendiente.");

        ParticipacionDAOCSV csv = new ParticipacionDAOCSV();
        ParticipacionDAOBinario dat = new ParticipacionDAOBinario();
        ParticipacionDAOJSON json = new ParticipacionDAOJSON();

        try {
            ArrayList<Participacion> cargados = csv.cargar(RUTA_CSV);
            dat.guardar(RUTA_BINARIO, cargados);
            ArrayList<Participacion> recuperados = dat.cargar(RUTA_BINARIO);
            //TODO: Seleccionar a quienes hayan finalizado
            boolean encontrado = false;

            /* ESTA COMENTADO PORQUE SE QUE FALTA ALGO PARA TERMINARLO Y QUE FUNCIONE PERO NO ME ACUERDO :)
            for (Participacion p : recuperados) {
                if (p.isFinalizada()) {
                    p.setFinalizada(false);
                    encontrado = true;
                }
            }
            json.guardar(RUTA_JSON, recuperados);
            */

//            COMPROBACION DEL TAMAÑO DE LAS LISTAS, DAN 4-4 ME FALTA EL JSON QUE SI LO HUBIERA TERMINADO SEGURO DA 3 :)
            System.out.println(cargados.size());
            System.out.println(recuperados.size());
            // System.out.println(exportados.size());
            //} catch (IOException e) {
            //throw new RuntimeException(e);
        } finally {

        }
    }
}
