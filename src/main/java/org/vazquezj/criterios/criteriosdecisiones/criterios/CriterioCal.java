package org.vazquezj.criterios.criteriosdecisiones.criterios;

public class CriterioCal extends CriterioBase{
    private double alpha;
    private double beta;

    public CriterioCal(double[][] matriz, double alpha, double beta) {
        super(matriz);
        this.alpha = alpha;
        this.beta = beta;
    }

    public int criterioMaxiMin() {
        //obtenemos minimos de cada fila
        double[] minimos = getMinimos();

        //obtenemos el indice del maximo de los minimos
        int indice = 0;
        for (int i = 0; i < minimos.length; i++) {
            if (minimos[i] > minimos[indice]) {
                indice = i;
            }
        }
        return indice + 1;
    }

    public int criterioMaxiMax() {
        //obtenemos maximos de cada fila
        double[] maximos = getMaximos();

        //obtenemos el indice del maximo de los maximos
        int indice = 0;
        for (int i = 0; i < maximos.length; i++) {
            if (maximos[i] > maximos[indice]) {
                indice = i;
            }
        }
        return indice + 1;
    }

    public int criterioLaplace() {
        double[][] matrizUtilidades = getMatriz();

        double[] valoresEsperados = new double[matrizUtilidades.length]; //se crea un arreglo de tamaño igual a la cantidad de filas
        for (int i = 0; i < matrizUtilidades.length; i++) {
            valoresEsperados[i] = valorEsperado(matrizUtilidades[i], 1.0 / matrizUtilidades[i].length);
        }

        //se busca el valor esperado mayor
        int maxIndex = 0;
        for (int i = 1; i < valoresEsperados.length; i++) {
            if (valoresEsperados[i] > valoresEsperados[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex + 1;
    }

    public int criterioHurtwicz() {
        //obtenemos minimos y maximos de cada fila
        double[] minimos = getMinimos();
        double[] maximos = getMaximos();

        //obtenemos valor esperado
        double[] valoresEsperados = new double[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            valoresEsperados[i] = valorEsperado(new double[]{minimos[i], maximos[i]}, alpha, beta);
        }

        //se busca el valor esperado mayor
        int maxIndex = 0;
        for (int i = 1; i < valoresEsperados.length; i++) {
            if (valoresEsperados[i] > valoresEsperados[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex + 1;
    }
}

