package jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages")
public class Message extends EntiteCommune {

    @Getter(onMethod_ = {
            @Column(name = "contenu", length = 200)
    })
    private String contenu;

    /* Association user et ticket
    1 message peut associer à 1,1 ticket
    1 ticket peut être associé à 0,* messages */
    @Getter(onMethod_ = {
            @ManyToOne(optional = false),
            @JoinColumn(name = "ticket_id")
    })
    private Ticket ticket;

    /* Association user et ticket
    1 user peut envoyer 0,* message
    1 message peut être envoyé par 1,1 user */
    @Getter(onMethod_ = {
            @ManyToOne(optional = false),
            @JoinColumn(name = "utilisateur_id")
    })
    private Utilisateur utilisateur;

}
