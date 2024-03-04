package jpa.controlleurs;

import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jpa.models.Message;
import jpa.services.impl.MessageDaoImpl;

import java.util.List;

@Path("messages")
@Produces({"application/json"})
public class MessageControlleur {
    @PersistenceContext
    private final MessageDaoImpl messageDaoImpl;

    public MessageControlleur() {
        this.messageDaoImpl = new MessageDaoImpl();
    }

    //Recuperer tous les messages
    @GET
    @Path("/")
    public List<Message> getAll() {
        return messageDaoImpl.getAll();
    }

    //Recuperer un message en fonction de son id
    @GET
    @Path("/{messageId}")
    public Message getById(@PathParam("messageId") String id) {
        return messageDaoImpl.getById(id);
    }

    //Création d'un message
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Message create(Message message) {
        return messageDaoImpl.create(message);
    }

    //Modification d'un message
    @PUT
    @Path("/update/{messageId}")
    @Consumes("application/json")
    public Message update(@PathParam("messageId") String id, Message message) {
        return messageDaoImpl.update(id, message);
    }

    @DELETE
    @Path("/delete/{messageId}")
    @Consumes("application/json")
    public void delete(@PathParam("messageId") String id) {
        messageDaoImpl.delete(id);
    }
}
