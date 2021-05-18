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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.Oeuvre;
import entities.RatingO;

import service.FavorisOService;
import service.RatingOService;

/**
 *
 * @author pc
 */
public class DetailOeuvre extends BaseForm{
    Label comments ;
       RatingOService r = new RatingOService();
RatingO rtest = new RatingO();
Container cnt1;
 Form f1;
    Form detaille;
   
    String urlimg = "http://localhost/PIDevWEB/public/PI/IMG/";
     public DetailOeuvre(Form previous, Resources res , Oeuvre o) 
     {  
super("détail d'oeuvre", BoxLayout.y());
Toolbar tb = new Toolbar(true);  
        setToolbar(tb);
       // getTitleArea().setUIID("Container");
        //setTitle("Liste des oeuvres");
        getContentPane().setScrollVisible(false);
        
     super.addSideMenu(res);
        Tabs swipe = new Tabs();
        Label spacer1 = new Label();
        Label spacer2 = new Label();
       addTab(swipe, res.getImage("news-item.jpg"), spacer1, " détail d'oeuvre");
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
                GridLayout.encloseIn(3, mesliste, mesfavoris, monpanier)
             
        ));
        
        mesliste.setSelected(true);
        
        addShowListener(e -> {
     
       
        });
        bindButtonSelection1(previous, res,mesliste, arrow);
        bindButtonSelection2(previous, res, mesfavoris, arrow);
        bindButtonSelection3(previous, res, monpanier, arrow);
        
//       
 int height = Display.getInstance().convertToPixels(20f);
       int width = Display.getInstance().convertToPixels(14f);
//       Button image = new Button(img.fill(width, height));
//       image.setUIID("Label");

// Container cnt1 = new Container(BoxLayout.x());       
//Container cnt = new Container(BoxLayout.y());
//Container cnt2 = new Container(BoxLayout.y());


     //  cnt.setLeadComponent(image);
       TextArea ta = new TextArea(o.getNom());
       ta.setUIID("Label");
       ta.setEditable(false);
       Label desc = new Label(o.getDescription(), "Label");
       Label domaine = new Label(o.getDoamine(), "Label");
       Label likes = new Label(o.getPrix() + " DT ", "LabelPrix");
       likes.setTextPosition(RIGHT);
     ImageViewer iv = new ImageViewer();
              EncodedImage   enc = EncodedImage.createFromImage(Image.createImage(this.getWidth(), this.getWidth()),false); 
               URLImage  img = URLImage.createToStorage(enc, o.getImg(), " http://localhost/PIDevWEB/public/PI/IMG/"+o.getImg());
             iv.setImage(img.scaled(700, 700));
            
               if (o.getQuantite()==0){
                   comments = new Label("hors stock", "Label");
               }
               else {
                   comments  = new Label(o.getQuantite()+" oeuvres", "Label");
               }
   FavorisOService f = new FavorisOService();
Button b= new Button("  ","ButtonF");
Oeuvre test = new Oeuvre();
FavorisOService.getInstance().isfav(o, 1, test);
     
       Style s = new Style(b.getUnselectedStyle());
       Style s1 = new Style(b.getUnselectedStyle());
           s.setFgColor(0xff2d32);
          s1.setFgColor(0xc3C0C0);
 if(test.getOeuvrage_id()==0) {
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s1);
     b.setIcon(heartImage);
 } else {
     FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
     b.setIcon(heartImage);
 }
  b.addActionListener( e -> {
     FavorisOService.getInstance().isfav(o, 1, test);
        
    
     if(test.getOeuvrage_id()==0) {
          // FontImage.setMaterialIcon(b, FontImage.MATERIAL_FAVORITE);
            FavorisOService.getInstance().ajoutFAv(o,1);
            FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           b.setIcon(heartImage);
 } else {   
         FavorisOService.getInstance().suppFAv(o,1);
          FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s1);
           b.setIcon(heartImage);
 } 
 });

Container cntF = new Container(BoxLayout.xRight());
cntF.add(b);
add(cntF);   
add(iv);
 cnt1 = 
            LayeredLayout.encloseIn(
                BoxLayout.encloseXCenter(
                    BoxLayout.encloseY(
                            new Label("    Nom : "),
                            new Label("    Domaine : "),
                            new Label("    Description : "),
                            new Label("    Prix : "),
                            new Label("    Quantité : ")
                ),
                    BoxLayout.encloseY(
                          ta,
                            domaine,
                            desc,
                           likes,
                           comments
                ))
            );


/// ********************



 
 
    // ServiceRating serv = new ServiceRating();
           
           

 RatingOService.getInstance().israte(o, 1, rtest);
 int value = (int)rtest.getNote();             
 if (value!=-1)
                    showStarPickingForm(this,value, o);
                    else
                    showStarPickingForm(this,0, o);
//               
//            });
//            detaille.getToolbar().addCommandToRightBar("back", null, (ev) -> {
//                f.show();
//            });
            


// raef
Button breclam = new Button("Ajouter au panier");


add(cnt1);
         add(breclam);
//}

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
    
    private void bindButtonSelection1(Form previous,Resources res, Button b, Label arrow) {
       b.addActionListener(e -> {
            if(b.isSelected()) {
              
                 new ListOeuvre(previous, res).show();
            }
        });
    }
      
    private void bindButtonSelection2(Form previous,Resources res, Button b, Label arrow) {
       b.addActionListener(e -> {
            if(b.isSelected()) {
              
                new ListeFavoris(previous, res).show();
            }
        });
    }
    
    private void bindButtonSelection3(Form previous,Resources res, Button b, Label arrow) {
       b.addActionListener(e -> {
            if(b.isSelected()) {
            }
        });
    }
    // ******************** 
    
    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
    
    private Slider createStarRankSlider(int v, Oeuvre o) {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(2);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        if (v!=-1)
        {
            starRank.setProgress(v);
           // starRank.setEditable(false);
           
        }
        else starRank.setEditable(true);
        starRank.addActionListener(e -> {
        RatingOService.getInstance().israte(o, 1, rtest);
      int value = (int)rtest.getNote();             
    if (value!=-1)
        {
//           
            System.out.println(starRank.getProgress());
            r.modifiernote(o, 1,starRank.getProgress());
            
            
            
       //*******************
       
        }
              else {
            System.out.println(starRank.getProgress());
                r.ajoutnote(o,1,starRank.getProgress());
              }

        });
        return starRank;
    }
    private void showStarPickingForm(Form f,int v, Oeuvre o) {
        f.add(FlowLayout.encloseCenter(createStarRankSlider(v, o)));
    

    }
    
    
    
    //***********************
    
        
         private static BitMatrix generateMatrix(String data, int size) {
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
            return bitMatrix;
        } catch (WriterException ex) { } 
        return bitMatrix;
    }
        
    
     private static Image getImageFromBitMatrix(final int width, final int height, final BitMatrix qrCodeBits) {
        Image qrCodeImg = null;
        int fillColor = 0x000000; // White is the default fill color
        qrCodeImg = Image.createImage(width, height, fillColor);
        Graphics g = qrCodeImg.getGraphics();
        drawBarCode(g, 0, 0, width, height, qrCodeBits);
        return qrCodeImg;
    }
        private static void drawBarCode(Graphics g, int xOffset, int yOffset, int width, int height, final BitMatrix qrCodeBits) {
        // Set the line drawing color to black
         g.setColor(0x000000);
        for (int y = 0; y < height; y++) {
            int x = 0;
            while (x < width) {
                if (qrCodeBits.get(x, y)) {
                    int lineStartPoint = x;
                    int curEndPoint = x;
                    // True - means this pixel is black, go and find the end of this line
                    while (curEndPoint < width && qrCodeBits.get(curEndPoint, y)) {
                        curEndPoint++; // check next pixel
                    }
                    if (curEndPoint >= width) {
                        x = width - 1; // reached end of line
                    } else {
                        x = curEndPoint;
                    }
                    g.drawLine(lineStartPoint + xOffset,
                            y + yOffset,
                            x + xOffset,
                            y + yOffset);
                }
                x++; // Check next pixel
            }
        }
       
    }
}
