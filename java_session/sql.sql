create database session_java;
use session_java;

create table sessions(
    id varchar(64) primary key,
    data text,
    access bigint
);