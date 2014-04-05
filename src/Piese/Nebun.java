/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Piese;

/**
 *
 * @author George
 */
public class Nebun extends Piesa{
    // constructor ce primeste ca parametru pozitia (x,y)
    public Nebun(int x, int y){
        setPozitie(new Pair(x, y));
        if(x == 1)
            setSide(2);
        else
            setSide(1);
    }
    public Nebun(int x, int y, int side){ 
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
        // se genereaza si se verifica daca sunt valide toate miscarile unui nebun
        if ((Math.abs(i-x_actual)) == Math.abs(j-y_actual)){
            int directie=0;
            if ((x_actual < i) && (y_actual > j)) directie = 1;
            else if ((x_actual > i) && (y_actual > j)) directie = 2;
                else if ((x_actual > i) && (y_actual < j)) directie = 3;
                    else if ((x_actual < i) && (y_actual < j)) directie = 4;
            boolean ok = true;
            switch (directie){
                case 1:{
                    for (int k = 1;k<Math.abs(i-x_actual); k++){
                        if (t.getTabla()[x_actual + k][y_actual - k] != null) 
                            ok = false;
                    }
                    break;
                }
                case 2:{
                    for (int k = 1;k<Math.abs(i-x_actual); k++){
                        if (t.getTabla()[x_actual - k][y_actual - k] != null) 
                            ok = false;
                    }
                    break;
                }
                case 3:{
                    for (int k = 1;k<Math.abs(i-x_actual); k++){
                        if (t.getTabla()[x_actual - k][y_actual + k] != null) 
                            ok = false;
                    }
                    break;
                }
                case 4:{
                    for (int k = 1;k<Math.abs(i-x_actual); k++){
                        if (t.getTabla()[x_actual + k][y_actual + k] != null) 
                            ok = false;
                    }
                    break;
                }
            }
            if (ok){
                return true;
            }
            else 
                return false;
        }
        else 
            return false;
    }
    // metoda ce cloneaza piesa
    public Nebun clona(){
        return new Nebun(this.getPozitie().getX(),this.getPozitie().getY(), this.getSide());
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
    public String toString(){
        String s=super.toString();
        return s+" Nebun";
    }
}
