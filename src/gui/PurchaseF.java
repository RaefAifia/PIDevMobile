/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package gui;
////
/////**
//// *
//// * @author HELA
//// */
////import com.codename1.payment.Purchase;
//////import com.codename1.braintree.impl.BraintreeNative;
//////import com.codename1.braintree.impl.BraintreePaymentCallback;
////import com.codename1.components.InfiniteProgress;
////import com.codename1.components.ToastBar;
////import com.codename1.io.ConnectionRequest;
////import com.codename1.io.Log;
////import com.codename1.io.NetworkManager;
////import com.codename1.io.Storage;
////import com.codename1.javascript.JSFunction;
////import com.codename1.javascript.JSObject;
////import com.codename1.javascript.JavascriptContext;
////import com.codename1.payment.PurchaseCallback;
////import com.codename1.system.NativeLookup;
////import com.codename1.ui.BrowserComponent;
////import com.codename1.ui.Button;
////import com.codename1.ui.Component;
////import com.codename1.ui.Container;
////import com.codename1.ui.Dialog;
////import com.codename1.ui.Display;
////import com.codename1.ui.FontImage;
////import com.codename1.ui.Form;
////import com.codename1.ui.Image;
////import com.codename1.ui.Label;
////import com.codename1.ui.events.ActionEvent;
////import com.codename1.ui.events.ActionListener;
////import com.codename1.ui.layouts.BorderLayout;
////import com.codename1.ui.layouts.BoxLayout;
////import com.codename1.ui.layouts.FlowLayout;
////import com.codename1.ui.util.Resources;
////import entities.Formation;
////
/////**
//// * It's not good practice to call the native interface directly, this class 
//// * hides some of the low level implementation details if any.
//// */
////public class PurchaseF extends Demo{
////    private static final String[] ITEM_IDS = {
////        "ITEM001",
////        "ITEM002",
////        "ITEM003"
////    };
////    private static final String[] ITEM_NAMES = {
////        "Give Us A Little Money",
////        "Give Us Some More Money",
////        "Thanks!!!"
////    };
////    @Override
////    public String getDisplayName() {
////         return "Purchase";
////    }
//////
//////    @Override
//////    public Image getDemoIcon() {
//////       
//////    }
//// 
////    
////    @Override
////    public Container createDemo(Formation f) {
////         final Container purchaseDemo = new Container(new BoxLayout(BoxLayout.Y_AXIS));
////        final Purchase p = Purchase.getInAppPurchase();
////        
////        if(p != null) {
////            if(p.isManualPaymentSupported()) {
////                purchaseDemo.addComponent(new Label("Manual Payment Mode"));
////               // final TextField tf = new TextField("100");
////              
////               // tf.setHint("Send us money, thanks");
////                Button sendMoney = new Button("Send Us Money");
////                sendMoney.addActionListener(new ActionListener() {
////                    public void actionPerformed(ActionEvent evt) {
////                        p.pay(f.getPrix(), "DT");
////                    }
////                });
////               // purchaseDemo.addComponent(tf);
////                purchaseDemo.addComponent(sendMoney);
////            } 
////            if(p.isManualPaymentSupported()) {
////                purchaseDemo.addComponent(new Label("Managed Payment Mode"));
////                for(int iter = 0 ; iter < ITEM_NAMES.length ; iter++) {
////                    Button buy = new Button(ITEM_NAMES[iter]);
////                    final String id = ITEM_IDS[iter];
////                    buy.addActionListener(new ActionListener() {
////                        public void actionPerformed(ActionEvent evt) {
////                            p.purchase(id);
////                        }
////                    });
////                    purchaseDemo.addComponent(buy);
////                  //  System.out.println(p.isManualPaymentSupported());
////                }
////            } 
////        } else {
////            purchaseDemo.addComponent(new Label("Payment unsupported on this device"));
////        }
////        
////        return purchaseDemo;
////    }
////
////    @Override
////    public Container createDemo() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
////    }
//////    public void pay (){
//////       Form hi = new Form("Hi World");
//////        Button buyWorld = new Button("Buy World");
//////        buyWorld.addActionListener(e->{
//////            if (Purchase.getInAppPurchase().wasPurchased(SKU_WORLD)) {
//////                Dialog.show("Can't Buy It", "You already Own It", "OK", null);
//////            } else {
//////                Purchase.getInAppPurchase().purchase(SKU_WORLD);
//////            }
//////        });
//////
//////        hi.addComponent(buyWorld);
//////        hi.show();
//////    }
//////    
//////    public static final String SKU_WORLD = "com.codename1.world";
//////    
//////    static boolean flag;
////// private static final String NUM_WORLDS_KEY = "NUM_WORLDS.dat";
//////  
//////    private Form current;
//////    private Resources theme;
//////
//////    
//////    @Override
//////    public void itemPurchased(String sku) {
//////         ToastBar.showMessage("Thanks.  You now own the world", FontImage.MATERIAL_THUMB_UP);
//////    }
//////
//////    @Override
//////    public void itemPurchaseError(String sku, String errorMessage) {
//////    ToastBar.showErrorMessage("Failure occurred: "+errorMessage);
//////    }
//////
//////    @Override
//////    public void itemRefunded(String sku) {
//////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////    }
//////
//////    @Override
//////    public void subscriptionStarted(String sku) {
//////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////    }
//////
//////    @Override
//////    public void subscriptionCanceled(String sku) {
//////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////    }
//////
//////    @Override
//////    public void paymentFailed(String paymentCode, String failureReason) {
//////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////    }
//////
//////    @Override
//////    public void paymentSucceeded(String paymentCode, double amount, String currency) {
//////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//////    }
//////    
//////    public static interface Callback {
//////        public String fetchToken();
//////        public void onPurchaseSuccess(String nonce);
//////        public void onPurchaseFail(String errorMessage);
//////        public void onPurchaseCancel();
//////    }
////    
////
//////}
//
//
//
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.gui;
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.BinaryBitmap;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatReader;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.NotFoundException;
//import com.google.zxing.Result;
//import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.common.HybridBinarizer;
//import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import javax.imageio.ImageIO;
//
//public class QRCode  {
//public static void main(String[] args) throws WriterException, IOException,
//			NotFoundException {
//		String qrCodeData = "Hello World!";
//		String filePath = "QRCode.png";
//		String charset = "UTF-8"; // or "ISO-8859-1"
//		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
//		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//
//		createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
//		System.out.println("QR Code image created successfully!");
//
//		System.out.println("Data read from QR Code: "
//				+ readQRCode(filePath, charset, hintMap));
//
//	}
//
//	public static void createQRCode(String qrCodeData, String filePath,
//			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
//			throws WriterException, IOException {
//		BitMatrix matrix = new MultiFormatWriter().encode(
//				new String(qrCodeData.getBytes(charset), charset),
//				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
//		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
//				.lastIndexOf('.') + 1), new File(filePath));
//	}
//
//	public static String readQRCode(String filePath, String charset, Map hintMap)
//			throws FileNotFoundException, IOException, NotFoundException {
//		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
//				new BufferedImageLuminanceSource(
//						ImageIO.read(new FileInputStream(filePath)))));
//		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
//				hintMap);
//		return qrCodeResult.getText();
//	}
//}
