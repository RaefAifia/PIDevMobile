/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.codename1.ui.InfiniteContainer;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.entities.Formation;
import java.util.ArrayList;
import com.mycompany.myapp.services.FormationService;

/**
 *
 * @author HELA
 */
public class afficher extends BaseForm{
    
     private Resources theme;
//     String search = null;  
         ArrayList<Formation> data = new ArrayList<Formation>();
     public afficher(Form forme,Resources res)  {
         
         
        super("Les Formations", BoxLayout.y());
                 Form f2 = new Form(BoxLayout.y());
 f2.setTitle("My School");
 f2.setUIID("backgroundd");
        Container c1 = new Container(BoxLayout.y());  
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
       
//        getTitleArea().setUIID("Container");
//        setTitle("Newsfeed");
      //  getContentPane().setScrollVisible(false);
         TextField tfmatiere = new TextField("", "recherche");
//            Button rechercher = new Button();
//                   rechercher.setText("chercher");

        // c1.add(tfmatiere); 
            //   c1.add(rechercher); 
                // c1.add(pay);   
            //  this.add(c1);
              
              
        super.addSideMenu(res);
        
        
        tb.addSearchCommand((evt) -> {
            if (tfmatiere.getText().length() != 0) {
               FormationService fs = new FormationService(); 
            data = fs.getSearchR(tfmatiere.getText()); 
            
            
        }}
            
            );
//           
//         rechercher.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//              if (tfmatiere.getText().length() != 0) {
//               FormationService fs = new FormationService(); 
//            data = fs.getSearchR(tfmatiere.getText()); 
////              }
////              
//                for(int i=0; i<data.size(); i++){ 
//                     Label l4 = new Label("\n");
//                     l4.setUIID("labels");
//                     Label l5 = new Label("Nom : "+data.get(i).getTitre());
//                     l5.setUIID("labels");
//                     Label l6 = new Label("Email : "+data.get(i).getPrix());
//                     l6.setUIID("labels");
//                      
//        c1.add(l4);
//        c1.add(l5);
//        c1.add(l6);
//        
//       }
//                
//                  f2.add(c1);
//                 f2.show(); 
//              
//            }
//        });

        
//          TextField tfmatiere = new TextField("", "recherche");
//            Button rechercher = new Button();
//                   rechercher.setText("rechercher");
//         rechercher.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                     if (tfmatiere.getText().length() != 0) {
//                               Form f2 = new Form(BoxLayout.y());
//            f2.setTitle("My School");
//            f2.setUIID("backgroundd");
//            Container c1 = new Container(BoxLayout.y());
//            FormationService fs = new FormationService(); 
//              data = fs.getSearchR(tfmatiere.getText()); 
// 
//  
//    for(int i=0; i<data.size(); i++){ 
//                     Label l4 = new Label("\n");
//                     l4.setUIID("labels");
//                     Label l5 = new Label("Nom : "+data.get(i).getTitre());
//                     l5.setUIID("labels");
//                     Label l6 = new Label("Email : "+data.get(i).getPrix());
//                     l6.setUIID("labels");
//                      
//        c1.add(l4);
//        c1.add(l5);
//        c1.add(l6);
//        
//        }
//            }
//                     
//                     );
                 
      

//        
//        Style st = UIManager.getInstance().getComponentStyle("Title");
//
//        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
//        TextField searchField = new TextField("", "Toolbar Search"); // <1>
//        searchField.getHintLabel().setUIID("Title");
//        searchField.setUIID("Title");
//        searchField.getAllStyles().setAlignment(Component.LEFT);
//        hi.getToolbar().setTitleComponent(searchField);
//        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, st);
//        searchField.addDataChangeListener((i1, i2) -> { // <2>
//            String t = searchField.getText();
//            if(t.length() < 1) {
//                for(Component cmp : hi.getContentPane()) {
//                    cmp.setHidden(false);
//                    cmp.setVisible(true);
//        }
//    } else {
//        t = t.toLowerCase();
//        for(Component cmp : hi.getContentPane()) {
//            String val = null;
//            if(cmp instanceof Label) {
//                val = ((Label)cmp).getText();
//            } else {
//                if(cmp instanceof TextArea) {
//                    val = ((TextArea)cmp).getText();
//                } else {
//                    val = (String)cmp.getPropertyValue("text");
//                }
//            }
//            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
//            cmp.setHidden(!show); // <3>
//            cmp.setVisible(show);
//        }
//    }
//    hi.getContentPane().animateLayout(250);
//});
//hi.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
//    searchField.startEditingAsync(); // <4>
//});


//            InfiniteContainer list = new InfiniteContainer () {
//           private Formation [] formations;
//            //public Component [] fetchComponent (int index , int )
//          
//            @Override
//            public Component[] fetchComponents(int index, int amount) {
//                
//                if (search!=null && search.length()>0){
//            ArrayList<Formation> ff = new ArrayList<Formation>();
//            search = search.toLowerCase();
//            for (Formation f : formations) {
//            if (f.getTitre().toLowerCase().indexOf(search) > - 1)
//                    {ff.add(f);}                      
//                     }
//            
//            formations= new Formation[ff.size()];
//ff.toArray(formations);
//                 }
//                 return fetchComponents(index, amount);
//            }
//             tb.addSearchCommand(e -> {
//                 search = e.getSource();
//        list.refresh();
//        
//        });
//            
//            }
        
        
        
       
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();

  addTab(swipe, res.getImage("news-item.jpg"), spacer1, " Liste des formations");     
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
        Button b = new Button("Liste des formations");
        RadioButton list_f = RadioButton.createToggle("Liste des formations", barGroup);
        list_f.setUIID("SelectBar");
//        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
//        featured.setUIID("SelectBar");
//       
//        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
//        myFavorite.setUIID("SelectBar");
//        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, list_f)
                //FlowLayout.encloseBottom(arrow)
        ));
//        
//        list_f.setSelected(true);
//        arrow.setVisible(false);
//        addShowListener(e -> {
//            arrow.setVisible(true);
//            updateArrowPosition(list_f, arrow);
//        });
//         bindButtonSelection1(forme, res,list_f, arrow);
//        bindButtonSelection(featured, arrow);
//       
//        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
//        addOrientationListener(e -> {
//            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
//        });
//        
//        Image img ;
//            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
//            URLImage urlImage = URLImage.createToStorage(placeholder, gm.getImage(), "http://localhost/PIDevWEB-main/public/images1/"+gm.getImage());
//            img.setImage(urlImage);
//        


// hedha eli zedtou ena 
//      theme = UIManager.initFirstTheme("/theme");
//          Tabs tab = new Tabs();
//        UIBuilder ui = new UIBuilder();
//       
//        Container cnt2 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
//        tab.addTab("", cnt2);
//
//        this.add(tab);


          //  search tbadel 3onwen tool bar
            //prepare field
//            TextField searchField;
//            searchField = new TextField("", "Articles' List");
//            searchField.getHintLabel().setUIID("Title");
//            searchField.setUIID("Title");
//            getToolbar().setTitleComponent(searchField);
//            //if field content changed
//            searchField.addDataChangeListener((i1, i2) -> {
//            String t = searchField.getText();
//            if(t.length() < 1) {
//            for(Component cmp : getContentPane()) {
//            cmp.setHidden(false);
//            cmp.setVisible(true);
//            }
//            } else {
//            t = t.toLowerCase();
//            for(Component cmp: getContentPane()) {
//            //tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->container->spanlabel )
//         //  String val = ((SpanLabel) ((Container)((Container) cmp).getComponentAt(0)).getComponentAt(0)).getText();
//            System.out.println( val );
//            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
//            cmp.setHidden(!show);
//            cmp.setVisible(show);
//               FormationService fs = new FormationService(); 
//            data = fs.getSearchR(tfmatiere.getText()); 
//              for(int i=0; i<data.size(); i++){ 
//                     Label l4 = new Label("\n");
//                     l4.setUIID("labels");
//                     Label l5 = new Label("Nom : "+data.get(i).getTitre());
//                     l5.setUIID("labels");
//                     Label l6 = new Label("Email : "+data.get(i).getPrix());
//                     l6.setUIID("labels");
//                      
//        c1.add(l4);
//        c1.add(l5);
//        c1.add(l6);
//        
//        }
//            
//            }
//            }
//            getContentPane().animateLayout(250);
//            });
            
            
 Slider s = new Slider();   
       s.setEditable(true);
       s.setMinValue(0);
      s.setMaxValue(5);
      s.setIncrements(1);
    
       Button btn = new Button("Chercher");
       //default value 
   //   this.add(s);
      
        ArrayList<Formation> list_e = new FormationService().getList2();
      //  ArrayList<ListeParticipant> list_p = new ServiceEvenement().Afficher();
        
    
        for(Formation fl :  list_e ){
        
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder,fl.getImage(), "http://localhost/PIDevWEB-main/public/PI/IMG/"+fl.getImage());
           // img.setImage(urlImage);
                addButton(forme, res , urlImage, fl, fl.getTitre(),false, fl.getPrix());
                }
          

        //{ cnt2.add(addItemEvent(gmi)); }
        //this.add(cnt2);
       
       // this.show();
          
        
        
 //afficherFormations x=  new afficherFormations();
// radioContainer.add(x);
         System.out.println("heey ");
     }

    public afficher() {
        //To change body of generated methods, choose Tools | Templates.
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer,  String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
       
//        Style heartStyle = new Style(likes.getUnselectedStyle());
//        heartStyle.setFgColor(0xff2d55);
//        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
//        likes.setIcon(heartImage);
//        likes.setTextPosition(RIGHT);

     //   FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
      //  SpanLabel sl = new SpanLabel(text, "Title");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
               
                BorderLayout.south(
                    BoxLayout.encloseY(
                            overlay,
                        //    new SpanLabel(text, "LargeWhiteText"),
                           // FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }}
    
   private void addButton(Form forme, Resources res ,URLImage img,Formation f, String title, boolean liked, float prix) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

        Label likes = new Label(prix + " DT ", "LabelPrix");
       likes.setTextPosition(RIGHT);

       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes)
               ));
            Container cnt1 = new Container(BoxLayout.x()); 
       cnt1.add(cnt);
      add(cnt1);

       image.addActionListener(e ->  
               new FormationDetails(res,forme,f).show());
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
       private void bindButtonSelection1(Form previous,Resources res, Button b, Label arrow) {
       b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
                new afficher(previous, res).show();
            }
        });
    }
    
//     public Container addItemEvent(Formation gm){//pour remplir la liste
//        Container cn1=new Container(new BorderLayout());
//        Container cn2=new Container(BoxLayout.y());
//        Button afficher = new Button("afficher");
//        Label lab=new Label(gm.getTitre());
//       // Label lab2=new Label(gm.getDesciption());
//         afficher.addActionListener((evt) -> {
//               new FormationDetails(theme,this,gm).show();
//           });
//
//        
//         
//
////        Button btnmodifier=new Button("modifier");
////       btnmodifier.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent evt) {
////             
////                new RefugiesModifierInterface(gm).show();
////            }
////        });
//        Container cn3=new Container(BoxLayout.x());
//   //  cn3.add(btnSupp).add(btnmodifier);
//        
//       ImageViewer img = new ImageViewer();
//            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
//            URLImage urlImage = URLImage.createToStorage(placeholder, gm.getImage(), "http://localhost/PIDevWEB-main/public/images1/"+gm.getImage());
//            img.setImage(urlImage);
//          cn3.add(afficher);
//                    cn2.add(lab).add(cn3);
//        cn1.add(BorderLayout.WEST,img );
//        cn1.add(BorderLayout.CENTER,cn2);
//        
//        return cn1;
//          
//}
}
