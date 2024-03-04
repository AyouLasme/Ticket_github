package jpa.services.impl;

import jpa.models.Affectation;
import jpa.models.Ticket;
import jpa.models.Utilisateur;

import java.util.List;
import java.util.stream.Collectors;

public class AffectationDaoImpl extends GeneriqueDaoImpl<Affectation, String> {
    public AffectationDaoImpl() {
        super();
        this.className = Affectation.class;
    }

    //Affectation d'un ticket à plusieurs utilisateurs en même temps
    public List<Affectation> create(Ticket ticket, List<Utilisateur> utilisateurs) {
        if (ticket == null) {
            throw new IllegalArgumentException("Erreur, ticket ne doit pas être ");
        }
        if (utilisateurs == null) {
            throw new IllegalArgumentException("utilisateur ne doit pas être null");
        }
        return utilisateurs.stream().map(utilisateur -> {
                    Affectation affectation = new Affectation(utilisateur, ticket);
                    return create(affectation);
                }).collect(Collectors.toList());
    }
}
