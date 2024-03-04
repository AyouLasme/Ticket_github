package jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
@Table(name = "tags")
public class Tag extends EntiteCommune {

    @Getter(onMethod_ = {
            @Column(name = "libelle", length = 200, unique = true)
    })
    private String libelle;

    /*
        Association tag et ticket
        1 tag peut être associé à 1,* tickets
        1 ticket peut être associé à 1,* tags
    */
    @Getter(onMethod_ = {
            @ManyToMany(mappedBy = "tag")
    })
    private Collection<Ticket> tickets = new ArrayList<>();
}