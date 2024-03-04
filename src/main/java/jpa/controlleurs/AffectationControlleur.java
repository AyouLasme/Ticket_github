package jpa.controlleurs;

import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jpa.models.Affectation;
import jpa.models.Ticket;
import jpa.models.Utilisateur;
import jpa.services.impl.AffectationDaoImpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Path("affectations")
@Produces({"application/json"})
public class AffectationControlleur {

    @PersistenceContext
    private final AffectationDaoImpl affectationDaoImpl;

    public AffectationControlleur() {
        this.affectationDaoImpl = new AffectationDaoImpl();
    }

    //Récupérer toutes les affectations
    @GET
    @Path("/")
    public List<Affectation> getAll() {
        return affectationDaoImpl.getAll();
    }

    //Récupérer une affectation en fonction de son id
    @GET
    @Path("/{affectationId}")
    public Affectation getById(@PathParam("affectationId") String id) {
        return affectationDaoImpl.getById(id);
    }

    //Création d'une affectation
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Affectation create(Affectation affectation) {
        return affectationDaoImpl.create(affectation);
    }

    //Affectation d'un ticket à plusieurs utilisateurs en même temps
    @POST
    @Path("/createAllUtilisateurs")
    @Consumes("application/json")
    public List<Affectation> create(Map<String, Object> map) {
        if (map == null || !map.containsKey("ticket") || !map.containsKey("utilisateurs")) {
            // Gérer le cas où la clé "ticket" ou "utilisateurs" est manquante dans la map
            return Collections.emptyList(); // Ou une autre gestion d'erreur appropriée
        }

        Object ticketObject = map.get("ticket");
        Object utilisateursObject = map.get("utilisateurs");

        if (!(ticketObject instanceof Ticket) || !(utilisateursObject instanceof List<?>)) {
            // Gérer le cas où le type d'objet n'est pas conforme aux attentes
            return Collections.emptyList(); // Ou une autre gestion d'erreur appropriée
        }

        Ticket ticket = (Ticket) ticketObject;
        List<Utilisateur> utilisateurs = (List<Utilisateur>) utilisateursObject;

        // Assurez-vous que les objets ne sont pas nuls avant d'appeler la méthode create
        if (ticket == null || utilisateurs == null) {
            // Gérer le cas où les objets sont nuls
            return Collections.emptyList(); // Ou une autre gestion d'erreur appropriée
        }

        return affectationDaoImpl.create(ticket, utilisateurs);
    }


    //Modification d'une affectation
    @PUT
    @Path("/update/{affectationId}")
    @Consumes("application/json")
    public Affectation update(@PathParam("affectationId") String id, Affectation affectation) {
        return affectationDaoImpl.update(id, affectation);
    }

    @DELETE
    @Path("/delete/{affectationId}")
    @Consumes("application/json")
    public void delete(@PathParam("affectationId") String id) {
        affectationDaoImpl.delete(id);
    }
}
