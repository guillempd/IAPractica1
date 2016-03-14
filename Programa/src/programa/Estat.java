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
    
    private ArrayList <Integer> assignacio;
    private ArrayList <Integer> ocupacioServidor;
    private final int R;
    private final int S;
    
    public Estat(Requests r, Servers s) {
        R = r.size();
        S = s.size();
        for (int i = 0; i < R; ++i) {
            int file = r.getRequest(i)[1];
            Set<Integer> set = s.fileLocations(file);
            Integer[] h = (Integer[]) set.toArray();
            Random rand = new Random(System.currentTimeMillis());
            assignacio.add(h[rand.nextInt(h.length)]);
        }
    }
    
}
