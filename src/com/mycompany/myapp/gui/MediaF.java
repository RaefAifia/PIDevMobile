///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//
//import com.codename1.capture.Capture;
//import com.codename1.components.InfiniteProgress;
//import com.codename1.components.MediaPlayer;
//import com.codename1.components.MultiButton;
//import com.codename1.components.ToastBar;
//import com.codename1.io.*;
//import com.codename1.media.Media;
//import com.codename1.media.MediaManager;
//import com.codename1.ui.*;
//import com.codename1.ui.events.ActionEvent;
//import com.codename1.ui.events.ActionListener;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import com.codename1.ui.plaf.Style;
//import com.codename1.ui.plaf.UIManager;
//
//import java.io.IOException;
//
//import static com.codename1.ui.CN.*;
//import static com.codename1.ui.util.Resources.getGlobalResources;
//import entities.Cours;
//
///**
// * Class that demonstrate the usage of the MediaPlayer and the MediaManager components.
// * The MediaPlayer allows you to control video playback. To use the MediaPlayer we need to first load the Media object from the MediaManager.
// * The MediaManager is the core class responsible for media interaction in Codename One.
// *
// * @author Sergey Gerashenko.
// */
//public class MediaF extends Demo {
//    private static final String CAPTURED_VIDEO = FileSystemStorage.getInstance().getAppHomePath() + "captured.mp4";
//    private static final String DOWNLOADED_VIDEO = FileSystemStorage.getInstance().getAppHomePath() + "http://localhost/PIDevWEB-main/public/images1/";
//    
//    public MediaF (Form parentForm) {
//        init("Media", getGlobalResources(). getImage("media-demo-icon.png"), parentForm,
//                "https://github.com/codenameone/KitchenSink/blob/master/src/com/codename1/demos/kitchen/MediaDemo.java");
//    }
//
//    @Override
//    public Container createContentPane(){
//       Container demoContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS), "VideoContainer");
////      
////        Style iconStyle = UIManager.getInstance().getComponentStyle("MediaIcon");
////        Component downloadButton = createVideoComponent("Hello (Download)", "Download to FileSystem", FontImage.createMaterial(FontImage.MATERIAL_ARROW_CIRCLE_DOWN, iconStyle),
////                                        e-> {
////                                            if (!existsInFileSystem(DOWNLOADED_VIDEO)){
////                                                downloadFile("https://www.codenameone.com/files/hello-codenameone.mp4");
////                                            }else{
////                                                ToastBar.showErrorMessage("File is already downloaded", FontImage.MATERIAL_SYSTEM_UPDATE);
////                                            }
////                                        });
////        
////        Component playOfflineButton = createVideoComponent("Hello (Offline)", "Play from FileSystem", FontImage.createMaterial(FontImage.MATERIAL_PLAY_CIRCLE_FILLED, iconStyle),
////                                        e-> {
////                                            if (existsInFileSystem(DOWNLOADED_VIDEO)){
////                                                playVideoOnNewForm(DOWNLOADED_VIDEO, demoContainer.getComponentForm());
////                                            }else{
////                                                ToastBar.showErrorMessage("For playing the video in offline mode you should first to download the video");
////                                            }
////                                        });
////                                            
////        
////        Component playOnlineButton = createVideoComponent("Hello (Online)", "Play thru http", FontImage.createMaterial(FontImage.MATERIAL_PLAY_CIRCLE_FILLED, iconStyle),
////                                        e -> playVideoOnNewForm("https://www.codenameone.com/files/hello-codenameone.mp4", demoContainer.getComponentForm()));
////        
////        Component captureVideoButton = createVideoComponent("Capture", "Record video and save to FileSystem", FontImage.createMaterial(FontImage.MATERIAL_VIDEOCAM, iconStyle),
////                                        e-> {
////                                            String capturedVideo = Capture.captureVideo();
////                                            if(capturedVideo != null){
////                                                try{
////                                                    Util.copy(openFileInputStream(capturedVideo), openFileOutputStream(CAPTURED_VIDEO));
////                                                }catch(IOException err) {
////                                                    Log.e(err);
////                                                }
////                                            }
////                                        });
////        
////        Component playCaptured = createVideoComponent("Play", "Play captured video", FontImage.createMaterial(FontImage.MATERIAL_PLAY_CIRCLE_FILLED, iconStyle),
////                                        e-> {
////                                            if (existsInFileSystem(CAPTURED_VIDEO)){
////                                                playVideoOnNewForm(CAPTURED_VIDEO, demoContainer.getComponentForm());
////                                            }
////                                            else{
////                                                ToastBar.showErrorMessage("you should to capture video first");
////                                            }
////                                        });
////  
////        demoContainer.addAll(downloadButton, playOfflineButton, playOnlineButton, captureVideoButton, playCaptured);
//       return demoContainer;
//    }
//
//    public void playVideoOnNewForm(String fileURI, Form parentForm) {
//        System.out.println("loula");
//        Form videoForm = new Form("Video", new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
//        videoForm.getContentPane().setUIID("ComponentDemoContainer");
//
//        Toolbar toolbar = videoForm.getToolbar();
//        toolbar.setUIID("DemoToolbar");
//        toolbar.getTitleComponent().setUIID("DemoTitle");
//        
//        videoForm.add(CENTER, new InfiniteProgress());
//        Command backCommand = Command.create("", FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("DemoTitleCommand")),
//                    e-> parentForm.showBack());
//        toolbar.setBackCommand(backCommand);
//        System.out.println("thenya");
//        videoForm.show();
//        scheduleBackgroundTask(()-> {
//            try{
//                System.out.println("theltha");
//                Media video = MediaManager.createMedia(fileURI, true);
//              
//    //  Media vid=   MediaManager.createMedia( fileURI.codePoints(), fileURI);
//                System.out.println(fileURI);
//                if(video != null){
//                    System.out.println("hedhi duree"+video.getDuration());
//                    video.prepare();
//                    System.out.println("rab3a");
//                    video.setNativePlayerMode(!isDesktop() && !isSimulator());
//                    MediaPlayer player = new MediaPlayer(video);
//                    player.setAutoplay(true);
//
//                    callSerially(()->{
//                        videoForm.removeAll();
//                        videoForm.setLayout(new BorderLayout());
//                        videoForm.add(BorderLayout.CENTER, player);
//                        videoForm.revalidate();
//                    });
//                }
//            }catch(IOException error){
//                Log.e(error);
//                 error.printStackTrace();
//    System.exit(1);
//                ToastBar.showErrorMessage("Error loading video");
//            }
//        });
//       // return videoForm;
//    }
//    
//    public void downloadFile(String url,Cours c){
//        ConnectionRequest cr = new ConnectionRequest();
//        cr.setPost(false);
//        cr.setFailSilently(true);
//        cr.setReadResponseForErrors(false);
//        cr.setDuplicateSupported(true);
//        //hedha lezem nzidou naarash win 
//          url =  FileSystemStorage.getInstance().getAppHomePath();
//        cr.setUrl(url);
//           System.out.println("ey ");
//        //   Util.downloadUrlToFile("http://localhost/PIDevWEB-main/public/images1/"+c.getCours_id(), fileName, true);
//        
//     //   cr.setDestinationFile("http://localhost/PIDevWEB-main/public/images1/"+c.getMedia());
//        cr.addResponseListener(e->{});
//        ToastBar.showConnectionProgress("Downloading", cr, null, null);
//        NetworkManager.getInstance().addToQueue(cr);
//    }
//    
//    public Component createVideoComponent(String firstLine, String secondLine, Image icon, ActionListener<ActionEvent> actionListener){
//        MultiButton videoComponent = new MultiButton(firstLine);
//        videoComponent.setTextLine2(secondLine);
//        videoComponent.setUIID("VideoComponent");
//        videoComponent.setIcon(icon);
//        videoComponent.setIconPosition("East");
//        videoComponent.addActionListener(actionListener);
//        videoComponent.setUIIDLine1("MediaComponentLine1");
//        videoComponent.setUIIDLine2("MediaComponentLine2");
//        return videoComponent;
//    }
//
// 
//}
