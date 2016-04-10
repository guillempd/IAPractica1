/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1.Heuristics;

import Practica1.Estat;
import aima.search.framework.HeuristicFunction;

/**
 *
 * @author guillem.perez.delgado
 */
public class DesviacioSuma1n implements HeuristicFunction {
    
    public double getHeuristicValue(Object n) {
        Estat e = (Estat)n;
        HeuristicFunction h1 = new Suma();
        HeuristicFunction h2 = new DesviacioTipica();
        int S = e.serversSize();
        return h1.getHeuristicValue(e) + S*Math.sqrt(h2.getHeuristicValue(e) / 500);
    }
    
}
