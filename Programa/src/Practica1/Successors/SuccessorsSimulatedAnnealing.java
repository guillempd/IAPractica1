/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1.Successors;

import IA.DistFS.Requests;
import IA.DistFS.Servers;
import Practica1.Estat;
import aima.search.framework.HeuristicFunction;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author David
 */
public class SuccessorsSimulatedAnnealing implements SuccessorFunction {
    
    private Requests r;
    private Servers s;
    private HeuristicFunction hf;
    
    public SuccessorsSimulatedAnnealing (Requests r, Servers s, HeuristicFunction hf) {
        this.r = r;
        this.s = s;
        this.hf = hf;
    }
    
    public ArrayList<Successor> getSuccessors(Object aState) {
        Estat e = (Estat)aState;
        ArrayList<Integer> assignacio = e.getAssignacio();
        ArrayList<Successor> ret = new ArrayList<>();
        Random rand = new Random(System.currentTimeMillis());
        int i = rand.nextInt(r.size()-1);
            int fileID = r.getRequest(i)[1];
            Set<Integer> setServers = s.fileLocations(fileID);
            Integer[] servers = new Integer[setServers.size()];
            setServers.toArray(servers);
            int j;
            do j = rand.nextInt(servers.length-1);
            while (servers[j].equals(assignacio.get(i)));
                Estat nouEstatDEuropa = new Estat(e);
                nouEstatDEuropa.Operador(i, servers[j]);
                ret.add(new Successor("Request " + i + " -> servidor " + servers[j] + ". Heuristica = " + hf.getHeuristicValue(nouEstatDEuropa) +"\n", nouEstatDEuropa));

        return ret;
    }
    
}