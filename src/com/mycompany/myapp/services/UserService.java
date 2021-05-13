/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class UserService {
     private static User currentuser;    
      public static User getCurrentUser() {
        return currentuser;
    }

    public static void setCurrentUser(User currentuser) {
        UserService.currentuser = currentuser;
}
    
public ArrayList<User> Users;
    
    public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean addUser(User u) {
        String url = Statics.BASE_URL + "/user/addUser?username=" + u.getUsername()+"&password="+u.getPassword()+"&nom="+u.getNom()
                +"&prenom="+u.getPrenom()+"&email="+u.getEmail()+"&adresse="+u.getAdresse()+"&numTel="+u.getNum_tel()+"&bio="+u.getBio(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service User, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean login(User u) {
        String url = Statics.BASE_URL + "/user/login?email="+ u.getEmail()+"&password="+u.getPassword();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener((evt) -> {
         {
            JSONParser jsonp= new JSONParser();
         
            try {
                Map<String,Object> Use = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                float id = Float.parseFloat(Use.get("userId").toString());
                u.setUser_id((int)id);
                System.out.println((int)id);
            } catch (IOException ex) {
                //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<User> parseUsers(String jsonText){
        try {
            Users=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UsersListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User t = new User();
                float id = Float.parseFloat(obj.get("userId").toString());
                t.setUser_id((int)id);
                t.setUsername(UsersListJson.get("username").toString());
               
              
                Users.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Users;
    }
    
    public ArrayList<User> getAllUsers(){
        String url = Statics.BASE_URL+"/user/afficheAll";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
    public User DetailUser(int id){
         User u = new User();
        String url = Statics.BASE_URL+"/user/afficheAll/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
        {
           JSONParser jsonp= new JSONParser();
         
            try {
                Map<String,Object> Use = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               
                float id1 = Float.parseFloat(Use.get("userId").toString());
                u.setUser_id((int)id1);                
                u.setAdresse(Use.get("adresse").toString());
                u.setNom(Use.get("nom").toString());
                u.setPrenom(Use.get("prenom").toString());
                u.setUsername(Use.get("username").toString());
                u.setEmail(Use.get("email").toString());
                u.setPassword(Use.get("password").toString());
                u.setBio(Use.get("bio").toString());
                u.setImage(Use.get("image").toString());
                String x=(String) Use.get("mailconfirme");
                if(x.equals("false")){
                u.setMailconfirme(0);
                }else{ u.setMailconfirme(1);}
                String y=(String) Use.get("numconfirme");
                if(y.equals("false")){
                u.setNumconfirme(0);
                }else{ u.setNumconfirme(1);}
                
               
            } catch (IOException ex) {
                //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

            //System.out.println("data===" + str);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return u;
    }
    public User confirmMail(int id){
         User u = new User();
        String url = Statics.BASE_URL+"/user/confirmEmail/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
        {
           JSONParser jsonp= new JSONParser();
         
            try {
                Map<String,Object> Use = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               
                float id1 = Float.parseFloat(Use.get("userId").toString());
                u.setUser_id((int)id1);                u.setAdresse(Use.get("adresse").toString());
                u.setNom(Use.get("nom").toString());
                u.setPrenom(Use.get("prenom").toString());
                u.setUsername(Use.get("username").toString());
                u.setEmail(Use.get("email").toString());
                u.setPassword(Use.get("password").toString());
                u.setBio(Use.get("bio").toString());
                u.setImage(Use.get("image").toString());
                String x=(String) Use.get("mailconfirme");
                if(x.equals("false")){
                u.setMailconfirme(0);
                }else{ u.setMailconfirme(1);}
                String y=(String) Use.get("numconfirme");
                if(y.equals("false")){
                u.setNumconfirme(0);
                }else{ u.setNumconfirme(1);}
               
            } catch (IOException ex) {
                //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

            //System.out.println("data===" + str);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return u;
    }
    public User confirmNum(int id){
         User u = new User();
        String url = Statics.BASE_URL+"/user/confirmNum/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
        {
           JSONParser jsonp= new JSONParser();
         
            try {
                Map<String,Object> Use = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               
                float id1 = Float.parseFloat(Use.get("userId").toString());
                u.setUser_id((int)id1);                u.setAdresse(Use.get("adresse").toString());
                u.setNom(Use.get("nom").toString());
                u.setPrenom(Use.get("prenom").toString());
                u.setUsername(Use.get("username").toString());
                u.setEmail(Use.get("email").toString());
                u.setPassword(Use.get("password").toString());
                u.setBio(Use.get("bio").toString());
                u.setImage(Use.get("image").toString());
                String x=(String) Use.get("mailconfirme");
                if(x.equals("false")){
                u.setMailconfirme(0);
                }else{ u.setMailconfirme(1);}
                String y=(String) Use.get("numconfirme");
                if(y.equals("false")){
                u.setNumconfirme(0);
                }else{ u.setNumconfirme(1);}
               
            } catch (IOException ex) {
                //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

            //System.out.println("data===" + str);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return u;
    }
}
