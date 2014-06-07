/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 03219223109
 */
public class Euller implements Estrategia {

    public List execute(double x0, double y0, double h, int m, int id) {
        List<ArrayList> valores = new ArrayList();
        List<Double> valoresX = new ArrayList();
        List<Double> valoresY = new ArrayList();
        valoresX.add(x0);
        valoresY.add(y0);

        for (int i = 0; i < m; i++) {
            valoresY.add(valoresY.get(i) + (h * f(valoresX.get(i), valoresY.get(i), id)));
            valoresX.add((valoresX.get(i)) + h);
        }
        valores.add((ArrayList) valoresX);
        valores.add((ArrayList) valoresY);
        return valores;
    }

    double f(double x, double y, int id) {
        if (id == 0) {
            return 1 - x + 4 * y;
        }
        if (id == 1) {
            return Math.pow(x, 2);
        }
        if (id == 2) {
            return x + y;                    
        }
        return x;
    }
}
