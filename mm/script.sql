
--- creation de l'utilisateur auquel l'autre master ira se connecter
create user 'mirindra'@'192.168.43.20' identified by 'mirindra'; 
grant replication slave on *.* to 'mirindra'@'192.168.43.20';

-- test 
mysql -u --user -h ip -p


stop slave; 
CHANGE MASTER TO MASTER_HOST = '192.168.43.20', MASTER_USER = 'replica', MASTER_PASSWORD = 'replica', MASTER_LOG_FILE = 'mysql-bin.000016', MASTER_LOG_POS = 14069; 
start slave ; 