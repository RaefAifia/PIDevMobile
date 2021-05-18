/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Oeuvre;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import utils.Statics;

/**
 *
 * @author pc
 */
public class OeuvrageService {
    public ArrayList<Oeuvre> oeuvres;
                            
    public static OeuvrageService instance=null;
       public static OeuvrageService getInstance() {
        if (instance == null) {
            instance = new OeuvrageService();
        }
        return instance;
    }

       public ArrayList<Oeuvre> listeOeuvres(String jsonText){
        try {
            oeuvres=new ArrayList<>();
            JSONParser j = new JSONParser();
              
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Oeuvre t = new Oeuvre();
                float id = Float.parseFloat(obj.get("oeuvrageId").toString());
                t.setOeuvrage_id((int)id);
                t.setPrix((Float.parseFloat(obj.get("prix").toString())));
                t.setQuantite((Float.parseFloat(obj.get("quantite").toString())));
                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                t.setImg(obj.get("image").toString());
                t.setDoamine(obj.get("domaine").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
               
                oeuvres.add(t);
                
            }
           
        } catch (IOException ex) {
            
        }
        return oeuvres;
    }

   public ArrayList<Oeuvre> getListOeuvres(){       
        ConnectionRequest con = new ConnectionRequest();
    String url = Statics.BASE_URL+"/oeuvrage/aa/listO";
     con.setUrl(url);
           con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              oeuvres = listeOeuvres(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
  
 NetworkManager.getInstance().addToQueueAndWait(con);
       return oeuvres;
   }
   
       public ArrayList<Oeuvre> mesOeuvres(String jsonText){
        try {
            oeuvres=new ArrayList<>();
            JSONParser j = new JSONParser();
              
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Oeuvre t = new Oeuvre();
                float id = Float.parseFloat(obj.get("oeuvrageId").toString());
                t.setOeuvrage_id((int)id);
                t.setPrix((Float.parseFloat(obj.get("prix").toString())));
                t.setIsvalid((int)(Float.parseFloat(obj.get("isvalid").toString())));
                t.setQuantite((Float.parseFloat(obj.get("quantite").toString())));
                t.setNom(obj.get("nom").toString());
                t.setDescription(obj.get("description").toString());
                t.setImg(obj.get("image").toString());
                t.setDoamine(obj.get("domaine").toString());
               
                oeuvres.add(t);
            }
           
        } catch (IOException ex) {
            
        }
        return oeuvres;
    }

   public ArrayList<Oeuvre> getmesOeuvres(int id){       
        ConnectionRequest con = new ConnectionRequest();
    String url = Statics.BASE_URL+"/oeuvrage/aa/listO/vendor?user_id="+id;
     con.setUrl(url);
           con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              oeuvres = mesOeuvres(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });
  
 NetworkManager.getInstance().addToQueueAndWait(con);
       return oeuvres;
   }



}
