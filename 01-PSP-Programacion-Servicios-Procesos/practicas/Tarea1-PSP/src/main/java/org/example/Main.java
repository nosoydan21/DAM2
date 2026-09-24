package org.example;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static ArrayList<String> procesos = new ArrayList();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            System.out.println("Elija una opción:");
            if (input.hasNextInt()) {
                opcion = input.nextInt();
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
                System.out.println("Elija un numero entero, gracias.");
                input.next();
                opcion = 0;
            }

        } while (opcion != 4);
    }

    public static void mostrarMenu(){
        System.out.println("=== GESTOR DE PROCESOS (WINDOWS) ===");
        System.out.println("1. Crear un nuevo proceso en segundo plano");
        System.out.println("2. Listar procesos");
        System.out.println("3. Terminar proceso en segundo plano");
        System.out.println("4. Salir");
    }

    public static void crearProceso(){
        Scanner input2 = new Scanner(System.in);

        System.out.println("Introduce el código del proceso: ");
        String codigo = input2.nextLine();

        procesos.add(codigo + " | Estado: VIVO");

        System.out.println("Proceso creado con codigo: " + codigo);
    }

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

    public static void matarProcesoPorPid(Scanner input){
        System.out.println("Introduce el codigo del proceso: ");
        String codigo = input.nextLine();

        for (int i = 0; i < procesos.size(); i++) {
            String proceso = procesos.get(i);
            if(proceso.startsWith())
        }
    }
}