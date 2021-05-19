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
import com.mycompany.myapp.entities.Blog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author hp
 */
public class Blog_Service {
        public ArrayList<Blog> Blogs;
    public static Blog_Service instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
     public Blog_Service() {
        req = new ConnectionRequest();
    }

    public static Blog_Service getInstance() {
        if (instance == null) {
            instance = new Blog_Service();
        }
        return instance;
    }
    
      public ArrayList<Blog> parseBlog(String jsonText) throws ParseException {
        try {
            Blogs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                Blog blog = new Blog();
                float id = Float.parseFloat(obj.get("blogId").toString());
                blog.setId((int) id);

             

                blog.setTitre(obj.get("titre").toString());
                blog.setImage(obj.get("image").toString());
                       
          blog.setDescription(obj.get("description").toString());
               
                Blogs.add(blog);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Blogs;
    }

    public ArrayList<Blog> findAll() {
        String url = Statics.BASE_URL + "/blog/MobileBlogs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Blogs = parseBlog(new String(req.getResponseData()));
                } catch (ParseException ex) {
                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Blogs;
    }
}
