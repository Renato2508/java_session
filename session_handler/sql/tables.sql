-- POSTGRE SYNTAX
CREATE TABLE sessions (
     id varchar(64) NOT NULL PRIMARY key ,
     access bigint DEFAULT NULL,
     data text DEFAULT NULL
);

-- MYSQL SYNTAX
CREATE TABLE sessions (
     id varchar(64) NOT NULL PRIMARY key ,
     access bigint DEFAULT NULL,
     data text DEFAULT NULL
);


    CREATE USER 'haproxys'@'192.168.43.158';
    GRANT ALL PRIVILEGES ON *.* TO 'haproxys'@'192.168.43.158';
    FLUSH PRIVILEGES;
