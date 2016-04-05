/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1.Heuristics;

import Practica1.Estat;
import aima.search.framework.HeuristicFunction;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 *
 * @author guillem.perez.delgado
 */
public class Desviacio implements HeuristicFunction {
    
    @Override
    public double getHeuristicValue(Object n) {
        Estat e = (Estat)n;
        ArrayList <Integer> ocupacio = e.getOcupacioServidor();
        int S = ocupacio.size();
        double sum = 0;
        for (int i = 0; i < S; ++i) sum += (double)ocupacio.get(i);
        double mitja = sum/(double)S;
        double desviacio = 0;
        for (int i = 0; i < S; ++i)  {
            desviacio += abs(mitja-(double)ocupacio.get(i));
        }
        return desviacio;
    }
    public double getHeuristicValue2(Object n) {
        Estat e = (Estat)n;
        ArrayList <Integer> ocupacio = e.getOcupacioServidor();
        int S = ocupacio.size();
        double sum = 0;
        for (int i = 0; i < S; ++i) sum += (double)ocupacio.get(i);
        double mitja = sum/(double)S;
        System.out.println("mitjana : " + mitja);
        double desviacio = 0;
        for (int i = 0; i < S; ++i)  {
            double ocupacioI = (double)ocupacio.get(i);
            System.out.println(mitja-ocupacioI);
            desviacio += abs(mitja-ocupacioI);
        }
        return desviacio;
    }
    
}
