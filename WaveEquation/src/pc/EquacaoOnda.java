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
        for (int i = 0; i < nX; i++) {
            pontos[0][i] = pontos[1][i] = Math.sin(Math.PI * i * dX);
        }
        for (int i = 0; i < nT; i++) {
            pontos[i][0] = pontos[i][nX - 1] = 0.0;
        }
    }

    //U(i,k+1) = (deltaX^2/2deltaT^2)(2U(k,i+1) - U(k-1,i+1) - U(k+1,i+1)) + (U(k+1,i-1) + U(k+1,i+1))/2
    public void resolverImplicito() {
        for (int i = 2; i < nT; i++) {
            double[][] sistema = montarSistema(i, pontos);
            double[] solucao = resolverSistema(sistema);
            for (int j = 0; j < solucao.length; j++) {
                pontos[i][j + 1] = solucao[j];
            }
        }
        mostrarMatriz(pontos);
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
        double delta = Math.pow(dX /(2.0 * dT), 2); //(deltaX / deltaT)^2
        for (int i = 0; i < length; i++) {
            ret[i][i] = 1;
            if (i > 0) {
                ret[i][i - 1] = -0.5;
            } else {
                ret[i][length] = matriz[linha][0] * 0.5;
            }
            if (i < length - 1) {
                ret[i][i + 1] = delta - 0.5;
            } else {
                ret[i][length] = matriz[linha][length + 1] * (0.5 - delta);
            }
            ret[i][length] += delta * (2.0 *matriz[linha - 1][i + 2] - matriz[linha - 2][i + 2]);
        }
        return ret;
    }

    private double[] resolverSistema(double[][] sistema) {
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
}
