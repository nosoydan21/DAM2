package es.iesvicentemedina.carrera.dao;

import com.google.gson.Gson;
import es.iesvicentemedina.carrera.modelo.Participacion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ParticipacionDAOJSON {
    public void guardar(String ruta, ArrayList<Participacion> listaParticipaciones) throws IOException {
        Gson gson = new Gson();

        try (FileWriter fw = new FileWriter(ruta)) {
            gson.toJson(listaParticipaciones, Participacion.class);
            fw.close();
        }
    }
}
