/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HELA
 */
public class Cours {
    private int cours_id;
    private String titre;
    private String niveau ;
      private String description;
      private String duree;
      private String file;
     private int formation_id;
     public  Formation f = new Formation();
     
    public Cours() {
        
    }

    public Cours( String titre, String niveau, String description, String duree, String file, int formation_id) {
        
        this.titre = titre;
        this.niveau = niveau;
        this.description = description;
        this.duree = duree;
        this.file = file;
        this.formation_id = formation_id;
    }

    
    public int getCours_id() {
        return cours_id;
    }

    public String getTitre() {
        return titre;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getDescription() {
        return description;
    }

    public String getDuree() {
        return duree;
    }

    public String getFile() {
        return file;
    }

    public Formation getF() {
        return f;
    }

    public void setCours_id(int cours_id) {
        this.cours_id = cours_id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setF(Formation f) {
        this.f = f;
    }

    public int getFormation_id() {
        return formation_id;
    }

    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }

    
}
