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
public class FavorisOService {
      public boolean resultOK;
      int nb;
      public ArrayList<Oeuvre> oeuvres;
    public static FavorisOService instance=null;
       public static FavorisOService getInstance() {
        if (instance == null) {
            instance = new FavorisOService();
        }
        return instance;
    }

       public ArrayList<Oeuvre> listeFOeuvres(String jsonText){
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

      
   public ArrayList<Oeuvre> getListFOeuvres(int id){       
        ConnectionRequest con = new ConnectionRequest();
    String url = Statics.BASE_URL+"/favoris/o/mobile/index?user_id="+id;
     con.setUrl(url);
           con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              oeuvres = listeFOeuvres(new String(con.getResponseData()));
               // con.removeResponseListener(this);
            }
        });
 NetworkManager.getInstance().addToQueueAndWait(con);
       return oeuvres;
   }

  public boolean ajoutFAv(Oeuvre ta, int id) {
        ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"/favoris/o/aa/listO/favoris/new?oeuvrage_id="+ta.getOeuvrage_id()+"&user_id="+id;
       
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
  public boolean suppFAv(Oeuvre ta, int id) {
        ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"/favoris/o/aa/listO/favoris/delete?oeuvrage_id="+ta.getOeuvrage_id()+"&user_id="+id;
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
  
public void isfav (Oeuvre ta, int id , Oeuvre test){
   
     ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"/favoris/o/aa/listO/favoris?oeuvrage_id="+ta.getOeuvrage_id()+"&user_id="+id;
       System.out.println(url);
         con.setUrl(url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((et)->{
            String jsonText =  new String(con.getResponseData());
                    JSONParser j = new JSONParser();
                try {
                    
                    Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                int nb = (int)Float.parseFloat(obj.get("favorisOId").toString());
                    System.out.println("hhhhh"+nb);
                    test.setOeuvrage_id(nb);
                } catch (IOException ex) {
                }
        }
        );
        System.out.println("mllll"+nb);
        NetworkManager.getInstance().addToQueueAndWait(con);
        
}
}
    