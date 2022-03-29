CREATE DATABASE IF NOT EXISTS flashcards;
USE flashcards;

CREATE TABLE IF NOT EXISTS boxes (
    id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    day_of_learn int(30),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cards (
    id int NOT NULL AUTO_INCREMENT,
    basic_word VARCHAR(100) NOT NULL,
    translated_word VARCHAR(100) NOT NULL,
    id_box int,
    bucket int,
    iteration int,
    FOREIGN KEY (id_box) REFERENCES boxes (id),
    PRIMARY KEY (id)
);
