-- Crear la base de dades
CREATE DATABASE IF NOT EXISTS ejercicios_examen;
USE ejercicios_examen;

-- Crear la taula Local
CREATE TABLE IF NOT EXISTS Local (
    id              INTEGER      PRIMARY KEY,
    metres_quadrats INTEGER      NOT NULL,
    preu            FLOAT        NOT NULL,
    tipus           VARCHAR(20)  NOT NULL
);

-- Donar permisos a l'usuari (ajusta 'nostre_usuari' i 'password' als teus valors)
GRANT ALL ON ejercicios_examen.* TO 'root'@'localhost';
FLUSH PRIVILEGES;