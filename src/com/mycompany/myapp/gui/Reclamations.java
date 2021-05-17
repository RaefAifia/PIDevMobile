/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ReclamationService;
import com.mycompany.myapp.services.UserService;
//import java.nio.file.attribute.UserPrincipalLookupService;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class Reclamations extends BaseForm {

  
    
 Form f;

    Container cn1;
    ImageViewer iv;
    URLImage img;
    Label lblnomeve;
    Label nomeve;
    Label promo;
     EncodedImage enc;
     String urlimg = "http://localhost/pi_symfony/public/images/profil/";
    public Reclamations(Form previous, Resources res) {  
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        super.addSideMenu(res);

        
    // super.addSideMenu(res);
      Image img1 = res.getImage("11.jpg");
        if(img1.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img1 = img1.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img1);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("", res.getImage(""), "BottomPad");
        Label twitter = new Label("", res.getImage(""), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl
                
        ));
       
        
        
        // special case for rotation
//        addOrientationListener(e -> {
//          updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
//        });
        ReclamationService ME = new ReclamationService();
        for(Reclamation eee : ME.getAllRecs(UserService.getCurrentUser().getUser_id())) {
           
         String urli="logo.png";
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(20, 90),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, urli,urli,URLImage.RESIZE_SCALE);
              addButton(previous, res , eee, eee.getDescription(), eee.getReclamation_nom(), urlImage);
              ScaleImageLabel image= new ScaleImageLabel(urlImage);
              Container containerImage = new Container();
              image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
                      
           
      
      //  addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
        //addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
       // addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
    } 
        Button add = new Button("ajouter");
        add.addActionListener((evt) -> {
            new ajouterReclamation(res).show();
        });
        add(add);
    }
  
    
   
    
private void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label("", "ImageOverlay");
         SpanLabel sl = new SpanLabel(text, "Title");
         
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
              sl,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            overlay,
                           //   FlowLayout.encloseIn(null),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
 private void addButton(Form previous, Resources res ,Reclamation o ,String concernant, String nom , Image img) {
     int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(1f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
      
    
       TextArea ta = new TextArea(o.getReclamation_nom());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
      
     cnt.add(BorderLayout.CENTER, BoxLayout.encloseX(ta));

       add(cnt);
       
     //System.out.println(o1.getSujet());
        image.addActionListener( e ->
               new ReclamationInfo(o, res).show()
       );
       
}
}
