/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author pc
 */
public class Home extends Form{
     Form current;
   
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public Home( Resources theme) {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Add Task");
        Button btnListTasks = new Button("List Tasks");

        btnListTasks.addActionListener(e -> new ListOeuvre(current, theme).show());
        btnAddTask.addActionListener(e -> new MesOffres(current, theme).show());
        addAll(btnAddTask, btnListTasks);

    }
    
}
