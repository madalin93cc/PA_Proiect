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
// clasa ce defineste o pereche
public class Pair {
    private int x, y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
        
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean change(int x, int y){
        this.x = x;
        this.y = y;
        return true;
    }
    public boolean equals(Object o1){
        Pair p=(Pair) o1;
        return (this.x==(p.getX())&& (this.y==(p.getY())));
    }
    public String toString(){
        return x + "-" + y;
    }
}
