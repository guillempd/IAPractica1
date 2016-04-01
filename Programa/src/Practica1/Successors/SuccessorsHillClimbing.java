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
import java.util.Set;

/**
 *
 * @author david.folque
 */
public class SuccessorsHillClimbing implements SuccessorFunction {
    
    private final Requests r;
    private final Servers s;
    private final HeuristicFunction hf;
    
    public SuccessorsHillClimbing (Requests r, Servers s, HeuristicFunction hf) {
        this.r = r;
        this.s = s;
        this.hf = hf;
    }
    
    @Override
    public ArrayList<Successor> getSuccessors(Object aState) {
        Estat e = (Estat)aState;
        ArrayList<Integer> assignacio = e.getAssignacio();
        ArrayList<Successor> ret = new ArrayList<>();
        int R = r.size();
        for (int i = 0; i < R; ++i) {
            int fileID = r.getRequest(i)[1];
            Set<Integer> setServers = s.fileLocations(fileID);
            Iterator<Integer> it = setServers.iterator();
            while (it.hasNext()) {
                int server = it.next();
                if (server != assignacio.get(i)) {
                    Estat nouEstatDEuropa = new Estat(e);
                    nouEstatDEuropa.Operador(i, server);
                    ret.add(new Successor("Request " + i + " -> servidor " + server + ". Heuristica = " + hf.getHeuristicValue(nouEstatDEuropa) +"\n", nouEstatDEuropa));
                }
            }
        }
        return ret;
    }
    
}