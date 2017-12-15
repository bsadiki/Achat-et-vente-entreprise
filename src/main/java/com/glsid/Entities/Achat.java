package com.glsid.Entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by X-MART on 5/7/2017.
 */
@Entity
@DiscriminatorValue("A")
public class Achat extends Ordre{
    public Achat() {
    }

    public Achat(Date date, int nombreActions, double prix, Societe societe) {
        super(date, nombreActions, prix, societe);
    }
}
