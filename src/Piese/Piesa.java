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
// clasa ce defineste o piese 
// si este mostenita de toate tipurile de piesa
public class Piesa { 
    private Pair pozitie; // pozitia pe tabla
    private int side; // culoarea : 2-negru 1-alb
    public boolean miscat = false;
    public int getSide(){
        return side;
    }
    public boolean setSide(int side){
        this.side=side;
        return true;
    }
    public Pair getPozitie(){
        return pozitie;
    }
    public boolean setPozitie(Pair x){
        this.pozitie = x;
        
        return true;
    }
    // functii ce vor fi suprascrise in clasele derivate
    public boolean muta(Tabla t, int i, int j, boolean q){
        return true;
    }
    public boolean makeMove(Tabla t, int i, int j, boolean q){
        return true;
    }
    public boolean makeMove(Tabla t, int i, int j, char q){
        return true;
    }
    public Piesa clona(){
        return new Piesa();
    }
    public String toString(){
        return side + " " + pozitie;
    }
}
