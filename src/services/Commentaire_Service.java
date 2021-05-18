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
import entities.Commentaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author hp
 */
public class Commentaire_Service  {
        public ArrayList<Commentaire> Commentaires;
    public static Commentaire_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Commentaire_Service() {
        req = new ConnectionRequest();
    }

    public static Commentaire_Service getInstance() {
        if (instance == null) {
            instance = new Commentaire_Service();
        }
        return instance;
    }
            public ArrayList<Commentaire> parseCommentaire(String jsonText) throws ParseException {
        try {
            Commentaires = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Commentaire commentaire = new Commentaire();
                float id = Float.parseFloat(obj.get("id").toString());
                commentaire.setId((int) id);

               commentaire.setText(obj.get("text").toString());

                commentaire.setDate(obj.get("date").toString());
     
                // questionnaire q =new questionnaire();
                // q.setDescription_cat_qst((String) map.get("description_cat_qst"));
             
                Commentaires.add(commentaire);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Commentaires;
    }

    public ArrayList<Commentaire> findAll(int id) {
        String url = Statics.BASE_URL + "/blog/MobileComments/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Commentaires = parseCommentaire(new String(req.getResponseData()));
                } catch (ParseException ex) {
                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Commentaires;
    }
       public boolean addCommentaire(Commentaire c,int id_blog) {
     
        String url = Statics.BASE_URL + "/blog/MobileComments_add/"+id_blog+"/"+c.getText();
try{
           req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Commentaires = parseCommentaire(new String(req.getResponseData()));
                } catch (ParseException ex) {
                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
}
catch( Exception e)
{
    System.out.println(e.getMessage());
}
      

        return resultOK;
    }
    
}
