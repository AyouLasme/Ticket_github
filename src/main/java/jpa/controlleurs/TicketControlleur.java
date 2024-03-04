package jpa.controlleurs;

import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jpa.models.Affectation;
import jpa.models.Ticket;
import jpa.models.Utilisateur;
import jpa.services.impl.TicketDaoImpl;

import java.util.List;
import java.util.Map;

@Path("tickets")
@Produces({"application/Json"})
public class TicketControlleur {

    @PersistenceContext
    private final TicketDaoImpl ticketDaoImpl;


    public TicketControlleur() {
        this.ticketDaoImpl = new TicketDaoImpl();
    }

    //Récuperer tous les tickets
    @GET
    @Path("/")
    public List<Ticket> getAllTicket() {
        return ticketDaoImpl.getAll();
    }

    //Recuperer un seul ticket à partir de son id
    @GET
    @Path("/{ticketId}")
    public Ticket getTicket(@PathParam("ticketId") String id) {
        return ticketDaoImpl.getById(id);
    }

    // Creation d'un ticket sans tag
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Ticket createTicket(Ticket ticket){
        return ticketDaoImpl.create(ticket);
    }

    // Creation d'un ticket avec tag
    @POST
    @Path("/createTicketAvecTags")
    @Consumes("application/json")
    public Ticket createTicketAvecTags(Map<String, Object> hashMap) {
        if (hashMap == null || !hashMap.containsKey("ticket") || !hashMap.containsKey("tagLibelleList"))
            return null;

        Ticket ticket = (Ticket) hashMap.get("ticket");
        List<String> tagLibelleList = (List<String>) hashMap.get("tagLibelleList");

        if (ticket == null || tagLibelleList == null)
            return null;

        return ticketDaoImpl.create(ticket, tagLibelleList);
    }



    //Recupere les tickets en fonction des tags
    @GET
    @Path("/tag/{libelleTag}")
    public List<Ticket> getTicketByTag(@PathParam("libelleTag") String tagLibelle){
        return ticketDaoImpl.getTicketByTagLibelle(tagLibelle);
    }

    //Supprimer un ticket
    @DELETE
    @Path("/delete/{ticketId}")
    @Consumes("application/json")
    public void deleteTicket(@PathParam("ticketId") String id){
        ticketDaoImpl.delete(id);
    }
}



