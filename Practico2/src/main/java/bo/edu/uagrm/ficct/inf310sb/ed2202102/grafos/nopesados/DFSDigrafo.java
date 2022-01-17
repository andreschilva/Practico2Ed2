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
public class DFSDigrafo {

    private RecorridoTresEstadosUtils controlDeMarcadosTresEstados;
    private RecorridoUtils controlDeMarcados;
    private DiGrafo diGrafo;
    private List<Integer> recorrido;


    public DFSDigrafo(DiGrafo unGrafo) {
        this.diGrafo = unGrafo;
        recorrido = new LinkedList<>();
        controlDeMarcadosTresEstados = new RecorridoTresEstadosUtils(diGrafo.cantidaDeVertices());
        controlDeMarcados = new RecorridoUtils(diGrafo.cantidaDeVertices());
    }    
    
    public DFSDigrafo(DiGrafo unGrafo, int posDeVerticeDePartida) {
        this.diGrafo = unGrafo;
        diGrafo.validarVertice(posDeVerticeDePartida);
        recorrido = new LinkedList<>();
        controlDeMarcadosTresEstados = new RecorridoTresEstadosUtils(diGrafo.cantidaDeVertices());
        controlDeMarcados = new RecorridoUtils(diGrafo.cantidaDeVertices());
        procesarDFS(posDeVerticeDePartida);
    }


    public void procesarDFS(int posDeVerticeEnTurno) {
        controlDeMarcados.marcarVertice(posDeVerticeEnTurno);
        recorrido.add(posDeVerticeEnTurno);
        Iterable<Integer> adyacenteDeVerticeenTurno = diGrafo.adyacenteDeVertice(posDeVerticeEnTurno);
        for (Integer posDeverticeAdy : adyacenteDeVerticeenTurno) {
            if (!controlDeMarcados.estaVerticeMarcado(posDeverticeAdy)) {
                procesarDFS(posDeverticeAdy);
            }
        }
    }


    public boolean hayCaminosATodos() {
        return controlDeMarcados.estanTodosMarcados();
    }

    public Iterable<Integer> obtenerRecorrido() {
        return this.recorrido;
    }

    public boolean hayCaminoAVertice(int posDeVerticeDestino) {
        diGrafo.validarVertice(posDeVerticeDestino);
        return controlDeMarcados.estaVerticeMarcado(posDeVerticeDestino);
    }



    public boolean esFuertementeConexoDiGrafo() {
        for (int i = 0; i < diGrafo.cantidaDeVertices(); i++) {
            this.procesarDFS(i);
            if (!this.controlDeMarcados.estanTodosMarcados()) {
                return false;
            }
            this.controlDeMarcados.desmarcarTodos();
        }
        return true;
    }

    public boolean esDebilmenteConexoDiGrafo() {
        int posVertice = 0;
        while (!this.controlDeMarcados.estanTodosMarcados()) {
            this.procesarDFS(posVertice);
            posVertice = this.encontrarVerticeConAdyMarcado();
            if (posVertice == -1 && !this.controlDeMarcados.estanTodosMarcados()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean hayCicloDiGrafo() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        boolean hayCiclos = false;
        int posVertice = 0;
        while (!this.controlDeMarcadosTresEstados.estanTodosMarcados()) {
            hayCiclos = this.hayCicloDiGrafo(posVertice);
            if (hayCiclos) {
                return hayCiclos;
            }
            posVertice = controlDeMarcadosTresEstados.getMenorVerticeNoMarcado();
            
        }
        return hayCiclos;
    }
    
    public boolean hayCicloDiGrafo(int posDeVerticeEnTurno) {

        if (controlDeMarcadosTresEstados.estaVerticeMarcadoPrimerEstado(posDeVerticeEnTurno)) {
            controlDeMarcadosTresEstados.marcarVerticeSegundoEstado(posDeVerticeEnTurno);
        }

        Iterable<Integer> adyacenteDeVerticeEnTurno = diGrafo.adyacenteDeVertice(posDeVerticeEnTurno);
        if (/*estanAdyacentesDeVerticeMarcados(adyacenteDeVerticeEnTurno) || */(diGrafo.gradoDeSalidaDeVertice(posDeVerticeEnTurno) == 0)) {
            controlDeMarcadosTresEstados.marcarVerticeTercerEstado(posDeVerticeEnTurno);
        }
        for (Integer posDeverticeAdy : adyacenteDeVerticeEnTurno) {
            if (controlDeMarcadosTresEstados.estaVerticeMarcadoPrimerEstado(posDeverticeAdy)) {
                if (hayCicloDiGrafo(posDeverticeAdy)) {
                    return true;
                }
            } else if (!controlDeMarcadosTresEstados.estaVerticeMarcadoTercerEstado(posDeverticeAdy)) {
                return true;
            }

        }
        controlDeMarcadosTresEstados.marcarVerticeTercerEstado(posDeVerticeEnTurno);
        return false;
    }

    private boolean estanAdyacentesDeVerticeMarcados(Iterable<Integer> adyacenteDeVerticeEnTurno) {
        for (Integer posDeverticeAdy : adyacenteDeVerticeEnTurno) {
            if (controlDeMarcadosTresEstados.estaVerticeMarcadoPrimerEstado(posDeverticeAdy)) {
                return false;
            }
        }
        return true;

    }

    public int cantIslasDiGrafo() {
        int posVertice = 0;
        int cantidadDeIslas = 0;
        while (!this.controlDeMarcados.estanTodosMarcados()) {
            this.procesarDFS(posVertice);
            posVertice = encontrarVerticeConAdyMarcado();
            if (posVertice == -1) {
                cantidadDeIslas++;
                posVertice = controlDeMarcados.getMenorVerticeNoMarcado();
            }  
        }
        return cantidadDeIslas;
    }

   /* private boolean tieneAdyacentesMarcados(int posDeVertice) {
        Iterable<Integer> adyacentesDelVertice = this.diGrafo.adyacenteDeVertice(posDeVertice);
        for (Integer posDeverticeAdy : adyacentesDelVertice) {
            if (controlDeMarcados.estaVerticeMarcado(posDeverticeAdy)) {
                return true;
            }
        }
        return false;
    }*/

    private int encontrarVerticeConAdyMarcado() {
        for (int i = 0; i < diGrafo.cantidaDeVertices(); i++) {
            if (!controlDeMarcados.estaVerticeMarcado(i)) {
                Iterable<Integer> adyacentesDelVertice = this.diGrafo.adyacenteDeVertice(i);
                for (Integer posDeverticeAdy : adyacentesDelVertice) {
                    if (controlDeMarcados.estaVerticeMarcado(posDeverticeAdy)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public List<List<Integer>> componentesDeIslasDiGrafo() {
        int posVertice = 0;
        List<List<Integer>> componentesDeIslas = new LinkedList<>();
        while (!this.controlDeMarcados.estanTodosMarcados()) {
            this.procesarDFS(posVertice);
            posVertice = encontrarVerticeConAdyMarcado();
            if (posVertice == -1) {
                List<Integer> listaAux = new LinkedList<>();
                for (Integer pos : this.recorrido) {
                    listaAux.add(pos);
                }
                componentesDeIslas.add(listaAux);
                this.recorrido.clear();
                posVertice = controlDeMarcados.getMenorVerticeNoMarcado();
            }  
        }
        return componentesDeIslas;
                
    } 
    
   /* public int cantDeComponentesFuertementeConexos() {
        int posVertice = 0;
        int cantidadDeIslas = 0;
        while (!this.controlDeMarcados.estanTodosMarcados()) {
            this.procesarDFS(posVertice);
            posVertice = encontrarVerticeConAdyMarcado();
            if (posVertice == -1) {
                cantidadDeIslas++;
                posVertice = controlDeMarcados.getMenorVerticeNoMarcado();
            }  
        }
        return cantidadDeIslas;
    }   */ 
    
    


}
