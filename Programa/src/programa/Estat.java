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

public class Estat {
    
    private ArrayList <Integer> assignacio; //assignacio[i] = servidor
    private ArrayList <Integer> ocupacioServidor;
    private Requests r;
    private Servers s;
    private final int R;
    private final int S;
    
    /**
     * Constructora.
     * @param r Conjunt de requests
     * @param s Conjunt de servidors
     * @param g Com es genera l'estat inicial
     */
    public Estat(Requests r, Servers s, Generacio g) {
        this.r = r;
        this.s = s;
        R = r.size();
        S = s.size();
        assignacio = new ArrayList<>();
        ocupacioServidor = new ArrayList<>();
        
        
        for (int i = 0; i < S; i++){
            ocupacioServidor.add(0);
        }
        
        //per cada request, li assignem un servidor random que contingui el fitxer
        for (int i = 0; i < R; ++i) { 
            
            int file = r.getRequest(i)[1];
            int user = r.getRequest(i)[0];
            Set<Integer> set = s.fileLocations(file); //conte enters amb els
            //id dels servidors que contenen el fitxer de la request
            
            Integer[] h = new Integer[set.size()];
            set.toArray(h);
            int serv_triat = escullServidor(user,h,g);
            assignacio.add(h[serv_triat]); //li assignem al servidor
            incrementa(ocupacioServidor, serv_triat, s.tranmissionTime(serv_triat, user));
            //actualitzem el temps del servidor
        }
    }
    
    /**
     * Constructora per copia.
     * @param e Estat a copiar
     */
    public Estat(Estat e){
        this.R = e.R;
        this.S = e.S;
        this.assignacio = new ArrayList<>(e.assignacio);
        this.ocupacioServidor = new ArrayList<>(e.ocupacioServidor);
        this.r = e.r;
        this.s = e.s;
    }
    
    /**
     * Escull un servidor per a una request, aleatoriament.
     * @param user Usuari que fa la request
     * @param h Array amb els servidors valids
     * @param g Com es triara el servidor (random, el mes proper, el mes llunya)
     * @return El servidor triat
     */
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
    
    /**
     * Getter d'assignacio.
     * @return Array list amb els servidors a on s'assigna cada request
     */
    public ArrayList<Integer> getAssignacio(){
        return assignacio;
    }
    
    /**
     * Getter d'ocupacioServidor.
     * @return Array list amb el temps que esta ocupat cada servidor
     */
    public ArrayList<Integer> getOcupacioServidor(){
        return ocupacioServidor;
    }
    
    /**
     * Compara dos estats.
     * @param e Estat a comparar amb l'actual
     * @return True si tenen les mateixes assignacions, false si no
     */
    public Boolean es_igual(Estat e){
        if (this.assignacio.size() != e.assignacio.size()) return false;
        for (int i = 0; i < assignacio.size(); i++){
            if (this.assignacio.get(i) != e.assignacio.get(i)) return false;
        }
        return true;
    }
    
    /**
     * Canvia de servidor una request. Si el nou servidor no conte el file de la
     * request, no fa res.
     * @param request Request a canviar
     * @param new_serv Nou servidor assignat a la request
     */
    public void Operador (int request, int new_serv){
        int user = r.getRequest(request)[0];
        int file = r.getRequest(request)[1];
        //si el nou servidor no conte la request, no fem res
        if (!s.fileLocations(file).contains(new_serv)) return; 
        
        int old_serv = assignacio.get(request);
        assignacio.set(request, new_serv);
        incrementa(ocupacioServidor, old_serv, -s.tranmissionTime(old_serv, user));
        incrementa(ocupacioServidor, new_serv, -s.tranmissionTime(new_serv, user));
    }
    
    /**
     * Incrementa un element d'una array list.
     * @param l Array list
     * @param i Posicio de l'element en l'array list
     * @param suma Valor a incrementar
     */
    private void incrementa (ArrayList<Integer> l, int i, int suma){
        l.set(i, l.get(i) + suma);
    }
    
}
