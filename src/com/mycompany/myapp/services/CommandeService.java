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
import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.PanierTemp;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author TBug
 */
public class CommandeService {
    public ArrayList<Commande> cmd;
    public static CommandeService instance=null;
       public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }
        return instance;
    }
       
       public ArrayList<Commande> listecmd(String jsonText){
           
        try {
            cmd = new ArrayList<>();
            JSONParser j = new JSONParser();
          
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Commande c = new Commande();
                
                float pid = Float.parseFloat(obj.get("CommandeId").toString());
                c.setCommande_id((int)pid);
                c.setPrix_tot((Float.parseFloat(obj.get("prix").toString())));
                
                String DateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp")+10 , obj.get("obj").toString().lastIndexOf("}"));
                Date currentTime = new Date(Double.valueOf(DateConverter).longValue()*1000);
                
                SimpleDateFormat Formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = Formatter.format(currentTime);
                c.setDate(currentTime);
                
                cmd.add(c);
            } 
            
        } catch (IOException ex) {   
        }
        return cmd;
    }
       
       
        public void AjoutCmd(int uid){
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL+"commande/newcc/"+uid;
        
        con.setUrl(url);
        con.addResponseListener((e)->{
            String str = new String(con.getResponseData());
        });
 
            NetworkManager.getInstance().addToQueueAndWait(con);    
   }
}
