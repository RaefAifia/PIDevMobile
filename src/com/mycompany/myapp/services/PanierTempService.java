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
import com.mycompany.myapp.entities.PanierTemp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author TBug
 */
public class PanierTempService {
    public ArrayList<PanierTemp> paniert;
    public ArrayList<Oeuvre> oeuvres;
    public static PanierTempService instance=null;
       public static PanierTempService getInstance() {
        if (instance == null) {
            instance = new PanierTempService();
        }
        return instance;
    }

       public ArrayList<PanierTemp> listePant(String jsonText){
           
        try {
            oeuvres = new ArrayList<>();
            paniert=new ArrayList<>();
            JSONParser j = new JSONParser();
          
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                Map<String,Object> leid= (Map<String,Object>) obj.get("oeuvrage");
                //Création des tâches et récupération de leurs données
                PanierTemp o = new PanierTemp();
                Oeuvre t = new Oeuvre();
                float id = Float.parseFloat(leid.get("oeuvrageId").toString());
                t.setOeuvrage_id((int)id);
                t.setPrix((Float.parseFloat(leid.get("prix").toString())));
                t.setQuantite((Float.parseFloat(leid.get("quantite").toString())));
                t.setNom(leid.get("nom").toString());
                t.setDescription(leid.get("description").toString());
                t.setImg(leid.get("image").toString());
                t.setDoamine(leid.get("domaine").toString());
                o.setOeuv(t);
                float pid = Float.parseFloat(obj.get("id").toString());
                o.setId((int)pid);
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                o.setQuantite((int)quantite);
             
                paniert.add(o);
            } 
            
        } catch (IOException ex) {   
        }
        return paniert;
    }
       
     
       
       

   public ArrayList<PanierTemp> getListPant(int uid){       
        ConnectionRequest con = new ConnectionRequest();
    String url = Statics.BASE_URL+"/panier/temp/pant/"+uid;
     con.setUrl(url);
           con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              paniert = listePant(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
  
            NetworkManager.getInstance().addToQueueAndWait(con);
       return paniert;
   }
   
   
   public void AjoutPanT(int id, int uid){
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL+"/panier/temp/newcc/"+id+"/"+uid;
        con.setUrl(url);
        con.addResponseListener((e)->{
            String str = new String(con.getResponseData());
        });
 
            NetworkManager.getInstance().addToQueueAndWait(con);    
   }
   
   public void DelPant(int id){
       ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"/panier/temp/delcc/"+id;
       con.setUrl(url);
       con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                con.removeResponseListener(this);
            }
       });
       NetworkManager.getInstance().addToQueueAndWait(con);
   }
   
   public void UpdPant(int id){
       ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"/panier/temp/updcc/"+id;
       con.setUrl(url);
       con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                con.removeResponseListener(this);
            }
   });
       NetworkManager.getInstance().addToQueueAndWait(con);

   }
}
