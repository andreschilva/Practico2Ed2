/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionHayCiclo;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Andres
 */
public class OrdenamientoTopologico {
    private DiGrafo digrafo;
    private DiGrafo digrafoAux;
    private Queue<Integer> colaDeVertices;
    private RecorridoUtils controlDeMarcados;

    public OrdenamientoTopologico(DiGrafo digrafo) throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        this.digrafo = digrafo;
        this.digrafoAux = new DiGrafo(digrafo.cantidaDeVertices());
        cargarDigrafoAux();
        this.colaDeVertices = new LinkedList<>();
        this.controlDeMarcados = new RecorridoUtils(this.digrafo.cantidaDeVertices());
    }
    
    public List<Integer> ordenar() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionHayCiclo {
        List<Integer> listaEnOrden = new LinkedList<>();
        if (this.digrafoAux.hayCicloConDFS()) {
            throw new ExcepcionHayCiclo();
        }
        cargarColaDeGrados();
        while (!this.colaDeVertices.isEmpty()) {
            listaEnOrden.add(colaDeVertices.poll());
            cargarColaDeGrados();
        }
        return listaEnOrden;
    }
    
    private void cargarColaDeGrados() {
        for (int i = 0; i < digrafoAux.cantidaDeVertices(); i++) {
            if ((digrafoAux.gradoDeEntradaDeVertice(i)== 0) && (!controlDeMarcados.estaVerticeMarcado(i))) {
                this.colaDeVertices.offer(i);
                this.controlDeMarcados.marcarVertice(i);
                this.digrafoAux.eliminarAdyacentesDelVertice(i);
            }
        }
    }

    private void cargarDigrafoAux() throws ExcepcionAristaYaExiste {
        for (int i = 0; i < digrafo.cantidaDeVertices(); i++) {
            Iterable<Integer> adyacentesDeVertice = digrafo.adyacenteDeVertice(i);
            for (Integer posAdyacentevertice : adyacentesDeVertice) {
                this.digrafoAux.insertarArista(i, posAdyacentevertice);
            }
        }
    }
    
    
    
}
