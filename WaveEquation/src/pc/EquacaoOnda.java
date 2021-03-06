package pc;

public class EquacaoOnda {

    // xO = 0
    // tO = 0
    private double dT, dX;
    private int nT, nX;
    private double[][] pontos;

    public EquacaoOnda(int nX, int nT, double deltaT) {
        this.pontos = new double[nT][nX];
        this.dT = deltaT;
        this.nT = nT;
        this.nX = nX;
        this.dX = 1.0 / (nX - 1.0);
        valoresIniciais();
    }

    private void valoresIniciais() {
        
//        for (int i = 0; i < nX; i++) {
//            pontos[0][i] = pontos[1][i] = Math.sin(Math.PI * i * dX);
//        }
        for (int i = 0; i < nT; i++) {
            pontos[i][0] = pontos[i][nX - 1] = 0.0;
        }
        pontos[0][nX/2] = pontos[1][nX/2] = 1.0;
    }
    //U(i, t+1) = -U(i, t-1) +2U(i,t) + (U(i+1,t) -2U(i,t) +U(i-1,t))*(dt/dx)^2
    public void resolverExplicito(){
        double delta = Math.pow(dT/dX, 2);
        for (int i = 2; i < nT; i++) {
            for (int j = 1; j < nX -1; j++) {
                pontos[i][j] = 2 * pontos[i-1][j] - pontos[i-2][j] + (pontos[i-1][j-1] - 2*pontos[i-1][j] + pontos[i-1][j+1]) * delta; 
            }
        }
        mostrarMatriz(pontos);
    }

    //U(k+1,i) = (deltaX/2*deltaT)^2 * (2U(k,i+1) - U(k-1,i+1) - U(k+1,i+1)) + (U(k+1,i-1) + U(k+1,i+1))/2
    public void resolverImplicito() {
        for (int i = 2; i < nT; i++) {
            double[][] sistema = montarSistema(i, pontos);
            double[] solucao = resolverSistema(sistema);
            for (int j = 0; j < solucao.length; j++) {
                pontos[i][j + 1] = solucao[j];
            }
        }
//        mostrarMatriz(pontos);
    }

    public static void mostrarArray(double[] array) {
        for (int j = 0; j < array.length; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println();
    }

    public static void mostrarMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            mostrarArray(matriz[i]);
        }
        System.out.println();
    }

    private double[][] montarSistema(int linha, double[][] matriz) {
        int length = matriz[linha].length - 2;
        double[][] ret = new double[length][length + 1];
        double delta = Math.pow(dX /dT, 2); //(deltaX / deltaT)^2
        for (int i = 0; i < length; i++) {
            ret[i][i] = (-delta - 2.0);
            if (i > 0) {
                ret[i][i - 1] = 1.0;
            } else {
                ret[i][length] = -(matriz[linha][0]);
            }
            if (i < length - 1) {
                ret[i][i + 1] = 1.0;
            } else {
                ret[i][length] = -(matriz[linha][length + 1]);
            }
            ret[i][length] += (delta * ( -2.0 * matriz[linha - 1][i + 1] + matriz[linha - 2][i + 1]));
        }
        return ret;
    }

    private double[] resolverSistema(double[][] sistema) {
//        System.out.println("antes");
//        mostrarMatriz(sistema);
//        System.out.println("");
        double[] ret = new double[sistema.length];
        for (int i = 0; i < sistema.length - 1; i++) {
            double d = sistema[i + 1][i] / sistema[i][i];
            multiplicarLinha(sistema, i, -d);
            somarLinhas(sistema, i, i + 1);
            sistema[i + 1][i] = 0.0;
        }
        for (int i = sistema.length - 1; i > 0; i--) {
            double d = sistema[i - 1][i] / sistema[i][i];
            multiplicarLinha(sistema, i, -d);
            somarLinhas(sistema, i, i - 1);
            sistema[i - 1][i] = 0.0;
        }
        for (int i = 0; i < sistema.length; i++) {
            double d = 1.0 / sistema[i][i];
            multiplicarLinha(sistema, i, d);
            ret[i] = sistema[i][ret.length];
        }
//        System.out.println("depois");
//        mostrarMatriz(sistema);
//        System.out.println("");
        return ret;
    }

    private void multiplicarLinha(double[][] sistema, int linha, double valor) {
        for (int i = 0; i < sistema[linha].length; i++) {
            sistema[linha][i] *= valor;
        }
    }

    private void somarLinhas(double[][] sistema, int linhaOrig, int linhaDest) {
        for (int i = 0; i < sistema[linhaDest].length; i++) {
            sistema[linhaDest][i] += sistema[linhaOrig][i];
        }
    }

    public double getdT() {
        return dT;
    }

    public double getdX() {
        return dX;
    }

    public int getnT() {
        return nT;
    }

    public int getnX() {
        return nX;
    }

    public double[][] getPontos() {
        return pontos;
    }
    
    
}
