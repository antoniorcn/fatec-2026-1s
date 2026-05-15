create database agenda;

use agenda;

create table contato ( 
    id int AUTO_INCREMENT,
    nome char(100) not null,
    telefone char(30) not null,
    email char(100) not null,
    PRIMARY KEY(id)
);

create table pet ( 
    id int AUTO_INCREMENT,
    nome char(100) not null,
    tipo char(30) not null,
    nascimento date,
    PRIMARY KEY(id)
);

select * from contato;