package connexion;

import java.sql.*;

/**
 * La classe Connect gère la connexion à une base de données PostgreSQL.
 */
public class Connect {

  /**
   * Établit une connexion à une base de données PostgreSQL.
   *
   * @return Une instance de Connection représentant la connexion à la base de données.
   * @throws Exception en cas d'erreur lors de la connexion.
   */

   
  public Connection getConnectionPostgresql() throws Exception {
  
    Connection connect = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connect =
        DriverManager.getConnection(
          "jdbc:mysql://192.168.43.158:3306/session_java",
          "haproxys",
          ""
        );
        connect.setAutoCommit(false);
    } catch (Exception e) {

      throw e;
    }
    return connect;
  }
}
