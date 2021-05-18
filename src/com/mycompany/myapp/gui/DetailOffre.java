/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import entities.Offre;


/**
 *
 * @author pc
 */
public class DetailOffre extends BaseForm{
    Label comments ;
       Form detaille;
int i = UserService.getCurrentUser().getUser_id();
Container cnt1;

     public DetailOffre(Form previous, Resources res , Offre o) 
     {  
super("détail d'oeuvre", BoxLayout.y());
Toolbar tb = new Toolbar(true);  
        setToolbar(tb);
       // getTitleArea().setUIID("Container");
        //setTitle("Liste des oeuvres");
        getContentPane().setScrollVisible(false);
        
    // super.addSideMenu(res);
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
       Label dat = new Label(o.getDate() , "Label");
      
  
 cnt1 = 
            LayeredLayout.encloseIn(
                BoxLayout.encloseXCenter(
                    BoxLayout.encloseY(
                            new Label("    Nom : "),
                            new Label("    Description : "),
                           new Label("    Date : ")
                            
                ),
                    BoxLayout.encloseY(
                          ta,
                            desc,
                        dat
                ))
            );
         
String data = "Monsieur/Madame :"+i.getNom()+"\n a gagné l'offre "+o.getNom()+"\n"+o.getDescription()+"\n Veuillez en profiter avant : \n"+o.getDate() ;
int sizecode = 300;
Button breclam = new Button("utiliser cet offre");
breclam.addActionListener( e -> {
              
       

        // encode
        BitMatrix bitMatrix = generateMatrix(data, sizecode);
     
        // write in a file
        Image qr = getImageFromBitMatrix(300, 300, bitMatrix);
        ImageViewer qv = new ImageViewer();
        qv.setImage(qr);
            System.out.println("laaaaaaa"+qr.toString());
            //Image a = new Image (res.getImage("dog.jpg"));   
         int sizer = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        Image img = res.getImage("dog.jpg");
        if(img.getHeight() < sizer) {
            img = img.scaledHeight(sizer);
        }
        if(img.getHeight() > Display.getInstance().getDisplayHeight()) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight());
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label nom = new Label(o.getNom(), "PrixLabel");
        Label des = new Label(o.getDescription()+"   ", "NewsTopLine");
        Label det = new Label("Valable jusqu'à", "Label");
        Label overlay = new Label(o.getDate(), "Label");
         
        
        Container page2 = 
            LayeredLayout.encloseIn(
                
                
                    BoxLayout.encloseY(
                            new Label(" "),
                             new Label(""),
                            new Label(" "),
                             new Label(""),
                             new Label(""),
                             new Label("")         
                ),
                    BoxLayout.encloseY(
                            new Label(" "),
                             new Label(""),
                            new Label(" "),
                             new Label(""),
                             new Label(""),
                             new Label("")
                )
            );
      
         
        Container page1 = 
            LayeredLayout.encloseIn(
                 image,
                  BorderLayout.east(BoxLayout.encloseY(
               
                          
                          new Label(""),
                          new Label(""),
                          new Label(""),
                          new Label(""),
                          new Label(""),
                          new Label(""),
                          nom,
                         BoxLayout.encloseX(des , new Label(""))
                )),
                BorderLayout.south(
                     BoxLayout.encloseXRight( 
                              BoxLayout.encloseY(
                             new Label(""),
                             new Label(""),
                             new Label(""),
                             new Label(""),
                             new Label(""),
                             new Label(""),
                             new Label(""),
                             new Label(""),
                             new Label(""),
                             new Label(""),
                               det,
                               overlay),
                     BoxLayout.encloseY(
                             new Label(qr),
                              new Label(""),
                             new Label(""),
                             new Label(""),
                             new Label("")
                         ) ))
            );
        detaille = new Form();
        detaille.add(page2);
          detaille.add(page1);
            detaille.show();
}
       );


add(cnt1);
add(breclam);

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