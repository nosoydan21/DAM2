package es.iesvicentemedina.carrera.dao;

import es.iesvicentemedina.carrera.modelo.Participacion;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipacionDAOBinario {

    public void guardar(String ruta, ArrayList<Participacion> listaParticipaciones) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(ruta);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(listaParticipaciones);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Participacion> cargar(String ruta) {
        ArrayList<Participacion> listaParticipaciones = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(ruta);
            ois = new ObjectInputStream(fis);

            listaParticipaciones = (ArrayList<Participacion>)ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Clase no encontrada " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + e.getMessage());
        }  finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listaParticipaciones;
    }

}
