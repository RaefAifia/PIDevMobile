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
import com.mycompany.myapp.entities.Oeuvre;
import com.mycompany.myapp.entities.RatingO;
import java.io.IOException;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author pc
 */
public class RatingOService {
    int nb;
     public boolean resultOK;
   public static RatingOService instance=null;
       public static  RatingOService  getInstance() {
        if (instance == null) {
            instance = new  RatingOService ();
        }
        return instance;
    }  
    
    public void israte (Oeuvre ta, int id , RatingO test){
     ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"rating/oeuvre/israte?oeuvrage_id="+ta.getOeuvrage_id()+"&user_id="+id;
       System.out.println(url);
         con.setUrl(url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((et)->{
            String jsonText =  new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                try {
                    Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                int nb = (int)Float.parseFloat(obj.get("note").toString());
                    System.out.println("hhhhh"+nb);
                    test.setNote(nb);
                } catch (IOException ex) {
                }
        }
        );
        System.out.println("mllll"+nb);
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    
}
    
     public boolean ajoutnote(Oeuvre ta, int id,int v) {
        ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"rating/oeuvre/new?oeuvrage_id="+ta.getOeuvrage_id()+"&user_id="+id+"&note="+v;
       
       System.out.println(url);
         con.setUrl(url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; 
                con.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
}
       public boolean modifiernote(Oeuvre ta, int id,int v) {
        ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"rating/oeuvre/edit?oeuvrage_id="+ta.getOeuvrage_id()+"&user_id="+id+"&note="+v;
       
       System.out.println(url);
         con.setUrl(url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; 
                con.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
}
}
