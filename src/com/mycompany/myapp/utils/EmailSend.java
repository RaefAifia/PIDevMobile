/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * https://www.google.com/settings/security/lesssecureapps
 */

package com.mycompany.myapp.utils;

import com.email.durgesh.Email;
import java.io.UnsupportedEncodingException;
import javax.mail.*;

import java.util.Random;

public class EmailSend {
    //private static Email email = new Email("mariem.arif@esprit.tn", "203@JFT1500");
    private static String colorCode;
    
        
        //System.out.println(colorCode);

    public static String SendMail(String mail) throws MessagingException, UnsupportedEncodingException{
        Email email = new Email("mariem.arif@esprit.tn", "203JFTJFT1500");
   
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        String hex = Integer.toHexString(rand_num);         
        //String colorCode = String.format("#%06x", rand_num);
        //String colorCode = "hi";
        String colorCode = "#"+hex;
        email.setFrom("mariem.arif@esprit.tn", "Admin");
        email.setSubject("Mail de validation");
        email.setContent("<h1> voici votre code de confirmation <h1> "+colorCode , "text/html");
        email.addRecipient("mariema020@gmail.com");
        email.send();
        return colorCode;
    }
    
    
    
    
}
