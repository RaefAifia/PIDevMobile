/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author pc
 */
public class RatingO {
    private int rating_oeuvre_id ;
   
    private Oeuvre o ;        
    private float note;

    public RatingO() {
         }

    public int getRating_oeuvre_id() {
        return rating_oeuvre_id;
    }

    
    public Oeuvre getO() {
        return o;
    }

    public float getNote() {
        return note;
    }

    public void setRating_oeuvre_id(int rating_oeuvre_id) {
        this.rating_oeuvre_id = rating_oeuvre_id;
    }

   
    public void setO(Oeuvre o) {
        this.o = o;
    }

    public void setNote(float note) {
        this.note = note;
    }

    

  

    @Override
    public String toString() {
        return "RatingO{" + "rating_oeuvre_id=" + rating_oeuvre_id + ", o=" + o + ", note=" + note + '}';
    }
    
        
}
