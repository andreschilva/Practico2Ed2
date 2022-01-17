/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.RecorridoUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Andres
 */
public class AlgoritmoDeFordFulkerson {
    private DiGrafoPesado digrafo;
    private RecorridoUtils controlDeMarcados;
    private Integer verticefuente;
    private Integer verticeSumidero;
    
    public AlgoritmoDeFordFulkerson(DiGrafoPesado digrafo) throws ExcepcionAristaYaExiste {
        this.digrafo = digrafo;
        this.controlDeMarcados = new RecorridoUtils(this.digrafo.cantidaDeVertices());
        encontrarVerticeFuente();
        encontrarVerticeSumidero();
        insertarAristasConPesoCero();
    }

    private void encontrarVerticeFuente() throws ExcepcionAristaYaExiste {
        List<Integer> listaDeVerticesFuente = new LinkedList<>();
        for (int i = 0; i < this.digrafo.cantidaDeVertices(); i++) {
            if (this.digrafo.gradoDeEntradaDeVertice(i) == 0) {
                listaDeVerticesFuente.add(i);
            }
        }
        if (listaDeVerticesFuente.size() == 1) {
            this.verticefuente = listaDeVerticesFuente.get(0);
        }else { 
            this.digrafo.insertarVertice();
            this.verticefuente = (this.digrafo.cantidaDeVertices()-1);
            for (Integer posVerticesFuentes : listaDeVerticesFuente) {
                this.digrafo.insertarArista((this.digrafo.cantidaDeVertices()-1),posVerticesFuentes , 1000);   
            }
        }
    }

    private void encontrarVerticeSumidero() throws ExcepcionAristaYaExiste {
        List<Integer> listaDeVerticesSumidero = new LinkedList<>();
        for (int i = 0; i < this.digrafo.cantidaDeVertices(); i++) {
            if (this.digrafo.gradoDeSalidaDeVertice(i) == 0) {
                listaDeVerticesSumidero.add(i);
            }
        }
        if (listaDeVerticesSumidero.size() == 1) {
            this.verticeSumidero = listaDeVerticesSumidero.get(0);
        }else { 
            this.digrafo.insertarVertice();
            this.verticeSumidero = (this.digrafo.cantidaDeVertices()-1);
            for (Integer posVerticesSumideros : listaDeVerticesSumidero) {
                this.digrafo.insertarArista(posVerticesSumideros,(this.digrafo.cantidaDeVertices()-1) , 1000);   
            }
        }
    }

    private void insertarAristasConPesoCero() throws ExcepcionAristaYaExiste {
        for (int i = 0; i < this.digrafo.cantidaDeVertices(); i++) {
            Iterable<Integer> adyacentesDeVertice = this.digrafo.adyacenteDeVertice(i);
            for (Integer posDeVerticeAdy : adyacentesDeVertice) {
                this.digrafo.insertarArista(posDeVerticeAdy, i, 0);
            }
        }
    }
    
    public  void fordFulkerson() throws ExcepcionAristaNoExiste {
        while (caminoAlSumidero() != null) {            
            
        }
    }
    
    private Object caminoAlSumidero() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    /* private AristaConPeso encontrarMayorCamino(Integer verticeActual) throws ExcepcionAristaNoExiste {
        List<Integer> adyacentesDelVertice = (List<Integer>) this.digrafo.adyacenteDeVertice(verticeActual);
        AristaConPeso mayorAristaConPeso = new AristaConPeso(verticeActual,adyacentesDelVertice.get(0),
                this.digrafo.peso(verticeActual, adyacentesDelVertice.get(0)));
        for (Integer posVerticeAdy : adyacentesDelVertice) {
            AristaConPeso aristaConPesoActual = new AristaConPeso(verticeActual,adyacentesDelVertice.get(posVerticeAdy),
                    this.digrafo.peso(verticeActual, adyacentesDelVertice.get(posVerticeAdy)));
            if (mayorAristaConPeso.compareTo(aristaConPesoActual) < 0) {
                mayorAristaConPeso = aristaConPesoActual;
            }
        }
        return mayorAristaConPeso;
    }*/


    
}
