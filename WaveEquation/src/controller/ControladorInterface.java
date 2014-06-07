/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import builder.GeradorGrafico;
import builder.GraficoBuilder;
import builder.RungeKutta2Grafico;
import builder.RungeKutta4Grafico;
import builder.EullerGrafico;
import builder.RungeKutta3Grafico;
import observer.ControleInterfaceObserver;
import observer.ControleInterfaceAtualizador;
import strategy.Contexto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author 03219223109
 */
public final class ControladorInterface {

    private int valorCombo, valorFuncao, m;
    private double x0,y0,h;
    private List<ArrayList> valores;
    private final Set<ControleInterfaceObserver> observers;
    public Contexto contexto;
    private XYSeries serie;
    private static ControladorInterface instance;

    private ControladorInterface() {
        observers = new HashSet<ControleInterfaceObserver>();
        adicionaObserver(new ControleInterfaceAtualizador());                        
    }

    public void setValoresIniciais(){
        this.valores = contexto.executarEstrategia(x0, y0, h, m, valorFuncao);
    }
    
    public static ControladorInterface getInstance(){
        if(ControladorInterface.instance == null){
            ControladorInterface.instance = new ControladorInterface();            
        }        
        return ControladorInterface.instance;
    }
    
    public void criarGrafico() throws Exception {
        serie = criarDados(valores);
        GraficoBuilder gBuilder = null;
        if (valorCombo == 0) {
            gBuilder = new EullerGrafico();        
        }
        if (valorCombo == 1) {
            gBuilder = new RungeKutta2Grafico();        
        }        
        if (valorCombo == 2) {
            gBuilder = new RungeKutta3Grafico();        
        }
        if (valorCombo == 3) {
            gBuilder = new RungeKutta4Grafico();        
        }
        
        GeradorGrafico g = new GeradorGrafico(gBuilder, serie);
        JFreeChart j = g.gerarGrafico();
        XYPlot plot = (XYPlot) j.getPlot();
        ChartFrame frame = new ChartFrame("Grafico", j);
        frame.setSize(450, 250);
        frame.setVisible(true);
    }

    public void adicionaObserver(ControleInterfaceObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(ControleInterfaceObserver observer) {
        this.observers.remove(observer);
    }

    public int getValorCombo() {
        return valorCombo;
    }

    public List<ArrayList> getLista() {
        return this.valores;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double getX0() {
        return x0;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public double getY0() {
        return y0;
    }

    public void setY0(double y0) {
        this.y0 = y0;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }
    
    public void setLista(List<ArrayList> lista){
        this.valores = lista;
    }
    
    public void setParametros(double x,double y,double h,int m){
        this.x0 = x;
        this.y0 = y;
        this.h = h;
        this.m = m;        
    }
    
    public void setValorFuncao(int i){
        this.valorFuncao = i;
    }
    public int getValorFuncao(){
        return this.valorFuncao; 
    }
    
    public void setValorCombo(int valorCombo) {
        this.valorCombo = valorCombo;
        for (ControleInterfaceObserver observer : this.observers) {
            observer.relaziarAlteracoes(ControladorInterface.getInstance());
        }
    }

    private XYSeries criarDados(List<ArrayList> valores) {
        XYSeries serie = new XYSeries("Valores");
        for (int i = 0; i < valores.get(0).size(); i++) {
            serie.add((Double) valores.get(0).get(i), (Double) valores.get(1).get(i));
        }
        return serie;
    }

}
