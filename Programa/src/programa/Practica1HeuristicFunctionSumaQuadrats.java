/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import aima.search.framework.HeuristicFunction;
import java.util.ArrayList;

/**
 *
 * @author guillem.perez.delgado
 */
public class Practica1HeuristicFunctionSumaQuadrats implements HeuristicFunction {
    
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
