/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Mega-PC
 */
public class Panier {
    private int id;
    private Commande cmd;
    private Oeuvre oeuv;
    private int quantite;

    public Panier() {
    }

    public Panier(int id, Commande cmd, Oeuvre oeuv, int quantite) {
        this.id = id;
        this.cmd = cmd;
        this.oeuv = oeuv;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

    public Oeuvre getOeuv() {
        return oeuv;
    }

    public void setOeuv(Oeuvre oeuv) {
        this.oeuv = oeuv;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Panier other = (Panier) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.cmd, other.cmd)) {
            return false;
        }
        if (!Objects.equals(this.oeuv, other.oeuv)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", cmd=" + cmd + ", oeuv=" + oeuv + ", quantite=" + quantite + '}';
    }
    
    
}
