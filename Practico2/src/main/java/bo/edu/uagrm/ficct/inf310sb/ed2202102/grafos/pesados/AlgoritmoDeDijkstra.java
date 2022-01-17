/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.RecorridoUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Andres
 */
public class AlgoritmoDeDijkstra {
    DiGrafoPesado diGrafoPesado;
    private List<Double> listaCostos;
    private List<Integer> listaPredecesores;
    private RecorridoUtils controlDeMarcados;

    public AlgoritmoDeDijkstra(DiGrafoPesado unDiGrafoPesado) {
        this.diGrafoPesado = unDiGrafoPesado;
        listaCostos = new LinkedList<>();
        listaPredecesores = new LinkedList<>();
        controlDeMarcados = new RecorridoUtils(diGrafoPesado.cantidaDeVertices());
    }

    private void inicializarListaCostos(int posVerticeOrigen) {
        listaCostos = new LinkedList<>();
        for (int i = 0; i < this.diGrafoPesado.cantidaDeVertices(); i++) {
            if (i == posVerticeOrigen) {
                listaCostos.add(i,0.0);
            }else { 
                listaCostos.add(i,Double.POSITIVE_INFINITY);
            }
        }
    }

    private void inicializarListaDePredecesores() {
        listaPredecesores = new LinkedList<>();
        for (int i = 0; i < this.diGrafoPesado.cantidaDeVertices(); i++) {
            listaPredecesores.add(-1);
        }
    }
    
    public void encontrarCaminoYcostosMinimos(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        diGrafoPesado.validarVertice(posVerticeOrigen);
        diGrafoPesado.validarVertice(posVerticeDestino);        
        inicializarListaCostos(posVerticeOrigen);        
        inicializarListaDePredecesores();
        this.controlDeMarcados.desmarcarTodos();
        
        while (!controlDeMarcados.estaVerticeMarcado(posVerticeDestino)) {
            int verticeMenorPesoNoMarcado = getVerticeMenorPesoNoMarcado();
            if (verticeMenorPesoNoMarcado == -1) {
                throw new ExcepcionAristaNoExiste("No existe camino al vertice destino " + posVerticeDestino);
            }
            Iterable<Integer> adyacentesDelVertice = diGrafoPesado.adyacenteDeVertice(verticeMenorPesoNoMarcado);
            for (int posDeverticeAdy: adyacentesDelVertice) {
                if (!controlDeMarcados.estaVerticeMarcado(posDeverticeAdy)) {
                    Double costoDeVerticeV = listaCostos.get(verticeMenorPesoNoMarcado);
                    Double costoDeVerticeA = listaCostos.get(posDeverticeAdy);
                    Double pesoDeIrDeVaA = diGrafoPesado.peso(verticeMenorPesoNoMarcado,posDeverticeAdy);
                    if (costoDeVerticeA > (costoDeVerticeV + pesoDeIrDeVaA)) {
                        listaCostos.set(posDeverticeAdy, costoDeVerticeV + pesoDeIrDeVaA);
                        listaPredecesores.set(posDeverticeAdy, verticeMenorPesoNoMarcado);
                    }
                }
            }
            controlDeMarcados.marcarVertice(verticeMenorPesoNoMarcado);
        }
        
    }

    private int getVerticeMenorPesoNoMarcado() {
        int verticeMenorPeso = controlDeMarcados.getMenorVerticeNoMarcado();
        if (verticeMenorPeso != -1) {
            for (int i = 0; i < listaCostos.size(); i++) {
                if (!controlDeMarcados.estaVerticeMarcado(i)) {
                    Double pesoDelVerticeMenorPeso = listaCostos.get(verticeMenorPeso);
                    Double pesoDelVerticeActual = listaCostos.get(i);
                    if (pesoDelVerticeMenorPeso > pesoDelVerticeActual) {
                        verticeMenorPeso = i;
                    }
                }
            }
            if (listaCostos.get(verticeMenorPeso) == Double.POSITIVE_INFINITY) {
                verticeMenorPeso = -1;
            }
        }
        return verticeMenorPeso;
    }

    public Double getCostoMinimo(int posVerticeDestino){
        return this.listaCostos.get(posVerticeDestino);
    }
    
    public List<Integer> getCaminoCostoMinimo(int posVerticeDestino) {
        List<Integer> caminoDeCostoMinimo = new LinkedList<>();
        caminoDeCostoMinimo.add(posVerticeDestino);
        int posVerticeEnTurno = posVerticeDestino;
        while (this.listaPredecesores.get(posVerticeEnTurno) != -1) {            
            ((LinkedList)caminoDeCostoMinimo).addFirst(this.listaPredecesores.get(posVerticeEnTurno));
            posVerticeEnTurno = this.listaPredecesores.get(posVerticeEnTurno);
        }
        return caminoDeCostoMinimo;
    }
    
    
}
