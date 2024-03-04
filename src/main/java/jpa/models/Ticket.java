package jpa.models;

import jakarta.persistence.*;
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
@Table(name = "tickets")
public class Ticket extends EntiteCommune {

    @Getter(onMethod_ = {
            @Column(name = "libelle")
    })
    private String libelle;

    /* Association affectation et ticket
    1 affectation peut être associée à 1,1 ticket
    1 ticket peut être avoir 1,* affectations */
    @Getter(onMethod_ = {
            @OneToMany(mappedBy = "ticket")
    })
    private Collection<Affectation> affectations = new ArrayList<>();

    /* Association utilisateur et ticket
    1 ticket peut être créé par 1,1 utilisateur
    1 utilisateur peut créer 1,* tickets */
    @Getter(onMethod_ = {
            @ManyToOne,
            @JoinColumn(name = "creer_par_id", nullable = false)
    })
    private Utilisateur creerPar;

    /* Association utilisateur et ticket
    1 ticket peut être clos par 1,1 utilisateur
    1 utilisateur peut clore 1,* tickets */
    @Getter(onMethod_ = {
            @ManyToOne,
            @JoinColumn(name = "ferme_par_id")
    })
    private Utilisateur fermePar;

    /* Association tag et ticket
    1 utilisateur peut être associé à 1,* tickets
    1 ticket peut être associé à 1,* tags */
    @Getter(onMethod_ = {
            @ManyToMany,
            @JoinTable(name = "ticket_by_tag", joinColumns = @JoinColumn(name = "ticket_id"),
                    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    })
    private Collection<Tag> tag = new ArrayList<>();
}