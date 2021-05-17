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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author asus
 */
public class ReclamationService {
    public ArrayList<Reclamation> Recs;
    
    public static ReclamationService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ReclamationService() {
         req = new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }

    public Reclamation addRec(String nom, String x,String y, int id , String description) {
        Reclamation v = new Reclamation();
        String url = Statics.BASE_URL + "/reclamation/newRec?nom=" + nom+"&description="+description+"&x="+x
                +"&concernant="+y+"&userId=" + id; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener((evt) -> {
        {
           JSONParser jsonp= new JSONParser();
         
            try {
                Map<String,Object> Use = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               
                float id1 = Float.parseFloat(Use.get("reclamationId").toString());
                v.setReclamation_id((int)id1);                
                
                
            } catch (IOException ex) {
                //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return v;

                }

    public ArrayList<Reclamation> parseRec(String jsonText){
        try {
            Recs =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String,Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)ReclamationListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("reclamationId").toString());
                t.setReclamation_id((int)id);
                t.setReclamation_nom(obj.get("reclamationNom").toString());
               
               
              
                Recs.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return Recs;
    }
    
    public ArrayList<Reclamation> getAllRecs(int id){
        String url = Statics.BASE_URL+"/reclamation/afficheAll/user/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Recs = parseRec(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Recs;
    }
    public Reclamation DetailRec(int id){
         Reclamation v = new Reclamation();
        String url = Statics.BASE_URL+"/reclamation/afficheAll/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
        {
           JSONParser jsonp= new JSONParser();
         
            try {
                Map<String,Object> Use = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
               
                float id1 = Float.parseFloat(Use.get("reclamationId").toString());
                v.setReclamation_id((int)id1);                
                
                v.setReclamation_nom(Use.get("reclamationNom").toString());
                v.setDescription(Use.get("description").toString());
                v.setX(Use.get("x").toString());
                v.setSujet(Use.get("concernant").toString());
                //v.setSujet((int)id0);
                
                
//                float id2 = Float.parseFloat(Use.get("userId").toString());
                
              //  v.setUser_id((int) id2);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = formatter.parse(Use.get("date").toString());
                v.setDate(date);
                
                float formation_id= Float.parseFloat(Use.get("formationId").toString());

                v.setFormation_id((int)formation_id);
                float evenement_id= Float.parseFloat(Use.get("evenementId").toString());

                v.setEvenement_id((int)evenement_id);
                float oeuvre_id= Float.parseFloat(Use.get("oeuvrageId").toString());

                v.setOeuvrage_id((int)oeuvre_id);
                
               
            } catch (IOException ex) {
                //Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                //Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
            }

            //System.out.println("data===" + str);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return v;
    }
    
    
}
