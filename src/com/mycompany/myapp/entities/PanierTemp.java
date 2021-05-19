/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Objects;

/**
 *
 * @author Mega-PC
 */
public class PanierTemp {
    private int id;
    private int User_id;
    private Oeuvre oeuv;
    private int quantite;

    public PanierTemp() {
    }

    public PanierTemp(int id, int User_id, Oeuvre oeuv, int quantite) {
        this.id = id;
        this.User_id = User_id;
        this.oeuv = oeuv;
        this.quantite = quantite;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public Oeuvre getOeuv() {
        return oeuv;
    }

    public void setOeuv(Oeuvre oeuv) {
        this.oeuv = oeuv;
    }


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PanierTemp other = (PanierTemp) obj;
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.getOeuv().getOeuvrage_id(), other.getOeuv().getOeuvrage_id())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PanierTemp{" + "id=" + id + ", User_id=" + User_id + ", oeuv=" + oeuv + ", quantite=" + quantite + '}';
    }

   
  
    
}
