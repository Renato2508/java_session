<?php
   // Inclure la classe MySess
   
   require_once 'OurSession.php';

   $sessionHandler = new OurSession();
   // Utiliser le SESSION_HANDLER POUR GERER LES VARIABLES DE SESSIONS
   //session_set_save_handler([$sessionHandler,'open'], [$sessionHandler,'close'], [$sessionHandler,'read'], [$sessionHandler,'write'], [$sessionHandler,'destroy'],[$sessionHandler,'gc']); 
   session_set_save_handler($sessionHandler, true);  
   
   session_start();

    $_SESSION['nom'] = $_POST['nom'];
    header('location: index.php');
?>