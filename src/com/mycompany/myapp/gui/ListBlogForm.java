/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Oeuvre;
import com.mycompany.myapp.services.Blog_Service;
import com.mycompany.myapp.services.Commentaire_Service;


/**
 *
 * @author hp
 */
public class ListBlogForm extends Form{ 
    
 Form f;

    Container cn1;
    ImageViewer iv;
    URLImage img;
    Label lblnomeve;
    Label nomeve;

    Resources resq;
    public ListBlogForm(Form previous, Resources res) {  
super("Liste des Blog", BoxLayout.y());
resq=res;
Toolbar tb = new Toolbar(true);  
        setToolbar(tb);
       // getTitleArea().setUIID("Container");
        //setTitle("Liste des oeuvres");
        getContentPane().setScrollVisible(false);
        
    // super.addSideMenu(res);
      tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
       addTab(swipe, res.getImage("news-item.jpg"), spacer1, " Liste des oeuvres");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "100 Likes  ");
                
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
      Component.setSameSize(radioContainer, spacer1, spacer2);
        
   add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesliste = RadioButton.createToggle("les oeuvres", barGroup);
        mesliste.setUIID("SelectBar");
        RadioButton mesfavoris = RadioButton.createToggle(" mes favoris ", barGroup);
         mesfavoris.setUIID("SelectBar");
        RadioButton monpanier = RadioButton.createToggle("voir panier", barGroup);
        monpanier.setUIID("SelectBar");
       
       Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
//       mesliste.addActionListener((e)-> {
//           InfiniteProgress ip = new InfiniteProgress();
//           final Dialog ipDlg = ip.showInifiniteBlocking();
//           refreshTheme();
//       });
//       
       add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesliste, mesfavoris, monpanier),
                FlowLayout.encloseBottom(arrow)
        ));
        
        mesliste.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
       arrow.setVisible(true);
        updateArrowPosition(mesliste, arrow);
        });
        bindButtonSelection1(previous, res,mesliste, arrow);
        bindButtonSelection2(previous, res, mesfavoris, arrow);
        bindButtonSelection3(previous, res, monpanier, arrow);
        // special case for rotation
//        addOrientationListener(e -> {
//          updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
//        });
 
    
    
    
    Toolbar.setGlobalToolbar(true);
                  this.add(new InfiniteProgress());
  Display.getInstance().scheduleBackgroundTask(() -> { 
      Display.getInstance().callSerially(() -> { 
                  this.removeAll();
             for (Blog c : new Blog_Service().findAll()) {

            this.add(addItem_Blog(c));

        }
             
            this.revalidate();
      });
             });
          this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
               this.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
           new MyApplication().start();
        });
 
    
    }
  
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
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
          public MultiButton  addItem_Blog(Blog c) {

        
                MultiButton m = new MultiButton();
                // Phediii l projet integration
              //  String url = "http://localhost/PIDevWEB/public/PI/IMG/"+c.getImage();
              String url = "http://127.0.0.1:8000/uploads/images/"+c.getImage();
    
              
                System.out.println(url);
                
                 m.setTextLine1(c.getTitre());
        m.setTextLine2(c.getDescription());

           Image imge;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3), false);
        imge = URLImage.createToStorage(enc, url, url);
            m.setIcon(imge);
        m.setEmblem(resq.getImage("news-tab-down-arrow.png"));
    
       

      
        

        m.addActionListener(e -> {

            Form f2 = new Form("Detail",BoxLayout.y());
           
         
        
            Label Titre_lab = new Label("Titre :");
            Label Titre_etxt = new Label(c.getTitre());
            Label des_lab = new Label("Description:");
            Label des_etxt = new Label(c.getDescription());
       

           
                
            f2.add(Titre_lab).add(Titre_etxt).add(des_lab).add(des_etxt).add("------------").add("Commentaires");
            
             for (Commentaire co : new Commentaire_Service().findAll(c.getId())) {

            f2.add(addItem_Commentaire(co));

        }
                     Button btn = new Button("Commentaire");
                     
                     f2.add(btn);
             btn.addActionListener(l-> {
             
                 Form f3= new Form("Add Commentaire",BoxLayout.y());
                 
                     TextField Commentaire = new TextField("", "Commentaire", 20, TextArea.ANY);
                            Commentaire.setUIID("NewsTopLine");
                      Validator val_Commentaire = new Validator();
 Button btn_submit = new Button("Submit");
              
             btn_submit.addActionListener(lw-> {
                   val_Commentaire.addConstraint(Commentaire, new LengthConstraint(8));

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_Commentaire.addConstraint(Commentaire, new RegexConstraint(text_saisir_des_caracteres, ""));
               if (Commentaire.getText().equals("")) {
                                    Dialog.show("Erreur", "Champ vide de Commentaire ", "OK", null);

                                } else if (val_Commentaire.isValid()) {
                                    Dialog.show("Erreur Commentaire !", "il faut saisir des caracteres  !", "OK", null);
                                }
               else
                                {
                                    Commentaire_Service service = new Commentaire_Service();
                                    
                                    Commentaire cm = new Commentaire();
                                    cm.setText(Commentaire.getText());
                                    service.addCommentaire(cm, c.getId());
                                     Dialog.show("Ajout !", "Commentaire  !", "OK", null);
                                                new ListBlogForm(f3,resq).showBack();
                                }
             }  );
             
             f3.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
          new ListBlogForm(f2, resq).show();
        });
      
                     
                     f3.add(Commentaire);
                         f3.add(btn_submit);
                     
                 
                 f3.show();
             
             });
             
             
     f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
       new ListBlogForm(f2, resq).show();
        });
 f2.show();
        });
      
  

       return m;

    }
      public Container addItem_Commentaire(Commentaire q) {

        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        Label nom = new Label("Text:  :"+q.getText());
                 Label date = new Label("Date:  :"+q.getDate());
        
        

        cn2.add(nom);
         cn2.add(date);
        cn1.add(BorderLayout.WEST, cn2);
        return cn1;
         }

 private void addButton(Form previous, Resources res ,Oeuvre o ,URLImage img, String nom , boolean liked, float prix,String quantité, String description) {
       int height = Display.getInstance().convertToPixels(20f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(nom);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       Label desc = new Label(description, "Label");
       Label likes = new Label(prix + " DT ", "LabelPrix");
       likes.setTextPosition(RIGHT);
//       if(!liked) {
//           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
//       } else {
//           Style s = new Style(likes.getUnselectedStyle());
//           s.setFgColor(0xff2d55);
//           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
//           likes.setIcon(heartImage);
//       }
       Label comments = new Label(quantité , "Label");
//       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
        Button btnListTasks = new Button();
        Style s = new Style(btnListTasks.getUnselectedStyle());
        
         //s.setFgColor(0xa65959);
       FontImage ajouterP =FontImage.createMaterial(FontImage.MATERIAL_ADD_SHOPPING_CART, s);
         btnListTasks.setIcon(ajouterP);
         btnListTasks.setTextPosition(RIGHT);
         
       //  .setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       cnt.add(BorderLayout.EAST, 
               BoxLayout.encloseY(
                       ta,
                       desc,
                       BoxLayout.encloseX(likes, comments)
               ));
     Container cnt1 = new Container(BoxLayout.x()); 
     Container cnt2 = new Container(BoxLayout.xRight()); 
       cnt1.add(cnt);           
       cnt2.add(btnListTasks);
        cnt1.add(cnt2); 
       if(o.getQuantite()==0){
           btnListTasks.setVisible(false);
       }
       add(cnt1);
       
       image.addActionListener( e -> 
               new DetailOeuvre(previous, res, o).show()
       );
//       raef
//       btnListTasks.addActionListener
   }
    
    private void bindButtonSelection1(Form previous,Resources res, Button b, Label arrow) {
       b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
    private void bindButtonSelection2(Form previous,Resources res, Button b, Label arrow) {
       b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
                new ListeFavoris(previous, res).show();
            }
        });
    }
    
    private void bindButtonSelection3(Form previous,Resources res, Button b, Label arrow) {
       b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
                
            }
        });
    }
}
