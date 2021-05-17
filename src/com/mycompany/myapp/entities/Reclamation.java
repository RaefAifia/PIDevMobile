/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Reclamation {
    private int user_id; 
    private String reclamation_nom,x;
    private int evenement_id,id;
    private int formation_id;
    private int oeuvrage_id;
    private String description, sujet;
    private int reclamation_id;
    private Date date;
    public Reclamation(){
        
    }
    public Reclamation(int reclamation_id,String reclamation_nom,int user_id, int evenement_id ,int formation_id,int oeuvrage_id,String sujet, String description, Date date){
        this.reclamation_id=reclamation_id;
        this.reclamation_nom=reclamation_nom;
        this.user_id=user_id;
        this.evenement_id=evenement_id;
        this.oeuvrage_id=oeuvrage_id;
        this.formation_id=formation_id;
        this.description=description;
        this.date=date;
        this.sujet=sujet;
    }
     public Reclamation(int reclamation_id,String reclamation_nom,int user_id, int evenement_id ,int formation_id,int oeuvrage_id, String description, Date date){
        this.reclamation_id=reclamation_id;
        this.reclamation_nom=reclamation_nom;
        this.user_id=user_id;
        this.evenement_id=evenement_id;
        this.oeuvrage_id=oeuvrage_id;
        this.formation_id=formation_id;
        this.description=description;
        this.date=date;
       
    }
    public Reclamation(int reclamation_id,int user_id, String reclamation_nom,String sujet,String description, Date date){
        this.user_id=user_id;
        this.reclamation_id=reclamation_id;
        this.reclamation_nom=reclamation_nom;
        this.sujet=sujet;
        this.description=description;
        this.date=date;
    }
    public Reclamation(int reclamation_id,int user_id, String reclamation_nom,String description){
        this.user_id=user_id;
        this.reclamation_id=reclamation_id;
        this.reclamation_nom=reclamation_nom;
        this.description=description;
        
    }
    public Reclamation(int user_id, String reclamation_nom,String description){
        this.user_id=user_id;
        
        this.reclamation_nom=reclamation_nom;
        
        this.description=description;
       
    }
    public Reclamation(int reclamation_id, int user_id, String reclamation_nom,String sujet,int id,String description,Date date){
        this.reclamation_id=reclamation_id;
        this.user_id=user_id;
        this.sujet=sujet;        
        this.reclamation_nom=reclamation_nom;
        this.description=description;
        this.date=date;
        this.id=id;
       
    }


    

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the evenement_id
     */
    public int getEvenement_id() {
        return evenement_id;
    }

    /**
     * @param evenement_id the evenement_id to set
     */
    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    /**
     * @return the formation_id
     */
    public int getFormation_id() {
        return formation_id;
    }

    /**
     * @param formation_id the formation_id to set
     */
    public void setFormation_id(int formation_id) {
        this.formation_id = formation_id;
    }

    /**
     * @return the oeuvrage_id
     */
    public int getOeuvrage_id() {
        return oeuvrage_id;
    }

    /**
     * @param oeuvrage_id the oeuvrage_id to set
     */
    public void setOeuvrage_id(int oeuvrage_id) {
        this.oeuvrage_id = oeuvrage_id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the reclamation_id
     */
    public int getReclamation_id() {
        return reclamation_id;
    }

    /**
     * @param reclamation_id the reclamation_id to set
     */
    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    /**
     * @return the reclamation_nom
     */
    public String getReclamation_nom() {
        return reclamation_nom;
    }

    /**
     * @param reclamation_nom the reclamation_nom to set
     */
    public void setReclamation_nom(String reclamation_nom) {
        this.reclamation_nom = reclamation_nom;
    }

    /**
     * @return the sujet
     */
    public String getSujet() {
        return sujet;
    }

    /**
     * @param sujet the sujet to set
     */
    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    /**
     * @return the x
     */
    public String getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(String x) {
        this.x = x;
    }
    
}
