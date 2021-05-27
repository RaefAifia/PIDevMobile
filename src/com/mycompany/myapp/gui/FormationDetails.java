/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.payment.Purchase;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Slider;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.codename1.ext.codescan.CodeScanner;
import com.codename1.ext.codescan.ScanResult;

import com.mycompany.myapp.entities.Formation;
import com.mycompany.myapp.entities.Inscription;
import com.mycompany.myapp.entities.User;
import java.io.IOException;

//import java.util.logging.Level;
//import java.util.logging.Logger;
import com.mycompany.myapp.services.FormationService;
import com.mycompany.myapp.services.UserService;



/**
 *
 * @author HELA
 */
public class FormationDetails  extends BaseForm{
     private static ScanResult callback;
//Static Formation fstat;
   User user=UserService.getCurrentUser();
      public FormationDetails(Resources res,Form forme, Formation f) {
     
          
     super("Détail de la formation "+f.getTitre(), BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       // setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
    super.addSideMenu(res);
        
     Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
    addTab(swipe, res.getImage("news-item.jpg"), spacer1, "Espace Formation  ");
    //    addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ", "66 Comments", "Dogs are cute: story at 11");
                       swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
       // rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
//        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        
      

      
        //this.setLayout(BoxLayout.y());
        TextArea lab = new TextArea("Nom: "+f.getTitre());
               TextArea lab2 = new TextArea("Description: "+f.getDescription());
                TextArea lab3 = new TextArea("Domaine: "+f.getDomaine());
          TextArea lab4 = new TextArea("Niveau: "+f.getNiveau());
            TextArea lab5 = new TextArea("Langue: "+f.getLangue());
             TextArea lab1 = new TextArea("Date: "+f.getDate());
              TextArea lab8 = new TextArea ("Durée: "+f.getDuree());
              TextArea lab7 = new TextArea("Prix: "+f.getPrix());
              TextArea lab6 = new TextArea ("Lieu: "+f.getLieu());
              TextArea lab9 = new TextArea ("Lieu: "+f.getNbInsc());
              lab8.setEditable(false);
               lab.setEditable(false);
               lab1.setEditable(false);
               lab2.setEditable(false);
               lab3.setEditable(false);
               
               lab4.setEditable(false);
               lab5.setEditable(false);
               lab6.setEditable(false);
               lab7.setEditable(false);
               
       // lab.setUIID("Title");
           ImageViewer img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, f.getImage(), "http://localhost/PIDevWEB-main/public/PI/IMG/"+f.getImage());
            img.setImage(urlImage);
    
       
        // Button payer = new Button("payer");
    
      
//         payer.addActionListener((evt) -> {
//            
//            PurchaseF pf = new PurchaseF();
//      pf.createDemo(f);
//         });


              
              
//               final Form hi = new Form("Codescan Demo");
//        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
//        ButtonGroup bgg = new ButtonGroup();
//        final RadioButton qr = new RadioButton("QR Code");
//        final RadioButton bar = new RadioButton("Bar Code");
//        bg.add(qr);
//        bg.add(bar);
//        hi.addComponent(new Label("Code Type"));
//        hi.addComponent(qr);
//        hi.addComponent(bar);
//        
//        Button scanBtn = new Button("Scan Code");
//        scanBtn.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent evt) {
//                final Container cnt = hi;
//
//                if(qr.isSelected()){
//                    CodeScanner.getInstance().scanQRCode(new ScanResult() {
//
//                        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
//                            //barCode.setText("Bar: " + contents);
//                            cnt.addComponent(new Label(contents));
//                            cnt.revalidate();
//                        }
//
//                        public void scanCanceled() {
//                            System.out.println("cancelled");
//                        }
//
//                        public void scanError(int errorCode, String message) {
//                            System.out.println("err " + message);
//                        }
//                    });
//                }else{
//                    CodeScanner.getInstance().scanBarCode(new ScanResult() {
//
//                        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
//                            //barCode.setText("Bar: " + contents);
//                            cnt.addComponent(new Label(contents));
//                            cnt.revalidate();
//                        }
//
//                        public void scanCanceled() {
//                            System.out.println("cancelled");
//                        }
//
//                        public void scanError(int errorCode, String message) {
//                            System.out.println("err " + message);
//                        }
//                    });        
//                }
//            }
//            
//        });
//        if (CodeScanner.isSupported()) {
//            hi.addComponent(scanBtn);
//        } else {
////            hi.addComponent(new SpanLabel("Sorry.  Codescanner not supported on this platform"));
////        }
//         hi.addComponent(scanBtn);
//        // add(hi);
//        hi.show();
              
                    Button inscrit = new Button("S'inscrire");
               Button inscrit1 = new Button("Reserver");
             //    Button accederC = new Button("Accéder aux cours");
           //   if (f.getLieu() == "en ligne " ||f.getLieu() ==  "online" ||f.getLieu() ==  "ONLINE" || f.getLieu() ==  "EN LIGNE" )
           
              if (f.getLieu().equals("en ligne" )|| f.getLieu().equals("online" ) || f.getLieu().equals("EN LIGNE" ) || f.getLieu().equals("ONLINE" ) )
              {
            inscrit1.setVisible(false);  inscrit.setVisible(true); 
            
                     inscrit.addActionListener((evt) -> {
     FormationService.getInstance().inscription( f,UserService.getCurrentUser().getUser_id());
       
           Dialog.show("success", "Vous êtes bien inscrit", new Command("OK"));
            new afficherCoursList(res,f).show();
                         System.out.println("hedhi?");
       
            });
              }
           else {  
                   inscrit.setVisible(false);
                  inscrit1.setVisible(true);
                 
                  inscrit1.addActionListener((evt) -> {
             
        FormationService.getInstance().inscription( f,UserService.getCurrentUser().getUser_id());
        Dialog.show("success", "Vous êtes bien inscrit", new Command("OK"));
         new QrFormation(res,forme,f).show();
              } );

              }

      
        Slider s = new Slider();
        s.setEditable(true);
       s.setMinValue(0);
      s.setMaxValue(5);
      s.setIncrements(1);
      // s.setProgress(5);
   
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style style = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, style).toImage();
    style.setOpacity(100);
    style.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, style).toImage();
    initStarRankStyle(s.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(s.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(s.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(s.getSliderFullUnselectedStyle(), fullStar);
    s.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
     Button btnE = new Button("Enregistrer votre notation");
    // Label l1 = new Label ("Vous pouvez noter cette formation");
      Font mediumBoldSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
      
     Label l = new Label ("Note");
       l.getUnselectedStyle().setFont(mediumBoldSystemFont);
     s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                    l.setText(Integer.toString(s.getProgress(evt)));
              
            }
        });
          
     
      btnE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    //FormationService fs = new FormationService();
                   // FormationService.getInstance().AjouterRating(s.getProgress(evt), f);
//              if(  FormationService.getInstance().isRated(1, f)==0)     {
                     
                    FormationService.getInstance().AjouterRating(s.getProgress(evt), f,UserService.getCurrentUser().getUser_id());
                   
                    System.out.println(f.getFormation_id());
              
                Dialog.show("success", "Notation est Bien ajoutée", new Command("OK"));
              }
//              
//              else {FormationService.getInstance().ModifRating(s.getProgress(evt), f);
//                Dialog.show("success", "Notation est Bien modifiée", new Command("OK"));}}
//                
            
catch (IOException ex) {
                    System.out.println(ex.getMessage());   
                   
                }
               
            } 
      } );
     
     Container cn = new Container();
     cn.add(s);
     cn.add(l);
    cn.add(btnE);
        this.addAll(lab,lab1,lab2,lab3,lab4,lab5,lab8,lab6,lab7,img, inscrit,inscrit1,cn);
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->  new afficher(forme,res).showBack());
    

    }

    public FormationDetails(Resources theme) {
      
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    


public void addTab(Tabs swipe, Image img, Label spacer, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
   //    img = img.scaledHeight(Display.getInstance().getDisplayHeight() );
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


     public void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
}




