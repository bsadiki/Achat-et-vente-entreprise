package com.glsid.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by X-MART on 5/7/2017.
 */
@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type_Ordre", discriminatorType = DiscriminatorType.STRING, length = 1)
public abstract class Ordre implements Serializable{
    @Id @GeneratedValue
    protected Long identifiant;
    protected Date date;
    protected int NombreActions;
    protected double prix;
    @ManyToOne
    @JoinColumn(name="code_societe")
    protected Societe societe;

    public Ordre() {
    }

    public Ordre(Date date, int nombreActions, double prix, Societe societe) {
        this.date = date;
        NombreActions = nombreActions;
        this.prix = prix;
        this.societe = societe;
    }

    public Long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNombreActions() {
        return NombreActions;
    }

    public void setNombreActions(int nombreActions) {
        NombreActions = nombreActions;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
}
