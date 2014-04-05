package Piese;


import Piese.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George
 */
// clasa ce defineste tabla de joc
public class Tabla {
    Piesa[][] tabla = new Piesa[10][10];
    // construieste tabla nula
    public Tabla(int i){
        
    }
    // construieste tabla initiala
    public Tabla(){
        for(int i = 1; i <= 8; i++){
            tabla[2][i] = new Pion(2, i);
            tabla[7][i] = new Pion(7, i);
           
        }
        tabla[1][1] = new Tura(1, 1);
        tabla[1][8] = new Tura(1, 8);
        tabla[8][1] = new Tura(8, 1);
        tabla[8][8] = new Tura(8, 8);
        
        tabla[1][2] = new Cal(1, 2);
        tabla[1][7] = new Cal(1, 7);
        tabla[8][2] = new Cal(8, 2);
        tabla[8][7] = new Cal(8, 7);
        
        tabla[1][3] = new Nebun(1, 3);
        tabla[1][6] = new Nebun(1, 6);
        tabla[8][3] = new Nebun(8, 3);
        tabla[8][6] = new Nebun(8, 6);
        
        tabla[1][4] = new Regina(1, 4);
        tabla[8][4] = new Regina(8, 4);
        
        tabla[1][5] = new Rege(1, 5);
        tabla[8][5] = new Rege(8, 5);
        
        
    }
    // intoarce tabla
    public Piesa[][] getTabla(){
        return this.tabla;
    }
    // intoarce o copie a tablei
    public Tabla clona(){
        Tabla t = new Tabla(1);
        for (int i = 0; i <= 9; i++){
            for (int j = 0; j <= 9; j++){
                if ( this.tabla[i][j] != null)  
                    t.tabla[i][j] = this.tabla[i][j].clona();
            }
        }
        return t;
    }
    public String toString(){
        String s = "";
        for(int i = 0; i<=9; i++){
            
            for(int j = 0; j<=9;j++){
                s = s + tabla[i][j] + "\t";
            }
            s = s + "\n";
        }
        return s;
    }
}