-- Crear la base de dades
CREATE DATABASE IF NOT EXISTS ChessClub;
USE ChessClub;

-- Taula countries
CREATE TABLE IF NOT EXISTS countries (
    id          INTEGER      PRIMARY KEY,
    countryName VARCHAR(40)
);

-- Taula players (amb clau forana a countries)
CREATE TABLE IF NOT EXISTS players (
    id          INTEGER      PRIMARY KEY,
    dni         VARCHAR(20),
    playerName  VARCHAR(40),
    points      INT          NOT NULL,
    idCountry   INTEGER,
    FOREIGN KEY (idCountry) REFERENCES countries(id)
);

-- Permisos (ajusta l'usuari i host als teus valors)
GRANT ALL ON ChessClub.* TO 'root'@'localhost';
FLUSH PRIVILEGES;