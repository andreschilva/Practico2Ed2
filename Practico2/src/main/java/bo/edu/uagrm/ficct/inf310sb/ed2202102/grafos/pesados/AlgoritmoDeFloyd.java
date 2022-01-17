/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import java.util.LinkedList;
import java.util.List;



/**
 *
 * @author Andres
 */
public class AlgoritmoDeFloyd {
    int maxFil;
    int maxCol;
    Double [][] matrizDePesos ;
    Integer [][] matrizDePredecesores;
    DiGrafoPesado digrafo;

    public AlgoritmoDeFloyd(DiGrafoPesado digrafo) throws ExcepcionAristaNoExiste {
        this.digrafo = digrafo;
        maxFil = this.digrafo.cantidaDeVertices();
        maxCol = this.digrafo.cantidaDeVertices();
        this.matrizDePesos = new Double[maxFil][maxCol];
        this.cargarMatrizDePesos();
        this.matrizDePredecesores = new Integer[maxFil][maxFil];
        this.cargarMatrizDePredecesores();
        floyd();
    }

    private void cargarMatrizDePesos() throws ExcepcionAristaNoExiste {
        for (int i = 0; i < maxFil; i++) {
            for (int j = 0; j < maxCol; j++) {
                if (i!=j) {
                    if (this.digrafo.existeAdyacencia(i, j)) {
                        this.matrizDePesos[i][j] = this.digrafo.peso(i, j);
                    } else {
                        this.matrizDePesos[i][j] = Double.POSITIVE_INFINITY;
                    }
                }else {
                    this.matrizDePesos[i][j] = 0.0;
                }
            }
        }
    }    

    private void cargarMatrizDePredecesores() {
        for (int i = 0; i < maxFil; i++) {
            for (int j = 0; j < maxCol; j++) {
                this.matrizDePredecesores[i][j] = -1;
            }
        }
    }
    
    private void floyd() {
        for (int pivote = 0; pivote < digrafo.cantidaDeVertices(); pivote++) {
            for (int i = 0; i < maxFil; i++) {
                for (int j = 0; j < maxCol; j++) {
                Double pesoPosActual = this.matrizDePesos[i][j];
                Double pesoPosFilaPivote = this.matrizDePesos[i][pivote];
                Double pesoPosPivoteColumna = this.matrizDePesos[pivote][j];
                    if (pesoPosActual > pesoPosFilaPivote + pesoPosPivoteColumna) {
                        this.matrizDePesos[i][j] = pesoPosFilaPivote + pesoPosPivoteColumna;
                        this.matrizDePredecesores [i][j] = pivote;
                    }
                }
            }
        }
    
    }
    
    public List<Integer> getCamino(int posOrigen, int posDestino) throws ExcepcionAristaNoExiste {
        if (this.matrizDePesos [posOrigen][posDestino] == Double.POSITIVE_INFINITY) {
            throw new ExcepcionAristaNoExiste("No hay camino");
        }else{
            List<Integer> listaDeCamino = new LinkedList<>();
            listaDeCamino.add(posOrigen);
            caminoCostoMinimo(posOrigen,posDestino,listaDeCamino);
            listaDeCamino.add(posDestino);
            return listaDeCamino;
        }
        
    }

    private void caminoCostoMinimo(int posOrigen, int posDestino, List<Integer> listaDeCamino) {
        if (this.matrizDePredecesores[posOrigen][posDestino] != -1) {
            Integer verticePredecesor = this.matrizDePredecesores[posOrigen][posDestino];
            caminoCostoMinimo(posOrigen, verticePredecesor, listaDeCamino);
            listaDeCamino.add(verticePredecesor);
            caminoCostoMinimo(verticePredecesor,posDestino, listaDeCamino);
        }
    }
    
    public Double getCostoMinimo(int posOrigen, int posDestino) throws ExcepcionAristaNoExiste {
        if (this.matrizDePesos [posOrigen][posDestino] == Double.POSITIVE_INFINITY) {
            throw new ExcepcionAristaNoExiste("No hay camino");    
        }else { 
            return this.matrizDePesos[posOrigen][posDestino];
        }
            
    }
    
}
