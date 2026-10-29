package es.iesvicentemedina.carrera.dao;

import es.iesvicentemedina.carrera.modelo.Participacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParticipacionDAOCSV {
    public ArrayList<Participacion> cargar(String ruta) {
        ArrayList<Participacion> listaParticipaciones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            int numLinea = 1;
            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                numLinea++;

                try {
                    String datos[] = linea.split(";");
                    if (datos.length != 4) {
                        System.err.println("Error en la línea " + linea + " --> Deben haber 4 campos y hay " + datos.length);
                        continue;
                    }

                    int numDorsal = Integer.parseInt(datos[0].trim());
                    String nombre = datos[1].trim();
                    int tiempoSeg = Integer.parseInt(datos[2].trim());
                    Boolean finalizada = Boolean.parseBoolean(datos[3].trim());

                    if (numDorsal <= 0) {
                        System.err.println("Error en la línea " + linea + " --> Numero de dorsal negativo: " + numDorsal);
                        continue;
                    }

                    if (tiempoSeg < 0) {
                        System.err.println("Error en la línea" + linea + " --> Tiempo en segundo negativo: " + tiempoSeg);
                        continue;
                    }

                    listaParticipaciones.add(new Participacion(numDorsal, nombre, tiempoSeg, finalizada));
                } catch (NumberFormatException e) {
                    System.err.println("Formato de número datos incorrecto en linea: " + numLinea + e.getMessage());
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + e.getMessage());
        }
        return listaParticipaciones;
    }
}
