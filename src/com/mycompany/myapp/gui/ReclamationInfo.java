/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ReclamationService;
import com.mycompany.myapp.services.UserService;
import java.util.Date;

/**
 *
 * @author asus
 */
public class ReclamationInfo extends BaseForm {

    public ReclamationInfo(Reclamation v , Resources res) {

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
         //User u =UserService.getInstance().DetailUser(UserService.getCurrentUser().getUser_id());
         
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
       
        Reclamation u = ReclamationService.getInstance().DetailRec(v.getReclamation_id());

       
        Label nom = new Label(u.getReclamation_nom(), res.getImage(""), "BottomPad" );
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom de réclamation", nom);
        
        Label description = new Label(u.getDescription(), res.getImage(""), "BottomPad" );
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);
        
        Label concernant = new Label(u.getX(), res.getImage(""), "BottomPad" );
        concernant.setUIID("TextFieldBlack");
        addStringValue("X", concernant);
        
        Label sujet = new Label(u.getSujet(), res.getImage(""), "BottomPad" );
        sujet.setUIID("TextFieldBlack");
        addStringValue("concernant", sujet);
        
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        String date = formatter.format(u.getDate());
//        Label datee = new Label(date, res.getImage(""), "BottomPad" );
//        datee.setUIID("TextFieldBlack");
//        addStringValue("Date", datee);
        //String conc="";
        
        
//        Label bio = new Label(String.valueOf(u.getNum_tel()), res.getImage(""), "BottomPad" );
//        bio.setUIID("TextFieldBlack");
//        addStringValue("Bio", bio);
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
