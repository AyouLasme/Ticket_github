package jpa.controlleurs;

import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jpa.models.Utilisateur;
import jpa.services.impl.UtilisateurDaoImpl;

import java.util.List;

@Path("utilisateurs")
@Produces({"application/json"})
public class UtilisateurControlleur {
    @PersistenceContext
    private final UtilisateurDaoImpl utilisateurDaoImpl;

    public UtilisateurControlleur() {
        this.utilisateurDaoImpl = new UtilisateurDaoImpl();
    }

    //Recuperer tous les utilisateurs
    @GET
    @Path("/")
    public List<Utilisateur> getAll() {
        return utilisateurDaoImpl.getAll();
    }

    //Recuperer un utilisateur à patir de son id
    @GET
    @Path("/{utilisateurId}")
    public Utilisateur getById(@PathParam("utilisateurId") String id) {
        return utilisateurDaoImpl.getById(id);
    }

    //Creation d'un Utilisateur
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Utilisateur create(Utilisateur utilisateur) {
        return utilisateurDaoImpl.create(utilisateur);
    }

    //Modifier un utilisateur
    @PUT
    @Path("/update/{utilisateurId}")
    @Consumes("application/json")
    public Utilisateur update(@PathParam("utilisateurId") String id, Utilisateur user) {
        return utilisateurDaoImpl.update(id, user);
    }

    //Supprimer un user
    @DELETE
    @Path("/delete/{utilisateurId}")
    @Consumes("application/json")
    public void delete(@PathParam("utilisateurId") String id) {
        utilisateurDaoImpl.delete(id);
    }

}
