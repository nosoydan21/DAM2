# UT1 PSP - Programación multiproceso (código comentado)

Orden recomendado de lectura:

1. ProgramacionMultiprocesos/LectorProceso      -> lanzar un proceso y leer su salida
2. ProgramacionMultiprocesos/RedireccionProceso -> redirigir salida (inheritIO, ficheros)
3. ProgramacionMultiprocesos/ejercicios/Ejercicio1 -> ping guardado en fichero + código de salida
4. ProgramacionMultiprocesos/ControlCicloVidaProceso -> waitFor con timeout, destroy, onExit
5. ProgramacionMultiprocesos/InteraccionProceso -> enviar datos al hijo (stdin) y cerrar el canal
6. Diagnostico de red/Main                      -> ping según SO + log en modo añadir
7. Diagnostico de red/Lanzador + Registrador    -> padre lanza hijo Java con argumentos y códigos de salida
8. Procesamiento_de_datos_en_tuberia            -> Lector | Filtro | Ordenador con startPipeline
9. Ejercicio_Pipeline                           -> pendiente de hacer (mismo patrón que el 8)
10. Tarea1-PSP                                  -> gestor con menú (simulado)

Resumen: ProcessBuilder prepara -> start() lanza -> getInputStream (leer salida),
getOutputStream (enviar entrada), redirect* (ficheros), waitFor (esperar + código),
destroy (matar), startPipeline (encadenar procesos).
