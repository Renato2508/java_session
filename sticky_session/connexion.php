<?php
function connect(){
    $host = "192.168.43.158"; // Adresse de la base de données PostgreSQL
    $port = "5432"; // Port PostgreSQL (généralement 5432)
    $dbname = "sessionhandler"; // Nom de la base de données
    $user = "postgres"; // Nom d'utilisateur PostgreSQL
    $password = "root"; // Mot de passe PostgreSQL

    try{
        $dbh = new PDO("pgsql:host=$host;port=$port;dbname=$dbname;user=$user;password=$password");
        return $dbh;
    }catch(Exception $e){
        echo $e;
    }
}
?>