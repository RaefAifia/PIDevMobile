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
import com.mycompany.myapp.entities.User_reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import utils.Statics;

/**
 *
 * @author hp
 */
public class Reservation_Service {
        public ArrayList<User_reservation> User_reservations;
    public static Reservation_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Reservation_Service() {
        req = new ConnectionRequest();
    }

    public static Reservation_Service getInstance() {
        if (instance == null) {
            instance = new Reservation_Service();
        }
        return instance;
    }
    
      public ArrayList<User_reservation> parseUser_reservation(String jsonText) throws ParseException {
        try {
            User_reservations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");
      
            for (Map<String, Object> obj : list) {
                User_reservation user_reservation = new User_reservation();
                
                Map<String, Object> map1 = ((Map<String, Object>) obj.get("user"));
                for (Entry<String, Object> entry : map1.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    if(key.equals("userId"))
                    {
                       float userId = Float.parseFloat(value.toString());
                user_reservation.setId_user_reserve((int) userId);  
                    }
              
                    
                }
   

                User_reservations.add(user_reservation);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return User_reservations;
    }

    public ArrayList<User_reservation> findreserve(int id_event) {
        String url = Statics.BASE_URL + "/evenement/MobileReservers/"+id_event;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    User_reservations = parseUser_reservation(new String(req.getResponseData()));
                } catch (ParseException ex) {
                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return User_reservations;
    }    
    
    public void deleteeserve(int id_event) {
        String url = Statics.BASE_URL + "/evenement/MobileReserversdelete/"+id_event;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
           
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }  
        public void addeserve(int id_event, int id_u) {
        String url = Statics.BASE_URL + "/evenement/reserver_Mobile/"+id_event+"/"+id_u;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
           
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }  
    
}
