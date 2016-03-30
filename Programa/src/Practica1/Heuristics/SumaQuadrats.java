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
public class SumaQuadrats implements HeuristicFunction {
    
    @Override
    public double getHeuristicValue(Object n) {
        Estat e = (Estat)n;
        ArrayList <Integer> ocupacio = e.getOcupacioServidor();
        int S = ocupacio.size();
        double sumQuadrats = 0;
        for (int i = 0; i < S; ++i) {
            double ocupacioI = ocupacio.get(i);
            sumQuadrats += ocupacioI*ocupacioI;
        }
        return sumQuadrats;
    }
}
