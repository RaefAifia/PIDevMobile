/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import entities.Commande;
import entities.Oeuvre;
import entities.Panier;
import entities.PanierTemp;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author Mega-PC
 */
public class PanierService {
    public ArrayList<Panier> pan;
    public ArrayList<Commande> cmd;
    public ArrayList<Oeuvre> oeuvres;
    public static PanierService instance=null;
       public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;
    }
       public ArrayList<Panier> listePan(String jsonText){
           
        try {
            oeuvres = new ArrayList<>();
            cmd=new ArrayList<>();
            pan = new ArrayList<>();
            JSONParser j = new JSONParser();
          
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                Map<String,Object> leid= (Map<String,Object>) obj.get("oeuvrage");
                Map<String,Object> le= (Map<String,Object>) obj.get("commande");
                //Création des tâches et récupération de leurs données
                Commande c = new Commande();
                Oeuvre t = new Oeuvre();
                Panier p = new Panier();
                float id = Float.parseFloat(leid.get("oeuvrageId").toString());
                t.setOeuvrage_id((int)id);
                t.setPrix((Float.parseFloat(leid.get("prix").toString())));
                t.setQuantite((Float.parseFloat(leid.get("quantite").toString())));
                t.setNom(leid.get("nom").toString());
                t.setDescription(leid.get("description").toString());
                t.setImg(leid.get("image").toString());
                t.setDoamine(leid.get("domaine").toString());
                
                
                
                float pi = Float.parseFloat(le.get("CommandeId").toString());
                c.setCommande_id((int)pi);
                c.setPrix_tot((Float.parseFloat(le.get("prix").toString())));
                
                String DateConverter = le.get("date").toString().substring(le.get("date").toString().indexOf("timestamp")+10 , le.get("le").toString().lastIndexOf("}"));
                Date currentTime = new Date(Double.valueOf(DateConverter).longValue()*1000);
                
                SimpleDateFormat Formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = Formatter.format(currentTime);
                c.setDate(currentTime);
                
                float ps = Float.parseFloat(le.get("Id").toString());
                p.setId((int)ps);
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                p.setQuantite((int)quantite);
                p.setCmd(c);
                p.setOeuv(t);
                
                
                pan.add(p);
            } 
            
        } catch (IOException ex) {   
        }
        return pan;
    }
       
             public void AjoutPan(int uid){
        ConnectionRequest con = new ConnectionRequest();
         
        String url = Statics.BASE_URL+"/pan/newc/"+uid;
         
        con.setUrl(url);
        con.addResponseListener((e)->{
            String str = new String(con.getResponseData());
        });
 
            NetworkManager.getInstance().addToQueueAndWait(con);    
   }
}
