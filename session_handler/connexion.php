<?php
function connect(){
    $host = "192.168.43.158"; // Adresse de la base de données PostgreSQL
    $port = "3306"; // Port PostgreSQL (généralement 5432)
    $dbname = "sessionhandlerphp"; // Nom de la base de données
    $user = "haproxys"; // Nom d'utilisateur PostgreSQL
    $password = ""; // Mot de passe PostgreSQL

    try{
        $dbh = new PDO("mysql:host=$host;port=$port;dbname=$dbname;user=$user;password=$password");
        return $dbh;
    }catch(Exception $e){
        echo $e;
    }


}
?>