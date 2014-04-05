/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Piese;

import java.util.ArrayList;

/**
 *
 * @author Colezea
 */
public class Cal extends Piesa{
    static ArrayList<Pair> miscari_cal = new ArrayList<Pair>(){{ //miscari valide ale calului
            add(new Pair(2,1));
            add(new Pair(1,2));
            add(new Pair(2,-1));
            add(new Pair(1,-2));
            add(new Pair(-1,2));
            add(new Pair(-2,1));
            add(new Pair(-2,-1));
            add(new Pair(-1,-2));
    }};
    // constructor ce primeste ca parametru pozitia (x,y)
    public Cal(int x, int y){ 
        setPozitie(new Pair(x, y));
        if(x == 1)
            setSide(2);
        else
            setSide(1);
    }
    public Cal(int x, int y, int side){ 
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
        // pereche formata din diferenta dintre pozitia unde vrem sa mutam si pozitia actuala 
        Pair x = new Pair(i-x_actual, j-y_actual);
        if(Cal.miscari_cal.contains(x)){    // miscarea e posibila
            return true;
        }
        else
            return false;  //miscare invalida

    }
    // daca miscarea e valida se executa
    public boolean makeMove(Tabla t, int i, int j, boolean q){
        if (muta(t, i, j,q)){
            int x_actual = this.getPozitie().getX();
            int y_actual = this.getPozitie().getY();
            t.getTabla()[i][j] = t.getTabla()[x_actual][y_actual];
            t.getTabla()[x_actual][y_actual] = null;
            this.setPozitie(new Pair(i, j));
            return true;
        }
        return false;
    }
    // metoda ce cloneaza piesa
    public Cal clona(){
        return new Cal(this.getPozitie().getX(),this.getPozitie().getY(), this.getSide());
    }
    public String toString(){
        String s=super.toString();
        return s+" Cal";
    }
}
