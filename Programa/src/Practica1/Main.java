/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica1;

import Practica1.Heuristics.Suma;
import Practica1.Successors.SuccessorsHillClimbing;
import IA.DistFS.Requests;
import IA.DistFS.Servers;
import aima.search.framework.GoalTest;
import aima.search.framework.HeuristicFunction;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author 1182177
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Requests r = new Requests(200,5,10);
            Servers s = new Servers(10,5,10);
            Estat estatInicial = new Estat(r,s,Generacio.LLUNYA);
            HeuristicFunction heuristicFunction = new Suma();
            SuccessorsHillClimbing successorFunction = new SuccessorsHillClimbing(r,s,heuristicFunction);
            GoalTest gt = new GoalTest(){
                @Override
                public boolean isGoalState(Object aState) {
                    return false;
                }
            };
            
            //estatInicial.print();
            System.out.print("\nHeuristic = " + heuristicFunction.getHeuristicValue(estatInicial) + "\n");
            
            
            
            
            Problem problem =  new Problem(estatInicial, successorFunction, gt, heuristicFunction);
            Search search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            
            System.out.println();
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
        
    }
    
    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }
}
