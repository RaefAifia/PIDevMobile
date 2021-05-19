/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Relations;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.RelationService;
import com.mycompany.myapp.services.UserService;

/**
 *
 * @author asus
 */
public class Profil extends BaseForm {

    public Profil(User u , Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
             super.addSideMenu(res);

        
        tb.addSearchCommand(e -> {});
         //User u =UserService.getInstance().DetailUser(UserService.getCurrentUser().getUser_id());
         
        ImageViewer img1 = new ImageViewer();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, u.getImage(), "http://localhost/PIDevWEB-main/public/PI/IMG/"+u.getImage());
         System.out.println(urlImage);  
        Image img = res.getImage("8.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("", res.getImage(""), "BottomPad");
        Label twitter = new Label("", res.getImage(""), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(urlImage, "")),
                            twitter
                    )
                )
        ));

       
        Label username = new Label(u.getUsername(), res.getImage(""), "BottomPad" );
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
        
        Label nom = new Label(u.getNom(), res.getImage(""), "BottomPad" );
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);
        
        Label prenom = new Label(u.getPrenom(), res.getImage(""), "BottomPad" );
        prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", prenom);
        
        Label email = new Label(u.getEmail(), res.getImage(""), "BottomPad" );
        email.setUIID("TextFieldBlack");
        addStringValue("Email", email);
        
        Label adresse = new Label(u.getAdresse(), res.getImage(""), "BottomPad" );
        adresse.setUIID("TextFieldBlack");
        if(u.getUser_id()!=UserService.getCurrentUser().getUser_id()){
            
            adresse.setHidden(true);}else{
        addStringValue("Adresse", adresse);}
        
        Label numTel = new Label(String.valueOf(u.getNum_tel()), res.getImage(""), "BottomPad" );
        numTel.setUIID("TextFieldBlack");
        if(u.getUser_id()!=UserService.getCurrentUser().getUser_id()){
            
            numTel.setHidden(true);}else{
        addStringValue("Numeroe", numTel);}
        
//        Label bio = new Label(String.valueOf(u.getNum_tel()), res.getImage(""), "BottomPad" );
//        bio.setUIID("TextFieldBlack");
//        addStringValue("Bio", bio);
        Button btnconfirm = new Button("Confirmer email");
        System.out.println(u.getMailconfirme());
        if((u.getMailconfirme()==0)&&(u.getUser_id()==UserService.getCurrentUser().getUser_id())){
            btnconfirm.setHidden(false);
        addStringValue("", btnconfirm);
            btnconfirm.addActionListener((evt) -> {
                new EmailConfirm(res).show();
            });
        }else{
            btnconfirm.setHidden(true);
            
        }
        Button btnssabonner = new Button();
        if(u.getUser_id()!=UserService.getCurrentUser().getUser_id()){
        Relations r = RelationService.getInstance().checkR(UserService.getCurrentUser().getUser_id(), u.getUser_id());
            System.out.println("relation id "+r.getRelation_id());    
        if(r.getRelation_id()==0){
           btnssabonner.setText("s abonner");
           addStringValue("", btnssabonner);
           btnssabonner.addActionListener((evt) -> {
                RelationService.getInstance().addR(UserService.getCurrentUser().getUser_id(), u.getUser_id());
                
                new Profil(u, res).show();
            });
        }
        else{
             btnssabonner.setText("se désabonner");
             addStringValue("", btnssabonner);
             btnssabonner.addActionListener((evt) -> {
                RelationService.getInstance().deleteR(UserService.getCurrentUser().getUser_id(), u.getUser_id());
                System.out.println("user page"+ u.getUser_id());
                new Profil(u, res).show();
            });
        }}
        Button btnconfirmn = new Button("Confirmer numéro");
        //System.out.println(u.getMailconfirme());
        
        if(u.getUser_id()!=UserService.getCurrentUser().getUser_id()){
            
            numTel.setHidden(true);
            adresse.setHidden(true);
        }
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
