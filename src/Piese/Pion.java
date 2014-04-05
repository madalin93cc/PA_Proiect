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
public class Pion extends Piesa{
    // miscari valide pentru pionii albi
    static ArrayList<Pair> miscari_alb = new ArrayList<Pair>(){{
            add(new Pair(-2,0));
            add(new Pair(-1,0));
            add(new Pair(-1,1));
            add(new Pair(-1,-1));
    }} // miscari valide pentru pionii albi
        , miscari_negru = new ArrayList<Pair>(){{
            add(new Pair(2,0));
            add(new Pair(1, 0));
            add(new Pair(1,1));
            add(new Pair(1,-1));
        }};
    // constructor ce primeste ca parametru pozitia (x,y)
    public Pion(int x, int y){
        setPozitie(new Pair(x, y));
        if(x == 2)
            setSide(2); // pentru negru
        else
            setSide(1); // pentru alb
        
    }
    public Pion(int x, int y, int side){ 
        setPozitie(new Pair(x, y));
        setSide(side);
    }
    // metoda ce intoarce true daca mutarea piesei pe pozitia (i,j) e valida si false altfel
    public boolean muta(Tabla t, int i, int j, boolean adversar){
        if (i<1 || i>8 || j<1 || j>8) return false; // daca mutarea iese din tabla intoarce false
        if (t.getTabla()[i][j] !=null) // daca pe pozitia (i,j) este o piesa de aceeasi culoare intoarce false
                if (t.getTabla()[i][j].getSide() == this.getSide()) 
                    return false;
        // pozitia actuala
        int x_actual = this.getPozitie().getX();
        int y_actual = this.getPozitie().getY();
        Pair x = new Pair(i-x_actual, j-y_actual);
        
        // se genereaza si se verifica toate miscarile unui pion in functie de culoarea acestuia
            if(this.getSide() == 1) // daca e alb
                if(Pion.miscari_alb.contains(x)){ // daca miscarea e valida 
                   if((x.getY() == 0) ){
                       if(t.getTabla()[i][j] == null){
                            if (x.getX() == -1) 
                                return true;
                            else 
                                if ( (x_actual == 7) && (t.getTabla()[6][y_actual] == null) ) 
                                    return true; 
                            return false;
                       }
                       else
                           return false;
                   }
                  
                   else{
                       if (t.getTabla()[i][j] != null){
                           return true;
                       }  
                       else {
                           if(adversar){
                               t.getTabla()[i+1][j] = null;
                               return true;
                           }
                           return false;
                       }
                       }
                }
                else
                    return false;
            else // daca e negru
                if(Pion.miscari_negru.contains(x)){ // daca miscarea e valida 
                   if((x.getY() == 0) ){
                       if(t.getTabla()[i][j] == null){
                            if (x.getX() == 1) 
                                return true;
                            else 
                                if ( (x_actual == 2) && (t.getTabla()[3][y_actual] == null) ) 
                                    return true; 
                            return false;
                       }
                       else
                           return false;
                   }
                  
                   else{
                       if (t.getTabla()[i][j] != null){
                           return true;
                       }  
                       else {
                           if(adversar){
                               t.getTabla()[i-1][j] = null;
                               return true;
                           }
                           return false;
                       }
                       }
                }
                else
                    return false;
    }
    // daca miscarea e valida se executa
    public boolean makeMove(Tabla t, int i, int j, boolean adversar){
        if (muta(t, i, j, adversar)){
            int x_actual = this.getPozitie().getX();
            int y_actual = this.getPozitie().getY();
            t.getTabla()[i][j] = t.getTabla()[x_actual][y_actual];
            t.getTabla()[x_actual][y_actual] = null;
            this.setPozitie(new Pair(i, j));
            // daca ajunge masina noastra cu pionul la promovare promoveaza automat regina.
            if(this.getSide() == 1 && this.getPozitie().getX() == 1)
                t.getTabla()[i][j] = new Regina(i, j, this.getSide());
            
            if(this.getSide() == 2 && this.getPozitie().getX() == 8)
                t.getTabla()[i][j] = new Regina(i, j, this.getSide());
            return true;
        }
        return false;
    }
    public boolean makeMove(Tabla t, int i, int j, char promovare){
        if (muta(t, i, j, false)){
            int x_actual = this.getPozitie().getX();
            int y_actual = this.getPozitie().getY();
            t.getTabla()[i][j] = t.getTabla()[x_actual][y_actual];
            t.getTabla()[x_actual][y_actual] = null;
            this.setPozitie(new Pair(i, j));
            switch(promovare){
                case 'q':{
                    t.getTabla()[i][j] = new Regina(i, j, this.getSide());
                    break;
                }
                case 'r':{
                    t.getTabla()[i][j] = new Tura(i, j, this.getSide());
                    break;
                }
                case 'b':{
                    t.getTabla()[i][j] = new Nebun(i, j, this.getSide());
                    break;
                }
                case 'k':{
                    t.getTabla()[i][j] = new Cal(i, j, this.getSide());
                    break;
                }
                
            }
            return true;
        }
        return false;
    }
        
    // metoda ce cloneaza piesa
    public Pion clona(){
        return new Pion(this.getPozitie().getX(),this.getPozitie().getY(), this.getSide());
    }
    public String toString(){
        String s=super.toString();
        return s+" Pion";
    }
}
