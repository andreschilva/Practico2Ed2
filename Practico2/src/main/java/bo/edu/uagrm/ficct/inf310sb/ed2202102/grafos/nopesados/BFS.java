/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Andres
 */
public class BFS {
    private RecorridoUtils controlDeMarcados;
    private Grafo grafo;
    private List<Integer> recorrido;
    
    public BFS(Grafo unGrafo, int posDeVerticeDePartida) {
        this.grafo = unGrafo;
        grafo.validarVertice(posDeVerticeDePartida);
        recorrido = new LinkedList<>();
        controlDeMarcados = new RecorridoUtils(grafo.cantidaDeVertices());
        ejecutarBFS(posDeVerticeDePartida);
    }
    
    private void ejecutarBFS(int posDeVerticeDePartida) {
        Queue<Integer> cola = new LinkedList<>();
        cola.offer(posDeVerticeDePartida);
        controlDeMarcados.marcarVertice(posDeVerticeDePartida);
        
        do {
            int posVerticeEnTurno = cola.poll();
            recorrido.add(posVerticeEnTurno);
            Iterable<Integer> adyacenteDeVerticeEnTurno = grafo.adyacenteDeVertice(posVerticeEnTurno);
            for (Integer posDeverticeAdy : adyacenteDeVerticeEnTurno) {
                if (!controlDeMarcados .estaVerticeMarcado(posDeverticeAdy)) {
                    cola.offer(posDeverticeAdy);
                    controlDeMarcados.marcarVertice(posDeverticeAdy);
                }
                
            }
        } while (!cola.isEmpty());
        
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
    
}
