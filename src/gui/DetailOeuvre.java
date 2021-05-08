/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
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
import entities.Oeuvre;
import service.OeuvrageService;

/**
 *
 * @author pc
 */
public class DetailOeuvre extends Form{
    Label comments ;
    String urlimg = " http://localhost/PIDevWEB/public/PI/IMG/";
     public DetailOeuvre(Form previous, Resources res , Oeuvre o) 
     {  
super("détail d'oeuvres", BoxLayout.y());
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
        
//       
 int height = Display.getInstance().convertToPixels(20f);
       int width = Display.getInstance().convertToPixels(14f);
//       Button image = new Button(img.fill(width, height));
//       image.setUIID("Label");
 Container cnt1 = new Container(BoxLayout.x());       
Container cnt = new Container(BoxLayout.y());
Container cnt2 = new Container(BoxLayout.y());
     //  cnt.setLeadComponent(image);
       TextArea ta = new TextArea(o.getNom());
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
       Label desc = new Label(o.getDescription(), "Label");
       Label domaine = new Label(o.getDoamine(), "Label");
       Label likes = new Label(o.getPrix() + " DT ", "LabelPrix");
       likes.setTextPosition(RIGHT);
//       if(!liked) {
//           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
//       } else {
//           Style s = new Style(likes.getUnselectedStyle());
//           s.setFgColor(0xff2d55);
//           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
//           likes.setIcon(heartImage);
//       }
     ImageViewer iv = new ImageViewer();
              EncodedImage   enc = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false); 
               URLImage  img = URLImage.createToStorage(enc, o.getImg(), " http://localhost/PIDevWEB/public/PI/IMG/"+o.getImg());
             iv.setImage(img);
             System.out.println(img);
               if (o.getQuantite()==0){
                   comments = new Label("hors stock", "Label");
               }
               else {
                   comments  = new Label(o.getQuantite()+"oeuvres", "Label");
               }
//       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
      
add(iv);
       cnt.add(ta);
       cnt.add(desc);
       cnt.add(domaine);
       cnt.add(likes);
       cnt.add( comments);
      cnt2.add("    Nom : "); 
      cnt2.add("    Domaine : ");
      cnt2.add("    Description : ");
      cnt2.add("    Prix : ");
      cnt2.add("    Quantité : ");
       cnt1.add(cnt2);
       cnt1.add(cnt);
         add(cnt1);
         //affichage ekher
//       add(iv);
//       cnt1.add("    Nom : "); 
//       cnt1.add(ta);
//        cnt2.add("    Description : ");
//       cnt2.add(desc);
//        cnt3.add("    Domaine : ");
//       cnt3.add(domaine);
//       cnt4.add("    Prix : ");
//       cnt4.add(likes);
//     cnt5.add("    Quantité : ");
//       cnt5.add( comments);
//     
//     
//       cnt.add(cnt1);
//       cnt.add(cnt2);
//       cnt.add(cnt3);
//       cnt.add(cnt4);
//       cnt.add(cnt5);
//       add(cnt);
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
    
    
     
     
    
     
}
