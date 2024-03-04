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
@Table(name = "affectations")
public class Affectation extends EntiteCommune{
    @Getter(onMethod_ = {
            @ManyToOne,
            @JoinColumn(name = "utilisateur_id")
    })
    private Utilisateur utilisateur;

    @Getter(onMethod_ = {
            @ManyToOne,
            @JoinColumn(name = "ticket_id")
    })
    private Ticket ticket;
}
