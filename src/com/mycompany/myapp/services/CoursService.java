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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.entities.Formation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HELA
 */
public class CoursService {
//      private static Formation currentF; 
//       public static Formation getCurrentUser() {
//        return currentF;
//    }
//
//    public static void setCurrentUser(Formation currentF) {
//        CoursService.currentF = currentF;
//}
     public ArrayList<Cours> listCours (String json) throws ParseException {

        ArrayList<Cours> listCours = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> c  = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> cours = (List<Map<String, Object>>) c.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : cours) {
                
                //Création des tâches et récupération de leurs données
                Cours a = new Cours();

                float id = Float.parseFloat(obj.get("coursId").toString());
                a.setCours_id((int) id);
                
               //  a.setF(obj.get("formation").toString());
                 
                a.setTitre(obj.get("titre").toString());
               
                a.setFile(obj.get("image").toString());
                a.setDuree(obj.get("duree").toString());
                a.setDescription(obj.get("description").toString());
                a.setNiveau(obj.get("niveau").toString());
                 a.setMedia(obj.get("media").toString());
                
//                 float id = Float.parseFloat(obj.get("formationId").toString());
//                 a.setIsvalid(obj.get("isvalid").toString());
                 
                
                System.out.println(a);

                listCours.add(a);

            }

        } catch (IOException ex) {
        }

       // System.out.println(listFormation);
        return listCours;

    }
    
    
         ArrayList<Cours> listCours = new ArrayList<>();
    
    public ArrayList<Cours> getList(Formation f ) {
     //   FormationService.getCurrentF().getFormation_id()
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/cours/allCours/"+f.getFormation_id());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                   
              // listCours = new FormationService()).coursList(f);

      listCours= ( new CoursService()).listCours(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   ex.getMessage();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCours;
    }
//    
//    public ArrayList<Cours> getListPant(Formation f){       
//        ConnectionRequest con = new ConnectionRequest();
//    String url = "http://127.0.0.1:8000/cours/allCours/"+f.getFormation_id();
//     con.setUrl(url);
//           con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//              listCours = coursList(f));
//                con.removeResponseListener(this);
//            }
//        });
//    
    
    
}
