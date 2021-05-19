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
import com.mycompany.myapp.entities.Offre;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author pc
 */
public class OffreService {
     public ArrayList<Offre> offres;
     public static OffreService instance=null;
       public static OffreService getInstance() {
        if (instance == null) {
            instance = new OffreService();
        }
        return instance;
    }
      public ArrayList<Offre> mesOffres(String jsonText){
        try {
            offres=new ArrayList<>();
            JSONParser j = new JSONParser();
              
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Offre t = new Offre();
                float id = Float.parseFloat(obj.get("offreId").toString());
               t.setOffre_id((int)id);
//                t.setPrix((Float.parseFloat(obj.get("prix").toString())));
//                t.setIsvalid((int)(Float.parseFloat(obj.get("isvalid").toString())));
//                t.setQuantite((Float.parseFloat(obj.get("quantite").toString())));
                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                t.setDate(obj.get("date").toString().substring(0,10));
//             
              
            offres.add(t);
         
            }
            
        } catch (IOException ex) { }
        return offres;
    }

   public ArrayList<Offre> getmesOffres(int id){       
        ConnectionRequest con = new ConnectionRequest();
    String url = Statics.BASE_URL+"offre/mobile/client?user_id="+id;;
     con.setUrl(url);
           con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              offres = mesOffres(new String(con.getResponseData()));
                con.removeResponseListener(this);   }});
           
 NetworkManager.getInstance().addToQueueAndWait(con);
       return offres;
   }   
    
}
