/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class Grafo {
    protected List<List<Integer>> listaDeAdyacencias;
    
    public Grafo() {
        this.listaDeAdyacencias = new LinkedList<>();
    }
    
    public Grafo(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        if (nroDeVertices <= 0) {
            throw new ExcepcionNumVerticesInvalido();
        }
        this.listaDeAdyacencias = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.insertarVertice();
        }
    }

    public void insertarVertice() {
        List<Integer> adyacentesDeVerticesAInsertar = new LinkedList<>();
        this.listaDeAdyacencias.add(adyacentesDeVerticesAInsertar);
    }
    
    public  int cantidaDeVertices() {
        return listaDeAdyacencias.size();
    }
    
    public int gradoDeVertice(int posdeVertices) {
        validarVertice(posdeVertices);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posdeVertices);
        return adyacentesDelVertice.size();
        
    }

    public void validarVertice(int posdeVertices) {
        if (posdeVertices < 0 || posdeVertices >= this.cantidaDeVertices()) {
            throw new IllegalArgumentException("No existe vertice en la posicion "
            + posdeVertices + " en su grafo");
        }
        
    }
    
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste{
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw  new  ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelVerticeOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelVerticeOrigen);
        
        if (posVerticeOrigen != posVerticeDestino) {
            List<Integer> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            adyacentesDelVerticeDestino.add(posVerticeOrigen);
            Collections.sort(adyacentesDelVerticeDestino); 
        }
        
    }
    
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        return adyacentesDelVerticeOrigen.contains(posVerticeDestino);
        
    }
    
    public Iterable<Integer> adyacenteDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        Iterable<Integer> iterableDeAdyacentesDeVertice = adyacentesDelVertice;
        return iterableDeAdyacentesDeVertice;
    }
    
    public int cantidadDeAristas() {
        int contarAdyacencias = 0;
        int contarLazos = 0;
        for (int i = 0; i < this.cantidaDeVertices(); i++) {
            List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(i);
            for (int j = 0; j < adyacentesDelVertice.size(); j++) {
                if (adyacentesDelVertice.get(j) != i) {
                    contarAdyacencias++;
                }else {
                    contarLazos++;
                }
            }
        }
        contarAdyacencias = contarAdyacencias / 2;
        return contarAdyacencias + contarLazos;
    }
    
    public void eliminarVertice(int posVerticeAEliminar) {
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<Integer> adyacentesDeUnVertice : this.listaDeAdyacencias) {
            int posicionDeVerticeAEliminarEnAdy = adyacentesDeUnVertice.indexOf(posVerticeAEliminar);
            if (posicionDeVerticeAEliminarEnAdy >= 0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeAEliminarEnAdy);
            }
            
            for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                int posicionDeAdyacente = adyacentesDeUnVertice.get(i);
                if (posicionDeAdyacente > posVerticeAEliminar) {
                    adyacentesDeUnVertice.set(i, posicionDeAdyacente - 1 );
                }
            }
        }
        
    }
    
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);   
        int posAristaAeliminarEnElOrigen = adyacentesDelVerticeOrigen.indexOf(posVerticeDestino);
        if (posAristaAeliminarEnElOrigen >= 0) {
            adyacentesDelVerticeOrigen.remove(posAristaAeliminarEnElOrigen);
        }
        
        List<Integer> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(posVerticeDestino);   
        int posAristaAeliminarEnELDestino = adyacentesDelVerticeDestino.indexOf(posVerticeOrigen);
        if (posAristaAeliminarEnELDestino >= 0) {
            adyacentesDelVerticeDestino.remove(posAristaAeliminarEnELDestino);
        }
    }
    
    public boolean hayCicloConDFS() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        DFSGrafoNoDirigido recorridoDFS = new DFSGrafoNoDirigido(this);
        return recorridoDFS.hayCicloGrafoNoDirigido();
    }
    
    public boolean esConexo() {
       DFSGrafoNoDirigido recorridoDFS = new DFSGrafoNoDirigido(this);
        return recorridoDFS.esConexoGrafoNoDirigido();
        
    }
    
    public int cantidadDeIslas() {
        DFSGrafoNoDirigido recorridoDFS = new DFSGrafoNoDirigido(this);
        return recorridoDFS.cantIslasGrafoNoDirigido();
    }
    
    public List<List<Integer>> componentesDeIslas() {
        DFSGrafoNoDirigido recorridoDFS = new DFSGrafoNoDirigido(this);
        return recorridoDFS.componentesDeIslasGrafo();
    }
    
    public List<Integer> recorridoDFS(int posVerticePartida) {
        DFSGrafoNoDirigido recorridoDFS = new DFSGrafoNoDirigido(this,posVerticePartida);
        return (List<Integer>) recorridoDFS.obtenerRecorrido();
    }
    
    
}


