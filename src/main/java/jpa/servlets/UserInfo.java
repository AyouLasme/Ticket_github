package jpa.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.models.Utilisateur;
import jpa.services.impl.UtilisateurDaoImpl;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="userinfo",
        urlPatterns={"/UserInfo"})
public class UserInfo extends HttpServlet {

     private UtilisateurDaoImpl utilisateurDaoImpl;

    @Override
    public void init() throws ServletException{
        // TODO auto-generated method stub
        super.init();
        this.utilisateurDaoImpl = new UtilisateurDaoImpl();
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        // Acceder à mes DAO
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getParameter("name"));
        System.out.println("==> " + utilisateurDaoImpl.create(utilisateur));

        out.println("<HTML>\n<BODY>\n" +
                "<H1>Recapitulatif des informations</H1>\n" +
                "<UL>\n" +
                " <LI>Nom: "
                + request.getParameter("name") + "\n" +
                " <LI>Prenom: "
                + request.getParameter("firstname") + "\n" +
                " <LI>Email: "
                + request.getParameter("email") + "\n" +
                "</UL>\n" +
                "</BODY></HTML>");
    }
}