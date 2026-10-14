# DAM-2

Repositorio de apuntes, prácticas y ejercicios del segundo curso del Ciclo Formativo de Grado Superior en
**Desarrollo de Aplicaciones Multiplataforma (DAM)**.

Se comparte entre el equipo de clase y el de casa, por lo que este README explica cómo dejar el entorno listo
para que **todo compile y se ejecute igual en los dos sitios**.

---

## 1. Requisitos del entorno

| Herramienta | Versión recomendada | Notas |
|---|---|---|
| **JDK** | **Eclipse Temurin 21 (LTS)** | Versión usada en todos los `pom.xml`. Instalado en `C:\Users\<usuario>\.jdks`. |
| **Maven** | **3.9.16** | Para compilar los módulos Java desde terminal. Instalado en `C:\Users\<usuario>\tools`. |
| **IntelliJ IDEA** | Ultimate 2026.2 o superior | Proyecto principal (Maven + Gradle). |
| **Android Studio** | Rabbit (2026.2) o superior | Solo para la asignatura **PMDM** (proyectos Gradle). |
| **Git** | 2.45+ | Para sincronizar. |
| **Android SDK** | Platform 37 / Build-Tools 36.0.0 | Se instala con Android Studio. |

> El proyecto está pensado para **JDK 21 (LTS)**. No uses versiones no LTS (26/27): los módulos de la carpeta
> `03-DI-Desarrollo-Interfaces` y `05-Acceso-a-Datos` fallaban porque pedían Java 26, que no está instalado.

### Variables de entorno (Windows)

```
JAVA_HOME = C:\Users\<usuario>\.jdks\temurin-21
Path      = %JAVA_HOME%\bin; C:\Users\<usuario>\tools\apache-maven-3.9.16\bin; ...
```

> En esta máquina está instalado **Eclipse Temurin 21.0.12.1 (LTS)** en `C:\Users\<usuario>\.jdks\temurin-21`.
> Cualquier JDK **21 LTS** (Temurin, Zulu, Oracle...) sirve, siempre que sea la **21**.
> **Maven 3.9.16** está en `C:\Users\<usuario>\tools\apache-maven-3.9.16` (`MAVEN_HOME`).

Comprobar:

```bat
java -version     :: debe mostrar 21.x
mvn -version
```

---

## 2. Estructura del repositorio

```
DAM2/
├── 01-PSP-Programacion-Servicios-Procesos/   (Maven, Java)
├── 02-SGE-Sistema-Gestion-Empresarial/
├── 03-DI-Desarrollo-Interfaces/              (Maven, Java + Swing)
├── 04-PMDM-Programacion-Multimedia-.../      (Gradle, Android)
│   └── practicas/
│       ├── Actividades/        (Gradle + AGP 9.4.1)
│       ├── ejercicioFrame/     (Gradle + AGP 9.4.1)
│       ├── WidgetsBasicos/     (Gradle + AGP 9.4.1)
│       ├── webviewVM/          (Gradle + AGP 9.4.1)
│       └── UT1-HolaMundo/      (Gradle + AGP 9.3.2)
├── 05-Acceso-a-Datos/                        (Maven, Java)
├── 06-PPY-Programacion-Python/
├── 07-PI-Proyecto-Intermodular/
├── 08-IPEII-Empleabilidad/
├── 09-SOT-Sostenibilidad/
├── recursos-generales/
└── sincronizar.bat                           (script de sincronización con GitHub)
```

---

## 3. Sincronización con GitHub

Doble clic en **`sincronizar.bat`**. Hace `pull` → `add` → `commit` → `push`.
La primera vez configura automáticamente tu identidad de Git.

Desde terminal, manualmente:

```bat
git pull
git add -A
git commit -m "Descripcion de los cambios"
git push
```

---

## 4. Módulos Maven (Java)

Cada proyecto es independiente (`pom.xml` propio). Con IntelliJ basta con abrir la carpeta del proyecto y
esperar a que importe el POM. Desde terminal, dentro de cada carpeta:

```bat
mvn clean compile      :: compilar
mvn exec:java          :: ejecutar (si tiene exec.mainClass)
mvn clean package      :: generar el .jar en target/
```

Proyectos Maven:

- `01-PSP-.../practicas/UT1/Tarea1-PSP`
- `01-PSP-.../practicas/UT1/Diagnostico de red`
- `01-PSP-.../practicas/UT1/Ejercicio_Pipeline`
- `01-PSP-.../practicas/UT1/ProgramacionMultiprocesos`
- `01-PSP-.../practicas/UT1/Procesamiento_de_datos_en_tuberia`
- `03-DI-.../practicas/EjemplosJOptionPanel`
- `03-DI-.../practicas/PrimeraInterfaz`
- `03-DI-.../practicas/Tarea1_UT2`
- `05-Acceso-a-Datos/practicas/01 AD Ejercicio_1`

Todos usan `<maven.compiler.release>21</maven.compiler.release>`.

---

## 5. Módulos Android / Gradle (PMDM)

Incluyen el **Gradle Wrapper**, así que no hace falta instalar Gradle: se descarga solo la versión correcta.

```bat
cd 04-PMDM-.../practicas/WidgetsBasicos
gradlew.bat assembleDebug     :: compilar APK de debug
gradlew.bat installDebug      :: instalar en emulador/dispositivo
```

- `local.properties` apunta al Android SDK y **no se sube a Git** (cada equipo lo genera solo).
  Android Studio lo crea automáticamente al abrir el proyecto.
- Versiones fijadas: **AGP 9.4.1 + Gradle 9.6.0** (y AGP 9.3.2 + Gradle 9.5.0 en `UT1-HolaMundo`).
- Para abrirlos, usa **Android Studio** o IntelliJ con el plugin de Android.

---

## 6. Python (06-PPY)

Prácticas de Python (`practicas/UT1/main.py`). Se pueden ejecutar con cualquier Python 3.10+.

---

## 7. Problemas frecuentes

| Síntoma | Solución |
|---|---|
| `release version 26 not supported` | Estás usando un JDK distinto de 21. Configura JDK 21 en `File > Project Structure > SDK`. |
| `sdk.dir` / `local.properties` | Abre el proyecto Android con Android Studio una vez; lo genera automáticamente. |
| `Please tell me who you are` (git) | Ejecuta `sincronizar.bat` una vez, o configura `git config --global user.email ...`. |
| Android no encuentra el SDK | Comprueba `sdk.dir` en `local.properties`. |
| Gradle no baja | Necesitas conexión a Internet la primera vez (descarga AGP, dependencias y el SDK). |
