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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import entities.Formation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author HELA
 */
public class FormationService {
    
    
    private Boolean responseResult;
      private ConnectionRequest req;

    public ArrayList<Formation> listFormation (String json) throws ParseException {

        ArrayList<Formation> listFormation = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> formation  = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> formations = (List<Map<String, Object>>) formation.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : formations) {
                //Création des tâches et récupération de leurs données
                Formation a = new Formation();

                float id = Float.parseFloat(obj.get("formationId").toString());
                a.setFormation_id((int) id);
                a.setTitre(obj.get("titre").toString());
                a.setDomaine(obj.get("domaine").toString());

                 float prix = Float.parseFloat(obj.get("prix").toString());
                a.setPrix((int)prix);
                a.setImage(obj.get("image").toString());
                a.setDuree(obj.get("duree").toString());
                a.setDescription(obj.get("description").toString());
                a.setDate(obj.get("date").toString());
                a.setNiveau(obj.get("niveau").toString());
                a.setLieu(obj.get("lieu").toString());
                a.setLangue(obj.get("langue").toString());
//                 float id = Float.parseFloat(obj.get("formationId").toString());
//                 a.setIsvalid(obj.get("isvalid").toString());
                 
                
                System.out.println(a);

                listFormation.add(a);

            }

        } catch (IOException ex) {
        }

        System.out.println(listFormation);
        return listFormation;

    }
     ArrayList<Formation> listFormation = new ArrayList<>();
    
    public ArrayList<Formation> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/formation/allFormation");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                   
                    listFormation = (new FormationService()).listFormation(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listFormation;
    }
    
    
    
    
    
    public Formation  FormationDetails(int id , Formation a){
    
     req.setUrl("http://127.0.0.1:8000/formation/show/"+a.getFormation_id());
     
     String str = new String (req.getResponseData());
     
     
     req.addResponseListener((evt)-> {
     
     JSONParser  jp = new JSONParser();
     try {
     
           Map<String, Object> obj  = jp.parseJSON(new CharArrayReader(new String (str).toCharArray()));
           
           
//            float id = Float.parseFloat(obj.get("formationId").toString());
//                a.setFormation_id((int) id);
                a.setTitre(obj.get("titre").toString());
                a.setDomaine(obj.get("domaine").toString());
                 float prix = Float.parseFloat(obj.get("prix").toString());
                a.setPrix((int)prix);
                a.setImage(obj.get("image").toString());
                a.setDuree(obj.get("duree").toString());
                a.setDescription(obj.get("description").toString());
                a.setDate(obj.get("date").toString());
                a.setNiveau(obj.get("niveau").toString());
                a.setLieu(obj.get("lieu").toString());
                a.setLangue(obj.get("langue").toString());
     
     }
     catch(IOException iEx){System.out.println("error: "+iEx.getMessage());}
     
         System.out.println("data: "+str);
             
     });
     
     NetworkManager.getInstance().addToQueueAndWait(req);
     return a;
    }
    
    
    
    
    
    
    public boolean addFormation(Formation f) {
        String url = Statics.BASE_URL + "/addFormation?domaine" + f.getDomaine()+ "date" + f.getDate()+"duree"+f.getDuree()+"lieu" +f.getLieu()+"prix"+f.getPrix()+"niveau"+ f.getNiveau()+"langue" +f.getLangue()+"description"+f.getDescription()+"image"+f.getImage()+"titre"+f.getTitre(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return responseResult;
    } 
     public boolean supprimer(Formation f)
    {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/formation/delete/"+f.getFormation_id());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this); //Supprimer  actionListener
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return responseResult;
    }
    
}
