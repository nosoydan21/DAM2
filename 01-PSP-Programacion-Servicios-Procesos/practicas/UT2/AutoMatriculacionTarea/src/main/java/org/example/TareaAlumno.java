package org.example;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.concurrent.ThreadLocalRandom;

public class TareaAlumno implements Runnable {
    private final String dni;

    private final int MAX_INTENTO = 3;
    private final int TIEMPO_BASE_ESPERA_MS = 1000;

    public TareaAlumno(String dni) {
        this.dni = dni;
    }

    @Override
    public void run() {
        String nombreHilo = Thread.currentThread().getName();

        //  TODO: instanciar el servidor ServidorMatriculación
        ServidorMartriculacion servidor = ServidorMartriculacion.getInstance();

        try {
            System.out.println("[" + nombreHilo + "] Concectando al servicio de matrícula");

            int tiempoEspera = TIEMPO_BASE_ESPERA_MS;
            boolean dentroCola;
            int intento = 0;

            for (int i = 1; i <= MAX_INTENTO; i++) {
                dentroCola = (servidor.entrarEnSalaEspera(this.dni));
                if (dentroCola) {
                    System.out.println("Ha accedido a la sala de espera");
                    break;
                }

                if (intento < MAX_INTENTO) {
                    int jitter = ThreadLocalRandom.current().nextInt(0, 300);
                    tiempoEspera = tiempoEspera + jitter;

                    System.out.println("[" + nombreHilo + "] Servido saturado (Intento " + intento + "/" + MAX_INTENTO + "). Reintentando en " + tiempoEspera + "ms");

                }
            }
            /*
            if (!servidor.entrarEnSalaEspera(this.dni)){
                System.out.println("["+ nombreHilo + "] Servidor saturado");
            }
            */

            // 1 Entrar en la sala de espera

            System.out.println("[" + nombreHilo + "] Ha accedido a la sala de espera");

            // 2 Validar expediente
            System.out.println("[" + nombreHilo + "] Validando expediente");
            servidor.validarExpediente(this.dni);

            // 3 intentarReservaPlaza
            System.out.println("[" + nombreHilo + "] Solicitando plaza");
            boolean reservado = servidor.intentarReservarPlaza();

            if (reservado) {
                System.out.println("[" + nombreHilo + "] Ha conseguido plaza. Plazas restantes: " + servidor.getPlazasDisponibles());
            } else {
                System.out.println("[" + nombreHilo + "] No ha conseguido plaza.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
