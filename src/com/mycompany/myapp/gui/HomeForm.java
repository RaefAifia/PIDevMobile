/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author asus
 */

public class HomeForm extends Form{
    Form current;
    private Resources theme;
    public HomeForm(){
     current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

      
        Button btnLogin = new Button("Login");
        
       btnLogin.addActionListener(e -> new login(theme).show());
      
        addAll(btnLogin);
}

}
