package servlet;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import sessionHandler.*;

public class LogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        System.out.println("LOGSERVLET");
        System.out.println("Chargement de la variable de session");

        // chargement d'une session depuis la base ou création 
        SessionHandler sh = new SessionHandler();
        Session session = sh.getSession(request, response);

        if(session.getAttribute("nom") == null){
            System.out.println("SESSION EXPIREE OU NOUVELLE CONNEXION");
            String nom = request.getParameter("nom");
            LocalDateTime now = LocalDateTime.now();        
            System.out.println("Heure de connexion: "+now);
             session.setAttribute("nom", nom );
             session.setAttribute("h_connex", String.valueOf(now));
        }

        String nom  = (String)session.getAttribute("nom");
        out.print("NOM: "+nom);

        String heure = session.getAttribute("h_connex").toString();        
        out.print("H connex: "+heure);   
        
        request.setAttribute("nom", nom);
        request.setAttribute("h_connex", heure);
        sh.getConnex().close();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/accueil.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
