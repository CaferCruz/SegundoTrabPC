/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pc;

import view.Programa;
import controller.ControladorInterface;

/**
 *
 * @author 03219223109
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {        
        ControladorInterface c = ControladorInterface.getInstance();
        Programa p = new Programa(c);
        p.setTitle("Equação da Onda");
        p.setVisible(true);
    }
}
