/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Andres
 */
public class DFSGrafoNoDirigido {

    private RecorridoUtils controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;

    public DFSGrafoNoDirigido(Grafo unGrafo) {
        this.grafo = unGrafo;
        recorrido = new LinkedList<>();
        controlDeMarcados = new RecorridoUtils(grafo.cantidaDeVertices());
    }
    
    public DFSGrafoNoDirigido(Grafo unGrafo, int posDeVerticeDePartida) {
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


    public boolean hayCicloGrafoNoDirigido() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        boolean hayCiclos = false;
        Grafo grafoAux = new Grafo(this.grafo.cantidaDeVertices());
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

    public boolean hayCicloGrafoNoDirigido(int posDeVerticeEnTurno, Grafo grafoAux) throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        controlDeMarcados.marcarVertice(posDeVerticeEnTurno);
        Iterable<Integer> adyacenteDeVerticeenTurno = grafo.adyacenteDeVertice(posDeVerticeEnTurno);
        for (Integer posDeverticeAdy : adyacenteDeVerticeenTurno) {
            if (!controlDeMarcados.estaVerticeMarcado(posDeverticeAdy)) {
                grafoAux.insertarArista(posDeVerticeEnTurno, posDeverticeAdy);
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
    
    public List<List<Integer>> componentesDeIslasGrafo() {
        int posVertice = 0;
        List<List<Integer>> componentesDeIslas = new LinkedList<>();
        while (!this.controlDeMarcados.estanTodosMarcados()) {
            this.procesarDFS(posVertice);
            List<Integer> listaAux = new LinkedList<>();
            for (Integer pos : this.recorrido) {
                listaAux.add(pos);
            }
            componentesDeIslas.add(listaAux);
            this.recorrido.clear();
            posVertice = controlDeMarcados.getMenorVerticeNoMarcado();
        }
        return componentesDeIslas ;
    }    


}
