create database zl_pet_tarde;

use zl_pet_tarde;

create table pet ( 
    id int AUTO_INCREMENT,
    nome char(100) not null,
    tipo char(30) not null,
    nascimento date,
    PRIMARY KEY(id)
);

select * from pet;