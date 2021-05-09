/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Formation;
import java.util.ArrayList;
import service.FormationService;

/**
 *
 * @author HELA
 */
public class afficherFormations  extends com.codename1.ui.Form {
    
    
    
         private Resources theme;
  
    public afficherFormations() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
        
        setTitle("Mes refugies");
        setLayout(BoxLayout.y());
      theme = UIManager.initFirstTheme("/theme");
        
        //Tabs : toolbar
        Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
       
        Container cnt2 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("", cnt2);
        //creer un groupe 
        Button ajouterBtn = new Button("ajouter une formation");
//        ajouterBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                new RefugiesAjouterInterface().show();
//            }
        //});
        this.add(tab).add(ajouterBtn);
        
        ArrayList<Formation> list_e = new FormationService().getList2();
      //  ArrayList<ListeParticipant> list_p = new ServiceEvenement().Afficher();
        
    
        for(Formation gmi :  list_e )
         
        { cnt2.add(addItemEvent(gmi));}
        //this.add(cnt2);
       
        this.show();
//         this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
//            new back().show();
//        });
     
    }

      public Container addItemEvent(Formation gm){//pour remplir la liste
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Label lab=new Label(gm.getTitre());
       // Label lab2=new Label(gm.getDesciption());
        
        Button btnSupp=new Button("supprimer");
        btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FormationService sr = new FormationService();
                //sr.supprimer(gm);
                new afficherFormations().show();
            }
        });
//        Button btnmodifier=new Button("modifier");
//       btnmodifier.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//             
//                new RefugiesModifierInterface(gm).show();
//            }
//        });
        Container cn3=new Container(BoxLayout.x());
      //  cn3.add(btnSupp).add(btnmodifier);
        
       ImageViewer img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, gm.getImage(), "http://localhost/PIDevWEB-main/public/images1/"+gm.getImage());
            img.setImage(urlImage);
        cn2.add(lab).add(cn3);
        cn1.add(BorderLayout.WEST,img );
        cn1.add(BorderLayout.CENTER,cn2);
        
        return cn1;
                
    }
    

    
}

    

