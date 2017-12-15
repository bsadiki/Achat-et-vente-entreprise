package com.glsid.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by X-MART on 5/7/2017.
 */
@Entity
public class Societe implements Serializable {
    @Id
    private String code;
    private String nom;
    @OneToMany(mappedBy = "societe",fetch = FetchType.LAZY)
    private Collection<Ordre> ordre;

    public Societe() {
    }

    public Societe(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<Ordre> getOrdre() {
        return ordre;
    }

    public void setOrdre(Collection<Ordre> ordre) {
        this.ordre = ordre;
    }
}
