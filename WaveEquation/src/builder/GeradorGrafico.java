/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author 03219223109
 */
public class GeradorGrafico {

    private GraficoBuilder graficoBuilder;
    private XYSeries data;

    public GeradorGrafico(GraficoBuilder g, XYSeries dados) {
        this.graficoBuilder = g;
        this.data = dados;
    }

    public JFreeChart gerarGrafico() throws Exception {
        this.graficoBuilder.buildDados(data);
        return this.graficoBuilder.getGrafico();
    }
}
