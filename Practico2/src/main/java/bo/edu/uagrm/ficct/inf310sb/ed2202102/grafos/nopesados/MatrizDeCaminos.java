/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import java.util.List;

/**
 *
 * @author Andres
 */
public class MatrizDeCaminos {
    int maxFil;
    int maxCol;
    Integer  matrizDeAdyacencia[][] ;
    DiGrafo digrafo;

    public MatrizDeCaminos(DiGrafo digrafo) {
        this.digrafo = digrafo;
        maxFil = this.digrafo.cantidaDeVertices();
        maxCol = this.digrafo.cantidaDeVertices();
        this.matrizDeAdyacencia = new Integer[maxFil][maxCol];
        this.cargarMatrizDeAdy();
        this.algoritmoDeWarshall();
    }

    private void cargarMatrizDeAdy() {
        for (int i = 0; i < maxFil; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (this.digrafo.existeAdyacencia(i, j)) {
                    this.matrizDeAdyacencia[i][j] = 1;
                }else {
                    this.matrizDeAdyacencia[i][j] = 0;
                }
            }
        }
    }
    
    private void algoritmoDeWarshall() {
        for (int k = 0; k < maxFil; k++) {
            for (int i = 0; i < maxFil; i++) { 
                if (i == k) {
                    continue;
                }
                for (int j = 0; j < maxCol; j++) {
                    if (j == k ) {
                        continue;
                    }
                    if (this.matrizDeAdyacencia[i][k] == 1 && this.matrizDeAdyacencia[k][j] == 1) {
                        this.matrizDeAdyacencia[i][j] = 1;
                    }
                    
                }
            }
        }
    }
    
    public boolean esFuertementeConexo() {
        for (int i = 0; i < maxFil; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (this.matrizDeAdyacencia[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean hayCiclo () {
        for (int i = 0; i < maxFil; i++) {
            if (this.matrizDeAdyacencia[i][i] == 1) {
                return true;
            }
        }
        return false;
    }
    
    public String mostrarCaminos() { 
     String cadena = "";   
        for (int i = 0; i < maxFil; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (this.matrizDeAdyacencia[i][j] == 1) {
                    cadena += " " + "(" + Integer.toString(i) + ", " + Integer.toString(j) + ")";
                }
            }
            cadena += "\n";
        }
        return cadena;
    }

    
    public String mostrar() {
        String cadena = "\n";
        for (int i = 0; i < maxFil; i++) {
            cadena += "|";
            for (int j = 0; j < maxCol; j++) {
                cadena += " " + Integer.toString(matrizDeAdyacencia[i][j]);
            }
            cadena += "|" + "\n";
        }
        return cadena;
    }
    
    
}
