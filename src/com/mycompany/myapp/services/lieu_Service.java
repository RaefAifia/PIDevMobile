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
import com.mycompany.myapp.entities.lieu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author hp
 */
public class lieu_Service {
    public ArrayList<lieu> Lieus;
    public static lieu_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public lieu_Service() {
        req = new ConnectionRequest();
    }

    public static lieu_Service getInstance() {
        if (instance == null) {
            instance = new lieu_Service();
        }
        return instance;
    }
    
      public ArrayList<lieu> parselieu(String jsonText) throws ParseException {
        try {
            Lieus = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                lieu l = new lieu();
                float lg = Float.parseFloat(obj.get("longitude").toString());
                l.setLg(lg);

               float lt = Float.parseFloat(obj.get("latitude").toString());
                l.setLt(lt);

               
                Lieus.add(l);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Lieus;
    }

    public ArrayList<lieu> findAll(int id) {
        String url = Statics.BASE_URL + "/lieu/evenement/LieuMobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    Lieus = parselieu(new String(req.getResponseData()));
                } catch (Exception ex) {
                   
                }
          
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Lieus;
    }
}
