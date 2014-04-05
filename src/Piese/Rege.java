/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Piese;

import java.util.ArrayList;

/**
 *
 * @author George
 */
public class Rege extends Piesa{
    static ArrayList<Pair> miscari = new ArrayList<Pair>(){{
        add(new Pair(0,1));
        add(new Pair(1,0));
        add(new Pair(1,1));
        add(new Pair(-1,0));
        add(new Pair(-1,1));
        add(new Pair(-1,-1));
        add(new Pair(0,-1));
        add(new Pair(1,-1));
        add(new Pair(0,-2));
        add(new Pair(0,2));
    }};
   // public boolean miscat = false;
    // constructor ce primeste ca parametru pozitia (x,y)
    public Rege(int x, int y){
        setPozitie(new Pair(x, y));
        if(x == 1)
            setSide(2);
        else
            setSide(1);
    }
    public Rege(int x, int y, int side){ 
        setPozitie(new Pair(x, y));
        setSide(side);
    }
    // metoda ce intoarce true daca mutarea piesei pe pozitia (i,j) e valida si false altfel
    public boolean muta(Tabla t, int i, int j, boolean q){
        if (i<1 || i>8 || j<1 || j>8) return false; // daca mutarea iese din tabla intoarce false
        if (t.getTabla()[i][j] !=null) // daca pe pozitia (i,j) este o piesa de aceeasi culoare intoarce false
                if (t.getTabla()[i][j].getSide() == this.getSide()) 
                    return false;
        // pozitia actuala
        int x_actual = this.getPozitie().getX();
        int y_actual = this.getPozitie().getY();
        Pair x = new Pair(i-x_actual, j-y_actual);
        if(Rege.miscari.contains(x)){    // miscarea e valida
            if ( (x.getY() != -2) && (x.getY() != 2) ){ // nu e rocada
                return true; // miscarea e valida
            }
            if ( ( (x_actual == 1) || (x_actual == 8) ) && (y_actual == 5) && (this.miscat == false)){ // regele e pe pozitia initiala
                if (x.getY() == -2){ //rocada la stanga
                    if (t.getTabla()[x_actual][1] instanceof Tura && !(t.getTabla()[x_actual][1].miscat)){
                        if ( (t.getTabla()[x_actual][2] == null) && // daca intre rege si tura nu se afla nimic
                             (t.getTabla()[x_actual][3] == null) &&
                             (t.getTabla()[x_actual][4] == null)  ){
                            return true;
                        }
                        else return false; // daca sunt piese intre ele - invalid
                    }
                    else return false; // tura nu e pe pozitia initiala
                }
                else { //rocada la dreapta
                    if (t.getTabla()[x_actual][8] instanceof Tura && !(t.getTabla()[x_actual][8].miscat)){
                        if ( (t.getTabla()[x_actual][6] == null) && // daca intre rege si tura nu se afla nimic
                             (t.getTabla()[x_actual][7] == null) ){
                            return true;
                        }
                        else return false; // daca sunt piese intre ele - invalid
                    }
                    else return false; // tura nu e pe pozitia initiala
                } 
            }
            else return false; // regele nu e pe pozitia initiala
        }
        else
            return false;  //miscare invalida
    }
    // daca miscarea e valida se executa
    public boolean makeMove(Tabla t, int i, int j, boolean q){
        if (muta(t, i, j, q)){
            int x_actual = this.getPozitie().getX();
            int y_actual = this.getPozitie().getY();
            Pair x = new Pair(i-x_actual, j-y_actual);
            if (x.getY() == 2) {
                // mut tura
                t.getTabla()[x_actual][6] = t.getTabla()[x_actual][8];
                t.getTabla()[x_actual][8] = null;
                t.getTabla()[x_actual][6].setPozitie(new Pair(x_actual, 6));
            }
            if (x.getY() == -2){
                // mut tura
                t.getTabla()[x_actual][4] = t.getTabla()[x_actual][1];
                t.getTabla()[x_actual][1] = null;
                t.getTabla()[x_actual][4].setPozitie(new Pair(x_actual, 4));
            }
            t.getTabla()[i][j] = t.getTabla()[x_actual][y_actual];
            t.getTabla()[x_actual][y_actual] = null;
            this.setPozitie(new Pair(i, j));
            this.miscat = true;
            return true;
        }
        return false; 
    }
    // metoda ce cloneaza piesa
    public Rege clona(){
        return new Rege(this.getPozitie().getX(),this.getPozitie().getY(), this.getSide());
    }
    public String toString(){
        String s=super.toString();
        return s+" Rege";
    }
}
