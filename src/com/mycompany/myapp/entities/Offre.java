/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author pc
 */
public class Offre {
  private int offre_id;
    private int User_id;
    private String nom;
    private String description;
    private int isvalid;
    private String date; 

    public Offre() {
    }

    public Offre(int offre_id, int User_id, String nom, String description, int isvalid, String date) {
        this.offre_id = offre_id;
        this.User_id = User_id;
        this.nom = nom;
        this.description = description;
        this.isvalid = isvalid;
        this.date = date;
    }

    public int getOffre_id() {
        return offre_id;
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

    public int getIsvalid() {
        return isvalid;
    }

    public String getDate() {
        return date;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
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

    public void setIsvalid(int isvalid) {
        this.isvalid = isvalid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Offre{" + "offre_id=" + offre_id + ", User_id=" + User_id + ", nom=" + nom + ", description=" + description + ", isvalid=" + isvalid + ", date=" + date + '}';
    }
    
}
