package jpa.controlleurs;

import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jpa.models.Tag;
import jpa.services.impl.TagDaoImpl;

import java.util.List;

@Path("tags")
@Produces({"application/json"})
public class TagControlleur {
    @PersistenceContext
    private final TagDaoImpl tagDaoImpl;


    public TagControlleur() {
        this.tagDaoImpl = new TagDaoImpl();
    }

    //Recuperer tous les tags
    @GET
    @Path("/")
    public List<Tag> getAllTag(){
        return tagDaoImpl.getAll();
    }

    //Recuperer un seul tag en fonction de son id
    @GET
    @Path("/{tagId}")
    public Tag getTag(@PathParam("tagId")String id){
        return tagDaoImpl.getById(id);
    }

    //Recuperation d'un tag en fonction du libelle
    @GET
    @Path("/tag/{libelleTag}")
    public Tag getByLibelle(@PathParam("libelleTag") String libelle){
        return tagDaoImpl.getByLibelle(libelle);
    }

    //Creation d'un Tag
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Tag createTag(Tag tag){
        return tagDaoImpl.create(tag);
    }

    //Modification d'un tag
    @PUT
    @Path("/update/{tagId}")
    @Consumes("application/json")
    public Tag updateTag(@PathParam("tagId") String id, Tag tag){
        return tagDaoImpl.update(id, tag);
    }

    //Suppression d'un tag
    @DELETE
    @Path("/delete/{tagId}")
    @Consumes("application/json")
    public void deleteTag(@PathParam("tagId") String id){
        tagDaoImpl.delete(id);
    }
}
