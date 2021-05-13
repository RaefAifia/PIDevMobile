/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author asus
 */
public class Relations {
    private int follower_id, followee_id;
    
    
    public Relations(int follower_id, int followee_id){
        this.followee_id=followee_id;
        this.follower_id=follower_id;
    }

    
    public int getFollower_id() {
        return follower_id;
    }

    
    public void setFollower_id(int follower_id) {
        this.follower_id = follower_id;
    }

    
    public int getFollowee_id() {
        return followee_id;
    }

    
    public void setFollowee_id(int followee_id) {
        this.followee_id = followee_id;
    }
    
}
