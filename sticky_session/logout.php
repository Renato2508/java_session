<?php
   // Inclure la classe OurSession

   
   session_start();
   session_destroy();
   header('location: index.php');
?>