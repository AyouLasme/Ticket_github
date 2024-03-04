package jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateurs")
public class Utilisateur extends EntiteCommune {

    @Getter(onMethod_ = {
            @Column(name = "nom", length = 30)
    })
    private String nom;

    @Getter(onMethod_ = {
            @Column(name = "prenom", length = 30)
    })
    private String prenom;

    @Getter(onMethod_ = {
            @Column(name = "email", unique = true)
    })
    private String email;

    /* Association utilisateur et affectations
    1 utilisateur peut etre avoir à 1,* affectations
    1 affectation peut etre associée à 1,1 utilisateur */
    @Getter(onMethod_ = {
            @OneToMany(mappedBy = "utilisateur")
    })
    private Collection<Affectation> affectations = new ArrayList<>();

}