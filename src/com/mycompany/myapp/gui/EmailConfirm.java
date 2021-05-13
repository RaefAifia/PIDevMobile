/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
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
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

/**
 *
 * @author asus
 */
public class EmailConfirm  extends BaseForm{
     String x;
    public EmailConfirm(Resources res){
        
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        Image img = res.getImage("profile-background.jpg");
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
                                ),
                            twitter
                    )
                )
        ));
        try {
            x = EmailSend.SendMail("aa");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        Label co = new Label("Veuillez fournir le code de confirmation", res.getImage(""), "BottomPad" );
        TextField codec = new TextField("");
        codec.setUIID("TextFieldBlack");
        Button btnconfirm = new Button("Confirmer");
        
       btnconfirm.addActionListener((evt) -> {
        
         try{
            if(codec.getText().isEmpty()){
                Dialog.show("veuillez remplir tous les champs", "","annuler", "ok");
            }
            else{
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                String image ="";
                
                if(!codec.getText().equals(x)){
                  Dialog.show("code incorrecte","","annuler", "ok");
                }else{
                    
                   User u =UserService.getInstance().confirmMail(UserService.getCurrentUser().getUser_id());
                    UserService.setCurrentUser(u);
                    new Profil(res).show();
                }
                
                
            }
                    
        }catch(Exception ex){
            ex.printStackTrace();
        }}         
       );
        addAll(co, codec , btnconfirm);
      

        
    }
    
}
