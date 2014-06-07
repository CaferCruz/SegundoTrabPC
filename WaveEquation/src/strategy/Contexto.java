/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package strategy;

import java.util.List;

/**
 *
 * @author 03219223109
 */
public class Contexto {
    private Estrategia estrategia;

    public Contexto(Estrategia est){
        this.estrategia = est;
    }
    public List executarEstrategia(double x0,double y0,double h,int m,int id){
        return this.estrategia.execute(x0, y0, h, m, id);
    }
}
