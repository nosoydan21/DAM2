package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * ============================================================
 * TAREA 1 PSP: GESTOR DE "PROCESOS" CON MENÚ
 * ============================================================
 * OJO: esto es una SIMULACIÓN. No lanza procesos reales; cada "proceso"
 * es solo un texto ("codigo | Estado: VIVO") guardado en una lista.
 * Sirve para practicar un menú con Scanner y la lógica de crear/listar/terminar.
 *
 * Para hacerlo REAL: guardar objetos Process en un Map<Long, Process> con su pid()
 * como clave, lanzarlos con ProcessBuilder.start() y terminarlos con destroy().
 */
public class Main {
    // Lista donde guardamos los "procesos" simulados (ArrayList sin tipo genérico = aviso del compilador)
    static ArrayList<String> procesos = new ArrayList();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            System.out.println("Elija una opción:");
            // hasNextInt() comprueba que lo escrito sea un número ANTES de leerlo
            if (input.hasNextInt()) {
                opcion = input.nextInt();
                input.nextLine(); // consume el salto de línea que deja nextInt() (si no, el siguiente nextLine() leería vacío)
                System.out.println("-----------------------------");

                switch (opcion) {
                    case 1:
                        crearProceso();
                        break;
                    case 2:
                        listarProcesos();
                        break;
                    case 3:
                        matarProcesoPorPid(input);
                        break;
                    case 4:
                        System.out.println("Saliendo del programa, muchas gracias");
                        break;
                    default:
                        System.out.println("Opción no valida, por favor elija una de las 4 opciones");
                }
            } else {
                // El usuario escribió algo que no es un número
                System.out.println("Elija un numero entero, gracias.");
                input.next();  // descartamos el texto inválido para no entrar en bucle infinito
                opcion = 0;    // valor cualquiera distinto de 4 para que el bucle continúe
            }

        } while (opcion != 4); // el menú se repite hasta elegir 4 (Salir)
    }

    public static void mostrarMenu(){
        System.out.println("=== GESTOR DE PROCESOS (WINDOWS) ===");
        System.out.println("1. Crear un nuevo proceso en segundo plano");
        System.out.println("2. Listar procesos");
        System.out.println("3. Terminar proceso en segundo plano");
        System.out.println("4. Salir");
    }

    // Opción 1: pide un código y lo añade a la lista como VIVO
    public static void crearProceso(){
        // (Crea un segundo Scanner sobre System.in; funciona, pero lo ideal es reutilizar el del main)
        Scanner input2 = new Scanner(System.in);

        System.out.println("Introduce el código del proceso: ");
        String codigo = input2.nextLine();

        procesos.add(codigo + " | Estado: VIVO");

        System.out.println("Proceso creado con codigo: " + codigo);
    }

    // Opción 2: recorre la lista y muestra cada proceso
    public static void listarProcesos(){
        if (procesos.isEmpty()) {
            System.out.println("No procesos encontrados");
            return;
        } else {
            for (String proceso : procesos) {
                System.out.println("PID:" + proceso);
            }
        }
    }

    // Opción 3: busca por código y lo marca como TERMINADO (no lo borra de la lista)
    public static void matarProcesoPorPid(Scanner input){
        System.out.println("Introduce el codigo del proceso: ");
        String codigo = input.nextLine();

        for (int i = 0; i < procesos.size(); i++) {
            String proceso = procesos.get(i);
            // startsWith(codigo + " |") evita confundir "1" con "10"
            if (proceso.startsWith(codigo + " |")) {
                procesos.set(i, codigo + " | Estado: TERMINADO"); // set = reemplaza el elemento
                System.out.println("Proceso terminado: " + codigo);
                return;
            }
        }
        System.out.println("No se encontro ningun proceso con el codigo: " + codigo);
    }
}
