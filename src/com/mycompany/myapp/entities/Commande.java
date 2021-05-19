/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Mega-PC
 */
public class Commande {
    private int commande_id;
    private int user_id;
    private float prix_tot;
    private Date date;
    
    public Commande(int commande_id, int user_id, float prix_tot, Date date) {
        this.commande_id = commande_id;
        this.user_id = user_id;
        this.prix_tot = prix_tot;
        this.date = date;
    }

    public Commande() {
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getPrix_tot() {
        return prix_tot;
    }

    public void setPrix_tot(float prix_tot) {
        this.prix_tot = prix_tot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Commande{" + "commande_id=" + commande_id + ", user_id=" + user_id + ", prix_tot=" + prix_tot + ", date=" + date + '}';
    }
}
