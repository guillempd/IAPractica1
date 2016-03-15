/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

/**
 *
 * @author guillem.perez.delgado
 */
public class Practica1SuccessorsHillClimbing implements SuccessorFunction {
    
    public List getSuccessors(Object aState) {
        Estat e = (Estat)aState;
        ArrayList ret = new ArrayList();
        int R = e.getAssignacio().size();
    }
    
}
