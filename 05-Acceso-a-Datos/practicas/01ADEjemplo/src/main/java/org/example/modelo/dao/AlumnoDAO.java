package org.example.modelo.dao;

import org.example.modelo.Alumno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {
    String fichero="datos.csv";

    public ArrayList<Alumno> cargarAlumnos() throws IOException {
        //FileReader, BufferedReader
        ArrayList listaAlumnos=new ArrayList();
        FileReader ficheroEntrada = new FileReader(fichero);
        BufferedReader flujoEntrada = new BufferedReader(ficheroEntrada);
        String linea=flujoEntrada.readLine();
        while(linea!=null){
            String[] datos =  linea.split(";");

        }
        return null;
    }

    public static void guardarAlumnos(List<Alumno> alumnos){

    }
}
