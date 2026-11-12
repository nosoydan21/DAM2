# Reservas de Equipo (JDBC)

Proyecto base Maven para la práctica de JDBC (DAM - Acceso a Datos, UD2). Equipo EJM.

## Requisitos

- JDK 17+
- Maven 3.9+

## Estructura

```text
.
├── pom.xml
└── src
    ├── main
    │   ├── java/es/ejm/reservas
    │   └── resources
    └── test
        └── java/es/ejm/reservas
```

## Base de datos (MariaDB)

1. Copia `src/main/resources/db.properties.example` a `src/main/resources/db.properties`.
2. Ajusta `db.url`, `db.user` y `db.password`.

> `db.properties` no está ignorado por defecto; si contiene credenciales reales, añádelo al `.gitignore`.

## Uso

```bash
mvn clean test
mvn -q exec:java
```
