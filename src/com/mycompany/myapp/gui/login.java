/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
import com.mycompany.myapp.utils.EmailSend;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


import javafx.concurrent.Task;
import javax.mail.MessagingException;

/**
 *
 * @author asus
 */
public class login  extends Form{
    Form current;
    String x;
    private Resources theme;
//    public login(Form previous ){
//        setTitle("Login");
//        setLayout(BoxLayout.y());
//       Label username = new Label("                          Username           ");
//      
//       TextField tfName = new TextField("Username","Username");
//        Label password = new Label("                          Password           ");
//       TextField tfpass= new TextField("passw", "Password");
//        Button btnLogin = new Button("connexion");
//         addAll(username , password , btnLogin, tfName, tfpass);
//
//    }
    public  login(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        //setUIID("SignIn");
        
        
         Image img = res.getImage("signin-image.jpg");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() ) {
     //   img = img.scaledHeight(Display.getInstance().getDisplayHeight() );
//        }
//img.scaled(1700, 1700);
        ScaleImageLabel sl = new ScaleImageLabel(img);
        //sl.setUIID("BottomPad");
        //sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        ImageViewer img1 = new ImageViewer();
        img1.setImage(img.scaled(img.getWidth(), img.getHeight()));
       //sl.setHeight(img.getHeight()*5);
       


        TextField email = new TextField("", "Email", 20, TextField.ANY);
        email.setUIID("labelMIMI");
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        password.setUIID("labelMIMI");
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Connexion");
        Button signUp = new Button("Créer compte");
        //signUp.addActionListener(e -> new SignUp(res).show());
        signUp.addActionListener((evt) -> {
            try {
                new SignUp(res).show();
            } catch (IOException ex) {
                //Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       Label ui = new Label();
        //signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("vous n'avez pas de compte?");
        add(BorderLayout.CENTER,img1);
        Container content = BoxLayout.encloseY(
               
              
              
                new FloatingHint(email),
                
                new FloatingHint(password),
                
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE, content);
        signIn.requestFocus();
        //signIn.addActionListener(e -> new NewsfeedForm(res).show());
        signIn.addActionListener((evt) -> {
       
        try{
            if(password.getText().isEmpty()|| email.getText().isEmpty()){
                Dialog.show("veuillez remplir tous les champs", "","annuler", "ok");
            }
            else{
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                String image ="";
                User u = new User( email.getText(),password.getText());
                User x = UserService.getInstance().login(u);
                if(x.getUser_id()==0){
                  Dialog.show("email ou mot de passe incorrecte","","annuler", "ok");
                }else{
                    System.out.println("id "+ x.getUser_id());
                    //UserService.getInstance().DetailUser(u.getUser_id());
                    UserService.setCurrentUser(x);
                    new Profil(x,res).show();
                }
                
                
            }
                    
        }catch(Exception ex){
            ex.printStackTrace();
        }         
        });
    }
    
}
