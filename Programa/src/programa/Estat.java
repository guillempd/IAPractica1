/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import java.util.ArrayList;
import IA.DistFS.*;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Guillem
 */

public class Estat {
    
    private ArrayList <Integer> assignacio; //assignacio[i] = servidor
    private ArrayList <Integer> ocupacioServidor;
    private final int R;
    private final int S;
    
    public Estat(Requests r, Servers s) {
        R = r.size();
        S = s.size();
        
        for (int i = 0; i < S; i++){
            ocupacioServidor.add(0);
        }
        //per cada request, li assignem un servidor random que contingui el fitxer
        for (int i = 0; i < R; ++i) { 
            
            int file = r.getRequest(i)[1];
            int user = r.getRequest(i)[0];
            Set<Integer> set = s.fileLocations(file); //conte enters amb els
            //id dels servidors que contenen el fitxer de la request
            
            Integer[] h = (Integer[]) set.toArray();
            Random rand = new Random(System.currentTimeMillis());
            int serv_triat = rand.nextInt(h.length);//triem un servidor aleatori
            assignacio.add(h[serv_triat]); //li assignem al servidor
            ocupacioServidor.set(serv_triat,s.tranmissionTime(serv_triat, user));
            //actualitzem el temps del servidor
        }
    }
    
    public ArrayList<Integer> getAssignacio(){
        return assignacio;
    }
    
    public ArrayList<Integer> getOcupacioServidor(){
        return ocupacioServidor;
    }
    
    
    
}
