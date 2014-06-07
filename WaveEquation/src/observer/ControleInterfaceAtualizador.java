/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import strategy.RungeKutta3;
import strategy.Contexto;
import strategy.Euller;
import strategy.RungeKutta2;
import strategy.RungeKutta4;
import controller.ControladorInterface;

/**
 *
 * @author 03219223109
 */
public class ControleInterfaceAtualizador implements ControleInterfaceObserver {

    public void relaziarAlteracoes(ControladorInterface controle) {
        controle = ControladorInterface.getInstance();
        if (controle.getValorCombo() == 0) {
            controle.contexto = new Contexto(new Euller());
        }
        if (controle.getValorCombo() == 1) {
            controle.contexto = new Contexto(new RungeKutta2());
        }
        if (controle.getValorCombo() == 2) {
            controle.contexto = new Contexto(new RungeKutta3());
        }
        if (controle.getValorCombo() == 3) {
            controle.contexto = new Contexto(new RungeKutta4());
        }
        //controle.setValoresIniciais();
        controle.setLista(controle.contexto.executarEstrategia(controle.getX0(),
                controle.getY0(), controle.getH(), controle.getM(), controle.getValorFuncao()));
    }

}
