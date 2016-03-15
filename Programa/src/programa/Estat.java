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
    private Requests r;
    private Servers s;
    private final int R;
    private final int S;
    
    public Estat(Requests r, Servers s, Generacio g) {
        this.r = r;
        this.s = s;
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
            int serv_triat = escullServidor(user,h,g);
            assignacio.add(h[serv_triat]); //li assignem al servidor
            ocupacioServidor.set(serv_triat, incrementa(ocupacioServidor, serv_triat, s.tranmissionTime(serv_triat, user)));
            //actualitzem el temps del servidor
        }
    }
    
    public Estat(Estat e){
        //constructora per copia
    }
    
    private int escullServidor(int user, Integer[] h, Generacio g){
        if (g == Generacio.RANDOM){
            Random rand = new Random(System.currentTimeMillis());
            return rand.nextInt(h.length);//triem un servidor aleatori
        }else if (g == Generacio.PROPER){
            int mindist = s.tranmissionTime(h[0], user);
            int minserv = h[0];
            for (int i = 0; i < h.length; i++){
                if (s.tranmissionTime(h[i], user) < mindist){
                    mindist = s.tranmissionTime(h[i], user);
                    minserv = h[i];
                }
            }
            return minserv;
        }else{ //g = llunya
            int maxdist = s.tranmissionTime(h[0], user);
            int maxserv = h[0];
            for (int i = 0; i < h.length; i++){
                if (s.tranmissionTime(h[i], user) > maxdist){
                    maxdist = s.tranmissionTime(h[i], user);
                    maxserv = h[i];
                }
            }
            return maxserv;
        }
    }
    
    public ArrayList<Integer> getAssignacio(){
        return assignacio;
    }
    
    public ArrayList<Integer> getOcupacioServidor(){
        return ocupacioServidor;
    }
    
    public Boolean es_igual(Estat e){
        return false;
    }
    
    public void Operador (int request, int new_serv){
        //si no es pot no modificar res
        
        
        int old_serv = assignacio.get(request);
        assignacio.set(request, new_serv);
        
        
    }
    
    private int incrementa (ArrayList<Integer> l, int i, int suma){
        return l.get(i) + suma;
    }
    
}
