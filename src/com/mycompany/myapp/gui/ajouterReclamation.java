/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ReclamationService;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author asus
 */
public class ajouterReclamation extends BaseForm {

    public ajouterReclamation(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
             super.addSideMenu(res);
             
             
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
       
        TextField nom = new TextField("", "Nom de réclamation", 20, TextField.ANY);
        TextField description = new TextField("", "Description", 20, TextField.ANY);
        
        
        TextField x = new TextField("", "Concernant", 20, TextField.ANY);
        TextField concernant = new TextField("", "Sujet", 20, TextField.ANY);
        nom.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
        x.setSingleLineTextArea(false);
        concernant.setSingleLineTextArea(false);
        
        nom.setUIID("labelMIMI");
        description.setUIID("labelMIMI");
        x.setUIID("labelMIMI");
        concernant.setUIID("labelMIMI");
        Button next = new Button("Confirmer");
        Button next1 = new Button("Retour");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(x),
                createLineSeparator(),
                new FloatingHint(concernant),
                createLineSeparator(),
                
                next, 
                next1
               
                
        );
        add(content);
         next.addActionListener((evt) -> {
              try{
              if(nom.getText().isEmpty() || description.getText().isEmpty() || x.getText().isEmpty() || concernant.getText().isEmpty()){
                Dialog.show("veuillez remplir tous les champs", "","annuler", "ok");
            } else{
                  
                  Reclamation r = ReclamationService.getInstance().addRec(nom.getText(), x.getText(), concernant.getText(), UserService.getCurrentUser().getUser_id(),description.getText());
                  if(r.getReclamation_id()==0){
                   Dialog.show("veuillez commander le produit avant", "","annuler", "ok");

                  }else{
                  Form f = new Form();
                  new Reclamations(f,res).show();
              }}
               }catch(Exception ex){
            ex.printStackTrace();
        }
             
         });
         next1.addActionListener((evt) -> {
              Form f = new Form();
             new Reclamations(f,res).show();
         });
}
}
