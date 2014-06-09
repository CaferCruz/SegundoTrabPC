package pc;

public class EquacaoOnda {
    
    // xO = 0
    // tO = 0
    private double dT, dX;
    private int nT,nX;
    private double[][] pontos;
    
    public EquacaoOnda(int nX, int nT, double deltaT){
        this.pontos = new double[nT][nX];
        this.dT = deltaT;
        this.nT = nT;
        this.nX = nX;
        this.dX = 1.0 / (nX - 1.0);
        valoresIniciais();
    }
    
    private void valoresIniciais(){
        for (int i = 0; i < nX; i++) {
            pontos[0][i] = pontos[1][i] = Math.sin(Math.PI * i * dX);
        }
        for (int i = 0; i < nT; i++) {
            pontos[i][0] = pontos[i][nX -1] = 0.0;
        }
    }
}
