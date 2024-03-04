package jpa.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jpa.EntityManagerHelper;
import jpa.models.Tag;
import jpa.models.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketDaoImpl extends GeneriqueDaoImpl<Ticket, String> {

    @PersistenceContext
    private final EntityManager manager;
    private final TagDaoImpl tagDaoImpl;


    public TicketDaoImpl() {
        super();
        this.className = Ticket.class;
        this.manager = EntityManagerHelper.getEntityManager();
        this.tagDaoImpl = new TagDaoImpl();
    }


    //Creation de ticket avec tag
    public Ticket create(Ticket ticket, List<String> tagLibelleList) {
        if (ticket == null) {
            throw new IllegalArgumentException("Erreur ticket ne doit pas être nulle");
        }
        if (tagLibelleList == null) {
            throw new IllegalArgumentException("Erreur, tagLibelle n'est pas nulle");
        }
        // conversion des listes des libellés de tags en libelle de tag
        List<Tag> tagList = tagLibelleList.stream().map(tagDaoImpl::getByLibelle).collect(Collectors.toList());
        ticket.setTag(tagList);

        EntityTransaction t = manager.getTransaction();
        t.begin();
        Tag tag = manager.find(Tag.class, tagLibelleList);
        ticket.getTag().add(tag);
        manager.persist(ticket);
        manager.merge(ticket);
        t.commit();
        return ticket;
    }


    public List<Ticket> getTicketByTagLibelle(String tagLibelle) {
        List<Ticket> tickets = manager.createQuery("SELECT t FROM Ticket t", Ticket.class).getResultList();
        return tickets
                .stream()
                .filter(ticket -> ticket.getTag().stream().anyMatch(tag -> tag.getLibelle().equals(tagLibelle)))
                .collect(Collectors.toList());
    }
}
