package jpa.services.impl;

import jpa.models.Utilisateur;

public class UtilisateurDaoImpl extends GeneriqueDaoImpl<Utilisateur, String> {
    public UtilisateurDaoImpl() {
        super();
        this.className = Utilisateur.class;
    }
}
