package org.example;

import java.util.concurrent.*;

public class ServidorMartriculacion {
    private static final ServidorMartriculacion INSTANCIA = new ServidorMartriculacion();

    // Recursos concurrentes encapsulados dentro del servicio
    private final BlockingQueue<String> salaEspera = new ArrayBlockingQueue<>(4);
    private final Semaphore servidorValidacion = new Semaphore(2);
    private final Semaphore mutexPlazas = new Semaphore(1);

    private int plazasDisponibles = 3;

    private ServidorMartriculacion() {}

    public static ServidorMartriculacion getInstance() {
        return INSTANCIA;
    }

    // boolean entrarEnSalaEspera(String dni)
    // PASO 1 ->  Paso por la consola de espera
    public boolean entrarEnSalaEspera(String dni) throws InterruptedException {
        return salaEspera.offer(dni, 1500, TimeUnit.MILLISECONDS);
    }


    // void validarExpediente(String dni)
    public void validarExpediente(String dni) throws InterruptedException {
        servidorValidacion.acquire();
        try {
            salaEspera.remove(dni);
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000,2000));
        } finally {
            servidorValidacion.release();
        }
    }
    // boolean intentarReservarPlaza(String dni)
    public boolean intentarReservarPlaza() throws InterruptedException {
        mutexPlazas.acquire();
        try {
            if (plazasDisponibles > 0) {
                plazasDisponibles--;
                return true;
            }
        } finally {
            mutexPlazas.release();
        }
        return false;
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }
}
