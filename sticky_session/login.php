<?php
   // Inclure la classe MySess
  session_start();

    $_SESSION['nom'] = $_POST['nom'];
    header('location: index.php');
?>