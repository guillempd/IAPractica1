/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1.Heuristics;

import Practica1.Estat;
import aima.search.framework.HeuristicFunction;
import java.util.ArrayList;

/**
 *
 * @author guillem.perez.delgado
 */

public class MinMax implements HeuristicFunction {
    
    //Suponemos que ocupacio tiene almenos un elemento
    @Override
    public double getHeuristicValue(Object n) {
        Estat e = (Estat)n;
        ArrayList <Integer> ocupacio = e.getOcupacioServidor();
        double max = ocupacio.get(0);
        int S = e.serversSize();
        for (int i = 1; i < S; ++i) {
            double ocupacioI = ocupacio.get(i);
            if (ocupacioI > max) max = ocupacioI;
        }
        return max;
    }
    
}
