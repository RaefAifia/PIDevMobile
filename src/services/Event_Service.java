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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import utils.Statics;


/**
 *
 * @author hp
 */
public class Event_Service  {
        public ArrayList<Event> Events;
    public static Event_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Event_Service() {
        req = new ConnectionRequest();
    }

    public static Event_Service getInstance() {
        if (instance == null) {
            instance = new Event_Service();
        }
        return instance;
    }
            public ArrayList<Event> parseEvent(String jsonText) throws ParseException {
        try {
            Events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Event event = new Event();
                float id = Float.parseFloat(obj.get("evenement_id").toString());
                event.setId((int) id);

                float capacite = Float.parseFloat(obj.get("capacite").toString());
                event.setCapacite((int) capacite);

                event.setImage(obj.get("image").toString());
                event.setDomaine(obj.get("domaine").toString());
                            event.setDescription(obj.get("description").toString());
                event.setTitre(obj.get("titre").toString());
           
          float prix = Float.parseFloat(obj.get("prix").toString());
                event.setPrix(prix);
                
     
     
              
         event.setDate_evenement(obj.get("date_evenement").toString());
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                Events.add(event);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Events;
    }

    public ArrayList<Event> findAll() {
        String url = Statics.BASE_URL + "/evenement/MobileEvents";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Events = parseEvent(new String(req.getResponseData()));
                } catch (ParseException ex) {
                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Events;
    }
    
}
