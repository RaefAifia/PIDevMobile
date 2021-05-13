package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.gui.Profil;
import com.mycompany.myapp.services.UserService;

import com.sun.scenario.animation.shared.InfiniteClipEnvelope;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUp extends BaseForm {

    public SignUp(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.ANY);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.ANY);
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        TextField adresse = new TextField("", "Adresse", 20, TextField.ANY);
        TextField numTel = new TextField("", "numéro", 20, TextField.NUMERIC);

        TextField bio = new TextField("", "Bio", 20, TextField.ANY);


        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        adresse.setSingleLineTextArea(false);
        bio.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                 new FloatingHint(nom),
                createLineSeparator(),
                 new FloatingHint(prenom),
                createLineSeparator(),
                 new FloatingHint(adresse),
                createLineSeparator(), 
                new FloatingHint(numTel),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((evt) -> {
       
        try{
            if(nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty()|| username.getText().isEmpty()){
                Dialog.show("veuillez remplir tous les champs", "","annuler", "ok");
            }
           
            else{
                 if(!password.getText().equals(confirmPassword)){
                    Dialog.show("mots de passes non identiques", "","annuler", "ok");
                }
                else {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                String image ="";
                User u = new User(nom.getText(), prenom.getText(), username.getText(), adresse.getText(),Integer.parseInt(numTel.getText()), "hello", image);
                UserService.getInstance().addUser(u);
                UserService.getInstance().DetailUser(u.getUser_id());
                UserService.setCurrentUser(u);
                new Profil(res).show();
//                iDialog.dispose();
//                refreshTheme();
                }
            }
                    
        }catch(Exception ex){
            ex.printStackTrace();
        }         
        });
    
    
}
}
