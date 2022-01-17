/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Andres
 */
public class DFSGrafoPesadoNoDirigido {

    private RecorridoUtils controlDeMarcados;
    private GrafoPesado grafo;
    private List<Integer> recorrido;

    public DFSGrafoPesadoNoDirigido(GrafoPesado unGrafo) {
        this.grafo = unGrafo;
        recorrido = new LinkedList<>();
        controlDeMarcados = new RecorridoUtils(grafo.cantidaDeVertices());
    }
    
    public DFSGrafoPesadoNoDirigido(GrafoPesado unGrafo, int posDeVerticeDePartida) {
        this.grafo = unGrafo;
        grafo.validarVertice(posDeVerticeDePartida);
        recorrido = new LinkedList<>();
        controlDeMarcados = new RecorridoUtils(grafo.cantidaDeVertices());
        procesarDFS(posDeVerticeDePartida);
    }



    public void procesarDFS(int posDeVerticeEnTurno) {
        controlDeMarcados.marcarVertice(posDeVerticeEnTurno);
        recorrido.add(posDeVerticeEnTurno);
        Iterable<Integer> adyacenteDeVerticeenTurno = grafo.adyacenteDeVertice(posDeVerticeEnTurno);
        for (Integer posDeverticeAdy : adyacenteDeVerticeenTurno) {
            if (!controlDeMarcados.estaVerticeMarcado(posDeverticeAdy)) {
                procesarDFS(posDeverticeAdy);
            }
        }
    }


    public boolean hayCicloGrafoNoDirigido() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        boolean hayCiclos = false;
        GrafoPesado grafoAux = new GrafoPesado(this.grafo.cantidaDeVertices());
        int posVertice = 0;
        while (!this.controlDeMarcados.estanTodosMarcados()) {
            hayCiclos = this.hayCicloGrafoNoDirigido(posVertice, grafoAux);
            if (hayCiclos) {
                return hayCiclos;
            }
            posVertice = controlDeMarcados.getMenorVerticeNoMarcado();
        }
        return hayCiclos;
    }

    public boolean hayCicloGrafoNoDirigido(int posDeVerticeEnTurno, GrafoPesado grafoAux) throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        controlDeMarcados.marcarVertice(posDeVerticeEnTurno);
        Iterable<Integer> adyacenteDeVerticeenTurno = this.grafo.adyacenteDeVertice(posDeVerticeEnTurno);
        for (Integer posDeverticeAdy : adyacenteDeVerticeenTurno) {
            if (!controlDeMarcados.estaVerticeMarcado(posDeverticeAdy)) {
                Double pesoDeArista = this.grafo.peso(posDeVerticeEnTurno, posDeverticeAdy);
                grafoAux.insertarArista(posDeVerticeEnTurno, posDeverticeAdy, pesoDeArista);
                if (hayCicloGrafoNoDirigido(posDeverticeAdy, grafoAux)) {
                    return true;
                }

            } else {
                if (!grafoAux.existeAdyacencia(posDeVerticeEnTurno, posDeverticeAdy)) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean hayCaminosATodos() {
        return controlDeMarcados.estanTodosMarcados();
    }

    public Iterable<Integer> obtenerRecorrido() {
        return this.recorrido;
    }

    public boolean hayCaminoAVertice(int posDeVerticeDestino) {
        grafo.validarVertice(posDeVerticeDestino);
        return controlDeMarcados.estaVerticeMarcado(posDeVerticeDestino);
    }

    public boolean esConexoGrafoNoDirigido() {
        this.procesarDFS(0);
        if (this.controlDeMarcados.estanTodosMarcados()) {
            return true;
        }
        return false;
    }

    public int cantIslasGrafoNoDirigido() {
        int posVertice = 0;
        int cantidadDeIslas = 0;
        while (!this.controlDeMarcados.estanTodosMarcados()) {
            this.procesarDFS(posVertice);
            cantidadDeIslas++;
            while (controlDeMarcados.estaVerticeMarcado(posVertice) && posVertice < grafo.cantidaDeVertices() - 1) {
                posVertice++;
            }
        }
        return cantidadDeIslas;
    }



}
