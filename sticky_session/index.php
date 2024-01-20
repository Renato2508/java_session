<?php

   

    session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
</head>
<body>
    <?php
         if(!isset($_SESSION['nom'])){
    ?>
    <form action="login.php" method = "POST" name = 'nom'>
        NOM: <input type="text" name = "nom">
        <input type="submit" value = "OK" >
    </form>

    <?php
         }
         else{
            echo 'RENATO souhaite la bienvenue à: '.$_SESSION['nom'];
            echo '<a href="logout.php">DECONNEXION</a>';
         }
    ?>
    
</body>
</html>