package jpa.services.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jpa.EntityManagerHelper;
import jpa.models.Tag;

import java.util.List;

public class TagDaoImpl extends GeneriqueDaoImpl<Tag, String> {

    private final EntityManager manager;

    public TagDaoImpl() {
        super();
        this.className = Tag.class;
        this.manager = EntityManagerHelper.getEntityManager();
    }


    //Recuperation d'un tag en fonction du libelle
    public Tag getByLibelle(String libelle) {
        if (libelle == null) {
            throw new IllegalArgumentException("libelle ne doit pas être nulle");
        }

        String queryStr = "SELECT t FROM " + className.getSimpleName() + " t WHERE LOWER(t.libelle) = LOWER(:libelle)";
        TypedQuery<Tag> query = manager.createQuery(queryStr, Tag.class);
        query.setParameter("libelle", libelle);

        List<Tag> result = query.getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

}
