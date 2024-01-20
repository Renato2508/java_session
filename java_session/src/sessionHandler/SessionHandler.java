package sessionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import connexion.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;
import java.net.http.HttpRequest;

import java.sql.ResultSet;
import java.util.HashMap;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;



public class SessionHandler{

    Connection connex;

    // _ _ _ CONSTRUCTORS _ _ _

    public SessionHandler(){
        try {
            connex = new Connect().getConnectionPostgresql();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // _ _ _METHODS _ _ _

    protected void gc(int max){}

    
    public void write(Session session) {
        String sql = "INSERT INTO sessions (id, access, data) VALUES ('%s', %d, '%s') ON DUPLICATE KEY Update data = '%s'";

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the data attribute to a JSON string
            //String dataJson = objectMapper.writeValueAsS*'tring(session.getData());
            Gson gson = new Gson();
            String dataJson = gson.toJson(session.getData());
            Instant now = Instant.now();
            long timestamp = now.toEpochMilli();

            System.out.println("DATA: "+dataJson);
            String req = String.format(sql,session.getId(),timestamp, dataJson, dataJson );
            System.out.println("REQ: "+req);

            Statement stm = connex.createStatement();
            stm.executeUpdate(req);
            connex.commit();
            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    protected Session read(String id_sh) {
        String sql = "SELECT data, id FROM sessions WHERE id = ?";
        Session session = null ;

        try (PreparedStatement pstmt = connex.prepareStatement(sql)) {
            pstmt.setString(1, id_sh);

            ResultSet rs = pstmt.executeQuery();

            // Create an ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            if (rs.next()) {
                String dataJson = rs.getString("data");
                HashMap<String, Object> data = objectMapper.readValue(dataJson, HashMap.class);
                session = new Session(id_sh, data, this);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return session;
    }
    protected void destroy(Session session){
        String sql = "DELETE FROM sessions WHERE id = ?";

        try (PreparedStatement pstmt = connex.prepareStatement(sql)) {
            pstmt.setString(1,session.getId());
            pstmt.executeUpdate();
            Statement stm = connex.createStatement();
            connex.commit();


        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected String generateID(HttpServletRequest req) {
        // Récupérer l'adresse IP du client
        String ipAddress = req.getRemoteAddr();

        // Obtenir le timestamp actuel
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        String timestamp = sdf.format(new Date());

        // Générer un ID unique en concaténant l'adresse IP et le timestamp
        String uniqueID = ipAddress + "-" + timestamp;

        return uniqueID;
    }


    public Session getSession(HttpServletRequest req, HttpServletResponse res) {
        // récuperer la variable de cookie
        Cookie[] cookies = req.getCookies();
        String id_sh = null;
    
        // lire l'attribut id_sh
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if ("id_sh".equals(cookie.getName())) {
                    id_sh = cookie.getValue();
                    break;
                }
            }
        }
        
    
        // si présent faire une lecture 
        if (id_sh != null) {
            Session session = this.read(id_sh);
            if(session != null )
                return session  ;
        }
    
        // sinon générer l'id unique en appelant la fontion generateId
        id_sh = generateID(req);
    
        // instancier une session
        Session session = new Session(id_sh, this);
    
        // écrire l'id de la session dans un cookie
        Cookie sessionCookie = new Cookie("id_sh", id_sh);
        res.addCookie(sessionCookie);
    
        // retourner la session
        return session;
    }

    public Connection getConnex() {
        return connex;
    }

    public void setConnex(Connection connex) {
        this.connex = connex;
    }
    
}