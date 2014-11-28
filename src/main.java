
import Piese.Mutare;
import Piese.Pair;
import Piese.Tabla;
import Piese.Piesa;
import Piese.Pion;
import Piese.Rege;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author George
 */

public class main {
    
    public static void main(String args[]) throws IOException{
        int side_on_move = 1; //cine muta : 1 - alb 2 - negru
        int mode;
        int culoare = 2; // culoarea cu care joaca motorul
        boolean adv = true;
        String in=new String();
        Scanner scanner=new Scanner(System.in); 
        BufferedWriter f=new BufferedWriter(new FileWriter("log2.out"));
        File file = new File("err.txt");
	FileOutputStream fos = new FileOutputStream(file);
	PrintStream ps = new PrintStream(fos);
	System.setErr(ps);
        Tabla t = null; // tabla de joc
        while (true){ // bucla infinita
            System.out.flush();
            if(side_on_move == culoare ){ // daca e motorul la mutare executa o mutare   
                adv = false;
                int i_p, i1_p;
                char j_p, j1_p;
                Mutare m = getValidMove(t,culoare,isSah(t, culoare)); // generez mutare 
                if (m != null){ // daca e valida
                    // se proceseaza mutarea astfel incat sa aiba formatul cunoscut de winboard
                    i_p = 8 - m.getFrom().getX() + 1;
                    i1_p = 8 - m.getWhere().getX() + 1;
                    j_p = Character.toChars(m.getFrom().getY()+96)[0];
                    j1_p = Character.toChars(m.getWhere().getY()+96)[0];
                    int from_x = m.getFrom().getX(),
                        from_y = m.getFrom().getY(),
                        to_x = m.getWhere().getX(),
                        to_y = m.getWhere().getY();
                    // executa miscarea si trimite comanda winboard-ului
                    t.getTabla()[from_x][from_y].makeMove(t,to_x, to_y, adv);
                    System.out.println("move " + j_p + i_p + j1_p + i1_p);
                    f.write("move " + j_p + i_p + j1_p + i1_p);
                    f.newLine();
                    f.write(t.toString());
                    f.newLine();
                }
                else { // daca nu am miscare valida abandonez 
                    System.out.println("resign");
                } // schimba jucatorul care se afla la mutare
                if (side_on_move == 1){
                    side_on_move = 2;
                }
                else side_on_move = 1;
                continue;
            }
            // interpretez fiecaer comanda primita de la winboard
            in=scanner.nextLine();
            String[] words=in.split(" ",2);
            f.write(in);
            f.newLine();
            System.out.flush();
            if(words[0].compareTo("xboard") == 0){
                continue;
            }
            if(words[0].compareTo("protover") == 0){
                System.out.println("feature usermove=1");
                System.out.println("feature colors=1");
            }
            if(words[0].compareTo("usermove") == 0){
                adv = true;
                if (side_on_move == 1){// schimb jucatorul
                    side_on_move = 2;
                }
                else side_on_move = 1;
                // procesez mutare pentru a ajunge in formatul cunoscut de motor
                String mutare = words[1];
                int x1, x2, y1, y2;
                y1 = mutare.charAt(0);
                y1 = y1 - 96;
                x1 = mutare.charAt(1);
                x1 = x1 - 48;
                x1 = 8 - x1 +1;
                y2 = mutare.charAt(2);
                y2 = y2 - 96;
                x2 = mutare.charAt(3);
                x2 = x2 - 48;
                x2 = 8 - x2 + 1;
                if(mutare.length() > 4){
                    t.getTabla()[x1][y1].makeMove(t, x2, y2, mutare.charAt(4));
                    continue;
                }
                t.getTabla()[x1][y1].makeMove(t, x2, y2, adv);
                f.write(t.toString());
                f.newLine();
                f.newLine();
                continue;
            }
            if(words[0].compareTo("new") == 0){
                t = new Tabla(); // initializez tabela
                continue;
            }
            if(words[0].compareTo("force") == 0){
                
                continue;
            }
            if(words[0].compareTo("go") == 0){
                continue;
            }
            if(words[0].compareTo("white") == 0){
                side_on_move = 1;
                culoare = 1;
                adv = false;
                continue;
                
            }
            if(words[0].compareTo("black") == 0){
                side_on_move = 1;
                culoare = 2;
                adv = true;
                continue;
            }
            if(words[0].compareTo("result") == 0){
                if (words[1].contains("resign")){
                    
                    continue;
                    
                }
            }
            if (words[0].compareTo("quit")==0) {
                f.close();
                break;
            }
        }
        
    }
    // metoda ce intoarce random o mutare valida
    public static Mutare getValidMove(Tabla t, int side, Mutare sah) throws IOException{
        ArrayList<Mutare> mutari = new ArrayList<Mutare>();
        // generez toate mutarile valide
        for (int i = 1; i < 9; i++){
            for (int j = 1; j< 9; j++){
                if (t.getTabla()[i][j] != null){
                    Piesa p = t.getTabla()[i][j]; 
                    if (p.getSide() == side){
                        for (int m = 1; m < 9; m++){
                            for (int n = 1; n< 9; n++){
                                if (p.muta(t, m, n, false)){ // miscarea e valida
                                    Tabla x = t.clona(); // clona a tablei
                                    x.getTabla()[i][j].makeMove(x, m, n, false); // execut miscarea pe clona
                                    if (isSah(x, side) == null){ // daca in urma executarii nu se intra in sah
                                        mutari.add(new Mutare(new Pair(i, j), new Pair(m, n))); // adaug miscarea
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (sah != null){ // daca este in sah
            // gasim si returnam miscarea care il scoate din sah
            Tabla x;
            ArrayList<Mutare> mutari_sah = new ArrayList<Mutare>();
            for (Mutare i:mutari){
                if((t.getTabla()[i.getFrom().getX()][i.getFrom().getY()] instanceof Rege) && (Math.abs(i.getFrom().getY() - i.getWhere().getY()) == 2))
                    continue;
                x = t.clona(); // copie a tablei
                // execut miscarea pe copie
                x.getTabla()[i.getFrom().getX()][i.getFrom().getY()].makeMove(x, i.getWhere().getX(), i.getWhere().getY(), false);
                // daca miscarea il scoate din sah
                if (isSah(x,side) == null){
                    mutari_sah.add(i); // adauga miscarea
                }
            }
            if (!mutari_sah.isEmpty()){ // daca am  gasit miscari
                Random rand = new Random();
                if (mutari_sah.size() == 1){
                    return mutari_sah.get(0);
                }
                int indice = rand.nextInt(mutari_sah.size()-1);
                return mutari_sah.get(indice); // returneaza miscare random
            }
            return null;// nu exista miscari
        }
        // daca nu e in sah returneaza o mutare random din mutarile valabile
        Random rand = new Random();
        if (mutari.size() == 0) return null;
        if (mutari.size() == 1){
            return mutari.get(0);
        }
        int indice = rand.nextInt(mutari.size()-1);
        return mutari.get(indice);
    }
    // verifica daca regele este in sah
    public static Mutare isSah(Tabla t, int side){
        int i_rege=0,j_rege=0; // pozitia regelui
        // caut pozitia regelui pe tabla
        for (int i = 1; i < 9; i++){
            boolean ok= false;
            for (int j = 1; j< 9; j++){
                if ( (t.getTabla()[i][j] instanceof Rege) && (t.getTabla()[i][j].getSide() == side) ){
                    i_rege = i;
                    j_rege = j;
                    ok = true;
                    break;
                }
              
            }
            if (ok) break;  
        }
        // verific toate miscarile 
        // daca facand o miscare regele este luat atunci returnez acea miscare
        for (int i = 1; i < 9; i++){
            for (int j = 1; j< 9; j++){
                if (t.getTabla()[i][j] != null){
                    Piesa p = t.getTabla()[i][j];
                    if (p.muta(t, i_rege, j_rege, false)){
                        return new Mutare(new Pair(i,j), new Pair(i_rege,j_rege));
                    }
                }
            }
        }
        return null; // altfel null - nu e sah
    }
}

