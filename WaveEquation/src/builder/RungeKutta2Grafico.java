/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package builder;

import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author 03219223109
 */
public class RungeKutta2Grafico implements GraficoBuilder {
    private List <ArrayList>valores;
    private XYSeries data;

    public void buildDados(XYSeries data){
        this.data = data;
    }

    public JFreeChart getGrafico() {
        XYSeriesCollection DadosXY = new XYSeriesCollection();
        DadosXY.addSeries(this.data);
        return ChartFactory.createXYLineChart("Grafico Runge Kutta Ordem 2", "X", "Y", DadosXY);
    }
}
