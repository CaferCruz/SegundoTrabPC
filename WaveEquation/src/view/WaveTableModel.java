package view;

import javax.swing.table.AbstractTableModel;

public class WaveTableModel extends AbstractTableModel {
    
    public float h;
    public float t0;
    public double[][] matrixPontos; // matrix[row][column]

    public WaveTableModel(float h, float t0, double[][] matrixPontos) {
        super();
        this.h = h;
        this.t0 = t0;
        this.matrixPontos = matrixPontos;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:{
                return "Instante t";
            }
            case 1:{
                return "Tempo";
            }
            default:{
                return "x"+(column -2);
            }
        }
    }

    @Override
    public int getRowCount() {
        return this.matrixPontos.length;
    }

    @Override
    public int getColumnCount() {
        return this.matrixPontos[0].length +2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:{
                return "t"+rowIndex;
            }
            case 1:{
                return t0 + (h * rowIndex);
            }
            default:{
                return matrixPontos[rowIndex][columnIndex -2];
            }
        }
    }
    
}
