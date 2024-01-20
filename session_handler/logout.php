<?php
   // Inclure la classe OurSession

   require_once 'OurSession.php';

   $sessionHandler = new OurSession();
   // Utiliser le SESSION_HANDLER POUR GERER LES VARIABLES DE SESSIONS
   //session_set_save_handler([$sessionHandler,'open'], [$sessionHandler,'close'], [$sessionHandler,'read'], [$sessionHandler,'write'], [$sessionHandler,'destroy'],[$sessionHandler,'gc']);   
   session_set_save_handler($sessionHandler, true);
   session_start();
   session_destroy();
   header('location: index.php');
?>