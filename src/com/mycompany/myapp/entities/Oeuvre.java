/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author pc
 */
public class Oeuvre {
    private int oeuvrage_id;
    private int User_id;
    private String nom;
    private String doamine ;
    private float prix;
    private float quantite;
    private String description;
    private String img;
    private int isvalid;
    private float rate; 
     
     public Oeuvre() {
    }

    public Oeuvre(int oeuvrage_id, int User_id, String nom, String doamine, float prix, float quantite, String description, String img, int isvalid) {
        this.oeuvrage_id = oeuvrage_id;
        this.User_id = User_id;
        this.nom = nom;
        this.doamine = doamine;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.img = img;
        this.isvalid = isvalid;
       
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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
        final Oeuvre other = (Oeuvre) obj;
        if (this.oeuvrage_id != other.oeuvrage_id) {
            return false;
        }
        return true;
    }

    
    public Oeuvre(int User_id, String nom, String doamine, float prix, float quantite, String description, String img) {
        this.User_id = User_id;
        this.nom = nom;
        this.doamine = doamine;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.img = img;
    }

    public void setIsvalid(int isvalid) {
        this.isvalid = isvalid;
    }

    public int getIsvalid() {
        return isvalid;
    }
    public int getOeuvrage_id() {
        return oeuvrage_id;
    }

    public String getDoamine() {
        return doamine;
    }

    public int getUser_id() {
        return User_id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setOeuvrage_id(int oeuvrage_id) {
        this.oeuvrage_id = oeuvrage_id;
    }

    public void setDoamine(String doamine) {
        this.doamine = doamine;
    }

    public void setUser_id(int User_id) {
        this.User_id = User_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Oeuvre{" + "oeuvrage_id=" + oeuvrage_id + ", User_id=" + User_id + ", nom=" + nom + ", doamine=" + doamine + ", prix=" + prix + ", quantite=" + quantite + ", description=" + description + ", img=" + img + ", isvalid=" + isvalid + '}';
    }

    
 
   


}
