package com.codename1.demos.codescan;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ext.codescan.CodeScanner;
import com.codename1.ext.codescan.ScanResult;

import java.io.IOException;


public class CodescanDemo {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Pro users - uncomment this code to get crash reports sent to you automatically
        /*Display.getInstance().addEdtErrorHandler(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                evt.consume();
                Log.p("Exception in AppName version " + Display.getInstance().getProperty("AppVersion", "Unknown"));
                Log.p("OS " + Display.getInstance().getPlatformName());
                Log.p("Error " + evt.getSource());
                Log.p("Current Form " + Display.getInstance().getCurrent().getName());
                Log.e((Throwable)evt.getSource());
                Log.sendLog();
            }
        });*/
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        final Form hi = new Form("Codescan Demo");
        hi.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        ButtonGroup bg = new ButtonGroup();
        final RadioButton qr = new RadioButton("QR Code");
        final RadioButton bar = new RadioButton("Bar Code");
        bg.add(qr);
        bg.add(bar);
        hi.addComponent(new Label("Code Type"));
        hi.addComponent(qr);
        hi.addComponent(bar);
        
        Button scanBtn = new Button("Scan Code");
        scanBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                final Container cnt = hi;

                if(qr.isSelected()){
                    CodeScanner.getInstance().scanQRCode(new ScanResult() {

                        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
                            //barCode.setText("Bar: " + contents);
                            cnt.addComponent(new Label(contents));
                            cnt.revalidate();
                        }

                        public void scanCanceled() {
                            System.out.println("cancelled");
                        }

                        public void scanError(int errorCode, String message) {
                            System.out.println("err " + message);
                        }
                    });
                }else{
                    CodeScanner.getInstance().scanBarCode(new ScanResult() {

                        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
                            //barCode.setText("Bar: " + contents);
                            cnt.addComponent(new Label(contents));
                            cnt.revalidate();
                        }

                        public void scanCanceled() {
                            System.out.println("cancelled");
                        }

                        public void scanError(int errorCode, String message) {
                            System.out.println("err " + message);
                        }
                    });        
                }
            }
            
        });
        if (CodeScanner.isSupported()) {
            hi.addComponent(scanBtn);
        } else {
            hi.addComponent(new SpanLabel("Sorry.  Codescanner not supported on this platform"));
        }
        hi.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }

}
