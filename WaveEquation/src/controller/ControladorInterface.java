/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


//import strategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author 03219223109
 */
public final class ControladorInterface {

    private int valorQuestao;
    private List<ArrayList> valores;
//    public Contexto contexto;
    private XYSeries serie;
    private static ControladorInterface instance;

    private ControladorInterface() {
                           
    }
    
    public static ControladorInterface getInstance(){
        if(ControladorInterface.instance == null){
            ControladorInterface.instance = new ControladorInterface();            
        }        
        return ControladorInterface.instance;
    }
    
   public static void setValorQuestao(int valor, int escolha){
   
   }
}
