# Proyecto base del examen de Acceso a Datos

Aplicación Java por consola para procesar los resultados de una carrera popular.
Duración del examen: 110 minutos. Sigue los requisitos del enunciado.

## Requisitos y apertura

- JDK 17 o posterior, configurado en el proyecto y en Maven.
- IntelliJ IDEA, Eclipse u otro IDE con soporte Maven, o Maven por terminal.
- Gson 2.10.1 está declarado en pom.xml. Maven debe descargarlo antes de trabajar sin conexión.

En IntelliJ IDEA:

1. Descomprime el ZIP y abre la carpeta que contiene pom.xml.
2. Importa el proyecto como Maven y espera a que termine la carga de dependencias.
3. Selecciona un JDK 17 o posterior como SDK del proyecto.
4. Ejecuta Main.main(). El directorio de trabajo debe ser la carpeta que contiene pom.xml y datos.

Por terminal, desde esa carpeta:

```bash
mvn compile
mvn exec:java
```

La ejecución inicial solo muestra un mensaje de implementación pendiente. No procesa datos.

## Archivos proporcionados

- `modelo/Participacion.java`: atributos, constructor, getters, setters y toString().
- `dao/ParticipacionDAOCSV.java`: método de lectura pendiente.
- `dao/ParticipacionDAOBinario.java`: métodos de escritura y lectura pendientes.
- `dao/ParticipacionDAOJSON.java`: método de escritura pendiente.
- `Main.java`: rutas y punto de entrada pendientes de integración.
- `datos/resultados.csv`: datos iniciales del examen.

Los archivos Java se encuentran en src/main/java/es/iesvicentemedina/carrera.

## Trabajo del alumnado

Completa los TODO y añade los imports y cambios de modelo necesarios. Las instrucciones
UnsupportedOperationException son marcadores provisionales: debes sustituirlas por tu implementación.
El proyecto no incluye la resolución ni el código de serialización, lectura, filtrado o conversión.

Conserva las firmas de los métodos y la separación entre Main y los DAO. Aplica la gestión
de excepciones y de recursos exigida por el enunciado.

Al completar el examen, la aplicación generará datos/resultados.dat y datos/finalistas.json.
Con el CSV inicial deberá cargar 4 participaciones, recuperar 4 del binario y exportar 3 finalistas.
Entrega el proyecto y los ficheros de datos en un ZIP, sin target ni carpetas de configuración del IDE.

## Preparación antes del examen

El profesorado debe abrir el proyecto en los equipos, cargar las dependencias de Maven y ejecutar
el Main inicial antes del examen. Así se comprueban el JDK, la configuración y el directorio de trabajo,
y las descargas no consumen tiempo de la prueba. La dependencia está configurada, pero el ZIP
no incluye Maven, el JDK ni los JAR descargados.
