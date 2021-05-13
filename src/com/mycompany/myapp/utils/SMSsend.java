/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.util.Random;

public class SMSsend {
    
    
    
    // Find your Account Sid and Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC5bfcaf26eb3d2b773c58480a46dc05e7";
    public static final String AUTH_TOKEN = "9055e469193d7536ffa451bbddc66f2a";

    public static String SMSsend(String mail){
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        String hex = Integer.toHexString(rand_num);         
       
        String colorCode = "#"+hex;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21620246474"),
                new com.twilio.type.PhoneNumber("+17179644250"),
                "This is your verification code " + colorCode)
            .create();

        System.out.println(message.getSid());
        return colorCode;
    }
}
