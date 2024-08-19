package org.vazquezj.criterios.criteriosdecisiones.criterios;

import java.util.Arrays;

abstract public class CriterioBase {
    protected double[][] matriz; //matriz de utilidades o escenarios
    double[] maximos; //valores maximos de cada fila
    double[] minimos; //valores minimos de cada fila

    public CriterioBase(double[][] matriz) {
        this.matriz = matriz;
        maximos = calcularMaximos();
        minimos = calcularMinimos();
    }

    public double[] getMinimos() {
        return minimos;
    }

    public double[] getMaximos() {
        return maximos;
    }

    public double[][] getMatriz() {
        return matriz;
    }

    //metodo que calcula los valores maximos de cada fila
    protected double[] calcularMaximos() {
        //usamos la api Stream para obtener el maximo de cada fila
        double[] maximos = Arrays.stream(matriz) //convertimos la matriz en un flujo de filas

                //obtenemos el maximo de cada fila transformando la fila en un flujo de double
                //y obteniendo el maximo de ese flujo
                .mapToDouble(fila -> Arrays.stream(fila).max().getAsDouble())
                .toArray(); //convertimos el flujo en un array
        return maximos;
    }

    //metodo que calcula los valores minimos de cada fila
    protected double[] calcularMinimos() {
        double [] minimos = Arrays.stream(matriz)
                .mapToDouble(fila -> Arrays.stream(fila).min().getAsDouble())
                .toArray();
        return minimos;
    }

    public double valorEsperado(double[] valores, double probabilidad) {
        double valorEsperado = 0;
        for (double valor : valores) {
            valorEsperado += valor * probabilidad;
        }
        return valorEsperado;
    }

    public double valorEsperado(double[] valores, double mejorCaso, double peorCaso) {
        double valorEsperado = valores[0] * peorCaso + valores[1] * mejorCaso;
        return valorEsperado;
    }

}

