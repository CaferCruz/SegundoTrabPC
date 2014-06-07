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
public interface Estrategia {
    List execute(double x0,double y0,double h,int m,int id);
}
