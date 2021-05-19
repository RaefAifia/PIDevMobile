/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.db.Database;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;

import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.entities.Inscription;
import com.mycompany.myapp.entities.User;
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
    ArrayList<Cours> list_c ;
     private static Formation currentF; 
       public static Formation getCurrentF() {
        return currentF;
    }

    public static void setCurrentF(Formation currentF) {
        FormationService.currentF = currentF;}
    private Boolean responseResult;
      private ConnectionRequest req;
      
       public static FormationService instance = null;
       
       public static FormationService getInstance() {
        if (instance == null) {
            instance = new FormationService();
        }
        return instance;
    }

   

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
                
//                 float idu = Float.parseFloat(obj.get("userId").toString());
//                a.setUser_id((int) idu);
                
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
//               
//               float nbi = Float.parseFloat(obj.get("nb").toString());
//                a.setNbInsc((int) nbi);
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
    
    
    
    
    
    public Formation  FormationDetails( Formation a){
    
     req.setUrl("http://127.0.0.1:8000/formation/show/"+a.getFormation_id());
     
     String str = new String (req.getResponseData());
     req.addResponseListener((evt)-> {
     
     JSONParser  jp = new JSONParser();
     try {
     
           Map<String, Object> obj  = jp.parseJSON(new CharArrayReader(new String (str).toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)obj.get("root");
            
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
                 
 for (Map<String,Object> obje : list){
     Map<String,Object> leid= (Map<String,Object>) obj.get("formation");
   Cours  c= new Cours();
    float idc = Float.parseFloat(obje.get("coursId").toString());
                c.setCours_id((int) idc);
                c.setF(a);
               //  c.setF(obj.get("formation").toString());
                 
                c.setTitre(obje.get("titre").toString());
               
                c.setFile(obje.get("image").toString());
                c.setDuree(obje.get("duree").toString());
                c.setDescription(obje.get("description").toString());
                c.setNiveau(obje.get("niveau").toString());
 
 }
             
             
     }
     catch(IOException iEx){System.out.println("error: "+iEx.getMessage());}
     
         System.out.println("data: "+str);
             
     });
     
     NetworkManager.getInstance().addToQueueAndWait(req);
     return a;
    }
    
    
      public ArrayList  coursList( Formation a){
    
     req.setUrl("http://127.0.0.1:8000/formation/show/"+a.getFormation_id());
     
     String str = new String (req.getResponseData());
     req.addResponseListener((evt)-> {
     
     JSONParser  jp = new JSONParser();
     try {
         list_c =new ArrayList<>();
     
           Map<String, Object> obj  = jp.parseJSON(new CharArrayReader(new String (str).toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)obj.get("root");
            
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
                 
 for (Map<String,Object> obje : list){
     Map<String,Object> leid= (Map<String,Object>) obj.get("formation");
     
     CoursService cs = new CoursService();
  // list_c=  cs.getList(a);
   Cours  c= new Cours();
    float idc = Float.parseFloat(obje.get("coursId").toString());
                c.setCours_id((int) idc);
                c.setF(a);
               //  c.setF(obj.get("formation").toString());
                 
                c.setTitre(obje.get("titre").toString());
               
                c.setFile(obje.get("image").toString());
                c.setDuree(obje.get("duree").toString());
                c.setDescription(obje.get("description").toString());
                c.setNiveau(obje.get("niveau").toString());
                list_c.add(c);
                System.out.println(c.getCours_id());
  }
              
     }
     catch(IOException iEx){System.out.println("error: "+iEx.getMessage());}
     
         System.out.println("data: "+str);
             
     });
     
     NetworkManager.getInstance().addToQueueAndWait(req);
     return list_c;
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
     
       public boolean inscription( Formation f, int id) {
           ConnectionRequest con = new ConnectionRequest();
           String url = Statics.BASE_URL+"inscription/newInscri?"+"user="+id+"&formation="+f.getFormation_id();
           

        
        con.setUrl(url); 
        System.out.println(url);
        //  System.out.println(u.getUser_id());
     
       con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                    responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                    con.removeResponseListener(this); //Supprimer  actionListener

               System.out.println(con.getResponseData());
            }
        });
       NetworkManager.getInstance().addToQueueAndWait(con);
      return responseResult;
    } 
    public boolean AjouterRating (int v, Formation f,int id ) throws IOException{

        ConnectionRequest con = new ConnectionRequest();
        String url =  Statics.BASE_URL+ "formation/rate?value="+v+"&user="+id+"&formation="+f.getFormation_id();
       // con.setUrl("http://127.0.0.1:8000/inscription/rate/"+f.getFormation_id()+"?value="+v+"&userId="+id);
        System.out.println(url);
       con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

               responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return responseResult;
    }
    
    
       public boolean ModifRating (int v, Formation f,int id) throws IOException{

        ConnectionRequest con = new ConnectionRequest();
        String url =  Statics.BASE_URL+ "formation/rate/modif?value="+v+"&user="+id+"&formation="+f.getFormation_id();
    
        System.out.println(url);
       con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

               responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return responseResult;
    }
       public boolean isRated (int v, Formation f) throws IOException{

        ConnectionRequest con = new ConnectionRequest();
        String url =  Statics.BASE_URL+ "formation/rated?user="+1+"&formation="+f.getFormation_id();
    

        System.out.println(url);
       con.setUrl(url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

               responseResult = con.getResponseCode() == 200; //Code HTTP 200 OK
                con.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
        return responseResult;
    } 
    
    
            public ArrayList<Formation> getSearchR(String nom){
        String url = "http://127.0.0.1:8000/formation/recherche/" + nom;
        req.setUrl(url);
       // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //   users = parseUser(new String(req.getResponseData()));
                    listFormation = listFormation(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println( ex.getMessage());                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listFormation;
    }
}
