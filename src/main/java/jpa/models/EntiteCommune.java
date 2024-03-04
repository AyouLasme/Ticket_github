package jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class EntiteCommune implements Serializable {
    @Getter(onMethod_ = {
            @Id,
            @GeneratedValue(strategy = GenerationType.UUID),
            @UuidGenerator(style = UuidGenerator.Style.TIME),
            @Column(name = "id", updatable = false, nullable = false)
    })
    protected String id;

    // date de creation de tous mes objets
    @Getter(onMethod_ = {
            @Column(name = "date_creation", nullable = false, updatable = false),
            @Temporal(value = TemporalType.TIMESTAMP),
            @JsonProperty(value = "date_creation")
    })
    protected Date dateCreation;

    // considérer comme date de fermeture car, dernière date de mise à jour
    @Getter(onMethod_ = {
            @Column(name = "date_update", nullable = false),
            @Temporal(value = TemporalType.TIMESTAMP),
            @JsonProperty(value = "date_maj")
    })
    protected Date dateMiseAJour;


    @PrePersist
    public void prePersist() {
        dateCreation = new Date();
        dateMiseAJour = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        dateMiseAJour = new Date();
    }

}
