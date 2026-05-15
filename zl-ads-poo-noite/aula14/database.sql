create database hollywood;

use hollywood;

create table filme (
    id int AUTO_INCREMENT,
    titulo char(100) UNIQUE NOT NULL,
    genero char(50) NOT NULL,
    lancamento date,
    PRIMARY KEY(id)
);