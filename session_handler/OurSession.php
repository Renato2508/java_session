<?php
    require_once('connexion.php');

    
    class OurSession implements SessionHandlerInterface {
        private $pdo = null;
        private $stmt = null;
        public $error = "";

        public function __construct( $session_time_expiration = 0.001 ) {
            // connexion a la base
            $this->pdo = connect();
        }
    
        function open ($path, $name) { return true; }
    
        function close () {
            if ($this->stmt !== null) { $this->stmt = null; }
            if ($this->pdo !== null) { $this->pdo = null; }
            return true;
        }
    
        function read ($id) {
            // récupération des données de session depuis la DB
            $sql = "SELECT data FROM sessions WHERE id='%s'";
            $sql = sprintf(  $sql , $id );
            $stmt = $this->pdo->query($sql);
            //echo $sql.'</br>';
            $result = $stmt->fetch(PDO::FETCH_ASSOC);

            if ($result) {
                return $result['data'];
            } else {
                return '';
            }
        }
    
        function write ($id, $data) {
            // stockage des données de session dans la DB
            //$sql = "INSERT INTO sessions VALUES ( '%s' , %s , '%s') ON CONFLICT (id) DO UPDATE SET data = '%s'";
            $sql = "INSERT INTO sessions VALUES ( '%s' , %s , '%s') ON DUPLICATE KEY UPDATE data = '%s'";
            $sql = sprintf( $sql , $id , time() , $data , $data );
            $this->pdo->exec($sql);
            //echo $sql.'</br>';
            //var_dump($data);
            return true;
        }
    
        function destroy ($id) {
            // suppressions
            $sql = "DELETE FROM sessions WHERE id='%s'"; 
            $sql = sprintf( $sql , $id ) ;
            echo $sql;
            $this->pdo->exec( $sql );
            echo 'VITA VO';
            return true;
        }
    
        function gc ($max) {
            // expiration de session
            $sql = "DELETE FROM sessions WHERE access < %g";
            $sql = sprintf( $sql , (time() - $max) );
            ////echo $sql.'</br>';
            $this->exec($sql);
            return true;
        }
        
    }
?>