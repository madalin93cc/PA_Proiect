/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Piese;

/**
 *
 * @author Colezea
 */
// clasa ce defineste o mutare si contine 2 perechi 
// una cu pozitia de unde se muta
// cealalta cu pozitia unde se muta
public class Mutare {
    private Pair from, where; // cele doua perechi
    public Mutare(Pair from, Pair where){
        this.from = from;
        this.where = where;
    }
    public Mutare(){
        this.from = new Pair(0,0);
        this.where = new Pair(0,0);
    }
    public Pair getFrom(){
        return this.from;
    }
    public Pair getWhere(){
        return this.where;
    }
    public String toString(){
        return from.toString() + "->" + where.toString();
    }
}
