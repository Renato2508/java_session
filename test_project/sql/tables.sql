CREATE TABLE sessions (
     id varchar(64) NOT NULL PRIMARY key ,
     access timestamp DEFAULT NULL,
     data JSON DEFAULT NULL
);
