/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MediaPlayer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;

import static com.codename1.ui.CN.callSerially;
import static com.codename1.ui.CN.isDesktop;
import static com.codename1.ui.CN.isSimulator;
import static com.codename1.ui.CN.scheduleBackgroundTask;

import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import entities.Cours;
import entities.Formation;


import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import services.CoursService;
import services.FormationService;

/**
 *
 * @author HELA
 */
public class afficherCoursList  extends BaseForm{

     private Resources theme;
     Formation a;
     //Media video;
     public afficherCoursList(Resources res, Formation f)  {
        super("Cours", BoxLayout.y());
        
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Newsfeed");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
addTab(swipe, res.getImage("news-item.jpg"), spacer1, "Les Cours  ");
                
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
                
//        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
//        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
//        featured.setUIID("SelectBar");
//        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
//        popular.setUIID("SelectBar");
//        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
//        myFavorite.setUIID("SelectBar");
//        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
//        
//        add(LayeredLayout.encloseIn(
//                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
//                FlowLayout.encloseBottom(arrow)
//        ));
//        
//        all.setSelected(true);
//        arrow.setVisible(false);
//        addShowListener(e -> {
//            arrow.setVisible(true);
//            updateArrowPosition(all, arrow);
//        });
//        bindButtonSelection(all, arrow);
//        bindButtonSelection(featured, arrow);
//        bindButtonSelection(popular, arrow);
//        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
//        addOrientationListener(e -> {
//            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
//        });
            theme = UIManager.initFirstTheme("/theme");
          Tabs tab = new Tabs();
        UIBuilder ui = new UIBuilder();
       
        Container cnt2 = ui.createContainer(theme, "GUI 1");//ajouter graphiquement un GUI element
        tab.addTab("", cnt2);

        this.add(tab);
        
//      ArrayList<Cours> list_e = new FormationService().coursList(a);
      FormationService fs = new FormationService();
        
      CoursService cs = new CoursService();
   ArrayList<Cours> list_new =  cs.getList(f);
     
        for(Cours cc :  list_new )
          

        {
          ImageViewer img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, cc.getFile(), "http://localhost/PIDevWEB-main/public/images1/"+cc.getFile());
            img.setImage(urlImage);
            
  
   String urlM = "http://localhost/PIDevWEB-main/public/images1/"+cc.getMedia();
   
   
   
   Button filepdf = new Button("Afficher les documents");
     filepdf.addActionListener(e -> {
       FileSystemStorage fss = FileSystemStorage.getInstance();
     String fileName = "http://localhost/PIDevWEB-main/public/images1/"+cc.getFile();
             System.out.println(urlM);
  if (!fss.exists(fileName)) {
          Util.downloadUrlToFile("http://localhost/PIDevWEB-main/public/images1/"+cc.getCours_id(), fileName, true);
        
            } 
            Display.getInstance().execute(fileName);
       
         });
       Button fileVideo = new Button("Visualiser les videos");
         fileVideo.addActionListener(e -> {
       FileSystemStorage fss = FileSystemStorage.getInstance();
       String fileName ="http://localhost/PIDevWEB-main/public/images1/"+cc.getMedia();
             System.out.println(fileName);
        if (!fss.exists(fileName)) {
          Util.downloadUrlToFile("http://localhost/PIDevWEB-main/public/images1/"+cc.getCours_id(), fileName, true);
        
            } 
            Display.getInstance().execute(fileName);
       
         });
      //  current.add(back);
      this.add(filepdf);
      this.add(fileVideo);
      this.show();

//      
//  
//        //  new showListFormation(previous).showBack();
//        current.show();
  Container c1 = new Container(BoxLayout.y());  
   Button down = new Button();
                   down.setText("download");
                  // c1.add(down);
          MediaF mf = new MediaF(forme);
           down.addActionListener(e -> {
               System.out.println("nekhdem");
         
               mf.downloadFile(urlM,cc);
               System.out.println(urlM);
        });
//          c1.add(devGuide);
          this.add(c1);
    //}
  

        //  mf.downloadFile(urlM);
//  Form hi=  mf.playVideoOnNewForm(urlM, forme);

// try{
//Media      video = MediaManager.createMedia(urlM, true);
//       MediaPlayer player = new MediaPlayer(video);
//         addButton(forme, res , urlImage, cc, cc.getTitre(),false, cc.getNiveau(),video);
// }
// catch(IOException error){
//                Log.e(error);
//                ToastBar.showErrorMessage("Error loading video");
//            }


       // videoForm.show();
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_VIDEO_LIBRARY, s);
//        videoForm.getToolbar().addCommandToRightBar(new Command("", icon) {
//               @Override
//    public void actionPerformed(ActionEvent evt) {
//        scheduleBackgroundTask(()-> {
//            try{
//                Media video = MediaManager.createMedia(urlM, true);
//                if(video != null){
//                    video.prepare();
//                    video.setNativePlayerMode(!isDesktop() && !isSimulator());
//                    MediaPlayer player = new MediaPlayer(video);
//                    player.setAutoplay(true);
//                    
//                    System.out.println(urlM);
//
//                    callSerially(()->{
//                        videoForm.removeAll();
//                        videoForm.setLayout(new BorderLayout());
//                        videoForm.add(BorderLayout.CENTER, player);
//                        videoForm.revalidate();
//                    });
//                }
//            }
//            catch(IOException error){
//                Log.e(error);
//                 error.printStackTrace();
//    System.exit(1);
//                ToastBar.showErrorMessage("Error loading video");
//            }
//        });
//    }
//    });
                final Form hi = new Form("MediaPlayer", new BorderLayout());
        hi.setToolbar(new Toolbar());
        Button play = new Button("play");
        //hi.getToolbar().addCommandToRightBar(new Command("", icon) 


         play.addActionListener( (evt) ->{
              System.out.println("lenna");
            // MediaF mf = new MediaF(forme);
             mf.playVideoOnNewForm(urlM, forme); 
             System.out.println(urlM);});
         Container c = new Container();

        // c.add(play);
         add(c);
     
//        Display.getInstance().openGallery((e) -> {
//            if(e != null && e.getSource() != null) {
//                
//                
//             String   urlM = "http://localhost/PIDevWEB-main/public/images1/"+cc.getMedia();
//                  urlM=      (String)e.getSource();
//                
//                try {
//                    Media video = MediaManager.createMedia(urlM, true);
//                    hi.removeAll();
//                    hi.add(BorderLayout.CENTER, new MediaPlayer(video));
//                    hi.revalidate();
//                    System.out.println(urlM);
//                } catch(IOException err) {
//                    Log.e(err);
//                } 
//            }
//        }, Display.GALLERY_VIDEO);
   
//
//hi.show();        
        
        
         addButton(forme, res , urlImage, cc, cc.getTitre(),false, cc.getNiveau());
        
         System.out.println(list_new);
         System.out.println("yes");
     //  this.add(play);
        }
        //this.add(cnt2);
     
        this.show();
    
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


     public void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
    
      private void addButton(Form forme, Resources res ,URLImage img,Cours c, String title, boolean liked, String niveau ) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
    
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);
        
   //   cnt.add( v.getVideoComponent());
//   
//    cnt.setLayout(new BorderLayout());
// cnt.addComponent(BorderLayout.EAST, play);

        Label likes = new Label(niveau );
       likes.setTextPosition(RIGHT);

      
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes) 
               ));
            Container cnt1 = new Container(BoxLayout.x()); 
       cnt1.add(cnt);
      add(cnt1);

//       image.addActionListener(e ->  
//               new FormationDetails(res,forme,f).show());
   }

    
       private void bindButtonSelection1(Form previous,Resources res, Button b, Label arrow) {
       b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
                new afficher(previous, res).show();
            }
        });
    }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
        
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
//      public Container addItemEvent(Cours gm){//pour remplir la liste
//        Container cn1=new Container(new BorderLayout());
//        Container cn2=new Container(BoxLayout.y());
////        Button afficher = new Button("afficher");
//      Label lab=new Label(gm.getTitre());
////       // Label lab2=new Label(gm.getDesciption());

//           });
//////
//
//        Container cn3=new Container(BoxLayout.x());
//   //  cn3.add(btnSupp).add(btnmodifier);
//        
//       ImageViewer img = new ImageViewer();
//            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth()/3, this.getWidth()/3),false);
//            URLImage urlImage = URLImage.createToStorage(placeholder, gm.getFile(), "http://localhost/PIDevWEB-main/public/images1/"+gm.getFile());
//            img.setImage(urlImage);
//        //  cn3.add(afficher);
//                //    cn2.add(lab).add(cn3);
//        cn1.add(BorderLayout.WEST,img );
//        cn1.add(BorderLayout.CENTER,cn2);
//        
//        return cn1;
//          
//}
}
