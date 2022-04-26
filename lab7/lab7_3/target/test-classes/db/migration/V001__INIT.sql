CREATE TABLE tqs_employee (
   id int PRIMARY KEY,
   name varchar(255) not null,
   email varchar(255) not null
);

CREATE SEQUENCE hibernate_sequence START 1;