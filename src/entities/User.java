/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class User {
    private int user_id, num_tel;
    private String nom, prenom , adresse, email,username,role, password, image, bio, validité;
    
   
    
    public User(int user_id) {
        this.user_id = user_id;
    }
    
    public User(){
        
    }
    
    public User(int user_id, int num_tel, String nom , String prenom ,String role,String adresse ,String username, String password,String email,String image, String bio){
    this.user_id=user_id;
    this.num_tel= num_tel;
    this.nom=nom;
    this.prenom=prenom;
    this.username=username;
    this.password=password;
    this.email=email;
    this.adresse=adresse;
    this.role=role;
    this.image=image;
    this.bio=bio;
    }
    public User( int num_tel, String nom , String prenom ,String adresse ,String username, String password,String email){
    
    this.num_tel= num_tel;
    this.nom=nom;
    this.prenom=prenom;
    this.username=username;
    this.password=password;
    this.email=email;
    this.adresse=adresse;
    this.role=role;
    this.image=image;
    
    
}
    public User(String username ,String role, String email, String password){
        
        
        this.username=username;
        this.password=password;
        this.email=email;
    }
    public User(int user_id,String username ,String nom,String prenom, String email){
        
        this.user_id=user_id;
        this.prenom=prenom;
        this.nom=nom;
        this.username=username;
        
        this.email=email;
    }
    public User(int user_id,String username ,String nom,String prenom, String email,String image){
        
        this.user_id=user_id;
        this.prenom=prenom;
        this.nom=nom;
        this.username=username;
        this.image=image;
        this.email=email;
    }

    public User(String email, String password) {
            this.email=email;
            this.password=password; 
            
    }
    public User( String password) {
            
            this.password=password; 
    }
    public User(String nom, String prenom, String username,String adresse, int num_tel,  String bio,String image) {
        this.prenom=prenom;
        this.username=username;
        this.num_tel= num_tel;
        this.nom=nom;
        
        this.adresse=adresse;
        this.bio=bio;
        this.image=image;
    }

    public User(String nom, String prenom, String username, String adresse, int num_tel, String email ) {
this.prenom=prenom;
        this.username=username;
        this.num_tel= num_tel;
        this.nom=nom;
         this.prenom=prenom;
        this.email=email;
        this.adresse=adresse;    }
    

    public User(String nom, String prenom, String username,String password, String adresse, int num_tel, String email,String image) {
this.prenom=prenom;
        this.username=username;
        this.num_tel= num_tel;
        this.nom=nom;
         this.prenom=prenom;
        this.email=email;
        this.adresse=adresse;
        this.password=password;
        this.image=image;    }

    public User(int user_id, String userName, String email) {
        this.username=username;
        this.email=email;
        this.user_id=user_id;
    }

    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
    public int getNum_tel() {
        return num_tel;
    }

    
    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    
    public String getNom() {
        return nom;
    }

    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    public String getPrenom() {
        return prenom;
    }

    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    public String getAdresse() {
        return adresse;
    }

    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

   
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return username;
    }

    
    public void setUsername(String username) {
        this.username = username;
    }

    
    public String getRole() {
        return role;
    }

    
    public void setRole(String role) {
        this.role = role;
    }

    
    public String getPassword() {
        return password;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString(){
       return "User{ " +user_id + ", name " +nom+", last name " +prenom+ ", email " + email;  
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
        final User other = (User) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return the validité
     */
    public String getValidité() {
        return validité;
    }

    /**
     * @param validité the validité to set
     */
    public void setValidité(String validité) {
        this.validité = validité;
    }

    
    
}
