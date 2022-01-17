/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.RecorridoUtils;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class AlgoritmoDePrim {
    private GrafoPesado grafoOriginal;
    private GrafoPesado grafoAux;
    private List<Integer> verticesGrafoOriginal;
    private List<Integer> verticesGrafoAux;
    private RecorridoUtils controlDeMarcados;

    public AlgoritmoDePrim(GrafoPesado grafoOriginal, int posVerticePartida) throws ExcepcionAristaNoExiste, ExcepcionAristaYaExiste, ExcepcionNumVerticesInvalido {
        this.grafoOriginal = grafoOriginal;
        this.verticesGrafoOriginal = new LinkedList<>();
        cargarlistaVerticesGrafoOrig(posVerticePartida);
        this.grafoAux = new GrafoPesado(grafoOriginal.cantidaDeVertices());
        controlDeMarcados = new RecorridoUtils(grafoAux.cantidaDeVertices());
        this.verticesGrafoAux = new LinkedList<>();
        this.verticesGrafoAux.add(posVerticePartida);
        int posVertice = this.verticesGrafoOriginal.indexOf(posVerticePartida);
        this.verticesGrafoOriginal.remove(posVertice);
        this.prim();
    }

    private void cargarlistaVerticesGrafoOrig(int posVerticePartida) {
        
        DFSGrafoPesadoNoDirigido DFS = new DFSGrafoPesadoNoDirigido(this.grafoOriginal);
        DFS.procesarDFS(posVerticePartida);
        this.verticesGrafoOriginal = (List<Integer>) DFS.obtenerRecorrido();
    }
    
    private void  prim() throws ExcepcionAristaNoExiste, ExcepcionAristaYaExiste, ExcepcionNumVerticesInvalido {
        while (!this.verticesGrafoOriginal.isEmpty()) {            
             AristaConPeso menorAristaConPeso = getMenorAristaConPeso();
            this.grafoAux.insertarArista(menorAristaConPeso.getOrigen(), menorAristaConPeso.getDestino(), 
                    menorAristaConPeso.getCosto());
            this.verticesGrafoAux.add(menorAristaConPeso.getDestino());
            int posDeverticeEliminar = this.verticesGrafoOriginal.indexOf(menorAristaConPeso.getDestino());
            this.verticesGrafoOriginal.remove(posDeverticeEliminar);
            if (this.grafoAux.hayCiclo()) {
                this.grafoAux.eliminarArista(menorAristaConPeso.getOrigen(), menorAristaConPeso.getDestino());
            }
        }
    }

    private AristaConPeso getMenorAristaConPeso() throws ExcepcionAristaNoExiste {
        Double menorPesoArista = Double.MAX_VALUE;
        AristaConPeso menorAristaConPeso = new AristaConPeso(verticesGrafoAux.get(0), verticesGrafoOriginal.get(0),menorPesoArista);
        for (Integer posVerticesGrafoAux : this.verticesGrafoAux) {
            for (Integer posVerticesGrafoOrig : this.verticesGrafoOriginal) {
                if (this.grafoOriginal.existeAdyacencia(posVerticesGrafoAux, posVerticesGrafoOrig)) {
                    Double pesoAristaActual = this.grafoOriginal.peso(posVerticesGrafoAux, posVerticesGrafoOrig);
                    if (menorPesoArista > pesoAristaActual) {
                        menorPesoArista = pesoAristaActual;
                        menorAristaConPeso.setOrigen(posVerticesGrafoAux);
                        menorAristaConPeso.setDestino(posVerticesGrafoOrig);
                        menorAristaConPeso.setCosto(menorPesoArista);
                    }
                }
            }
        }
        return menorAristaConPeso;
    }
     

    public GrafoPesado getGrafoAux() {
        return grafoAux;
    }
    
    
}
