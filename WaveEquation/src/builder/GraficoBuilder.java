/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import java.util.List;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author 03219223109
 */
public interface GraficoBuilder {            
    public void buildDados(XYSeries data);
    JFreeChart getGrafico();
}
