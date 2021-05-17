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
import com.mycompany.myapp.entities.Relations;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author asus
 */
public class RelationService {
    public ArrayList<User> Users;
    
    public static RelationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public RelationService() {
         req = new ConnectionRequest();
    }

    public static RelationService getInstance() {
        if (instance == null) {
            instance = new RelationService();
        }
        return instance;
    }

    public Boolean addR(int follower, int followee) {
       
        String url = Statics.BASE_URL +"/relations/newRmobile?follower_id="+follower+"&followee_id="+followee;
         req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
}
     public Relations checkR(int follower, int followee) {
       Relations r = new Relations();
        String url = Statics.BASE_URL +"/relations/checkR?follower_id="+follower+"&followee_id="+followee;
         req.setUrl(url);// Insertion de l'URL de notre demande de connexion
         System.out.println("url " +url);
         req.addResponseListener((evt) -> {
        
        {
           JSONParser jsonp= new JSONParser();
         
            try {
                Map<String,Object> Use = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               
                float relation_id = Float.parseFloat(Use.get("relationId").toString());
                System.out.println(relation_id);
               
                r.setRelation_id((int)relation_id);
               
            } catch (IOException ex) {
                //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }

            //System.out.println("data===" + str);
        }
      
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return r;
}
    public Boolean deleteR(int follower, int followee) {
        
        String url = Statics.BASE_URL +"/relations/deleteRmobile?follower_id="+follower+"&followee_id="+followee;
         req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
}
}
