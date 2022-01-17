/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class GrafoPesado {
    protected List<List<AdyacenteConPeso>> listaDeAdyacencias;

    public GrafoPesado() {
        this.listaDeAdyacencias = new LinkedList<>();
    }
    
    public GrafoPesado(int nroDeVertices) throws ExcepcionNumVerticesInvalido {
        if (nroDeVertices <= 0) {
            throw new ExcepcionNumVerticesInvalido();
        }
        this.listaDeAdyacencias = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            this.insertarVertice();
        }
    }

    public void insertarVertice() {
        List<AdyacenteConPeso> adyacentesDeVerticesAInsertar = new LinkedList<>();
        this.listaDeAdyacencias.add(adyacentesDeVerticesAInsertar);
    }
    
    public  int cantidaDeVertices() {
        return listaDeAdyacencias.size();
    }
    
    public int gradoDeVertice(int posdeVertices) {
        validarVertice(posdeVertices);
        List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posdeVertices);
        return adyacentesDelVertice.size();    
    }

    public void validarVertice(int posdeVertices) {
        if (posdeVertices < 0 || posdeVertices >= this.cantidaDeVertices()) {
            throw new IllegalArgumentException("No existe vertice en la posicion"
            + posdeVertices + "en su grafo");
        }
    }
    
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double peso) throws ExcepcionAristaYaExiste{
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw  new  ExcepcionAristaYaExiste();
        }
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino,peso); 
        adyacentesDelVerticeOrigen.add(adyacenteDelOrigen);
        Collections.sort(adyacentesDelVerticeOrigen);
        
        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(posVerticeDestino);
            AdyacenteConPeso adyacenteDelDestino = new AdyacenteConPeso(posVerticeOrigen,peso);
            adyacentesDelVerticeDestino.add(adyacenteDelDestino);
            Collections.sort(adyacentesDelVerticeDestino); 
        }
        
    }
    
    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino);
        return adyacentesDelVerticeOrigen.contains(adyacenteDelOrigen);
        
    }
    
    public Iterable<Integer> adyacenteDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
        List<Integer> soloVertices = new ArrayList<>();
        for (AdyacenteConPeso adyacenteConPeso : adyacentesDelVertice) {
            soloVertices.add(adyacenteConPeso.getIndiceDeVertice());
        }
        Iterable<Integer> iterableDeAdyacentesDeVertice = soloVertices;
        return iterableDeAdyacentesDeVertice;
    }
    
    public int cantidadDeAristas() {
        int contarLazosDobles = 0;
        int contarLazosEnSiMismo = 0;
        for (int i = 0; i < this.cantidaDeVertices(); i++) {
            List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(i);
            for (int j = 0; j < adyacentesDelVertice.size(); j++) {
                AdyacenteConPeso posAdyacente = adyacentesDelVertice.get(j);
                if (posAdyacente.getIndiceDeVertice() != i) {
                    contarLazosDobles++;
                }else {
                    contarLazosEnSiMismo++;
                }
            }
        }
        contarLazosDobles = contarLazosDobles / 2;
        return contarLazosDobles + contarLazosEnSiMismo;
    }
    
    public void eliminarVertice(int posVerticeAEliminar) {
        validarVertice(posVerticeAEliminar);
        this.listaDeAdyacencias.remove(posVerticeAEliminar);
        for (List<AdyacenteConPeso> adyacentesDeUnVertice : this.listaDeAdyacencias) {
            AdyacenteConPeso unAdyacenteConPeso  = new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeAEliminarEnAdy = adyacentesDeUnVertice.indexOf(unAdyacenteConPeso);
            if (posicionDeVerticeAEliminarEnAdy >= 0) {
                adyacentesDeUnVertice.remove(posicionDeVerticeAEliminarEnAdy);
            }
            
            for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                AdyacenteConPeso adyacenteEnTurno = adyacentesDeUnVertice.get(i);
                if (adyacenteEnTurno.getIndiceDeVertice() > posVerticeAEliminar) {
                    adyacenteEnTurno.setIndiceDeVertice(adyacenteEnTurno.getIndiceDeVertice() - 1);
                }
            }
        }
        
    }
    
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);   
        AdyacenteConPeso unAdyacenteConPesoDest = new AdyacenteConPeso(posVerticeDestino);
        int posAristaAeliminar = adyacentesDelVerticeOrigen.indexOf(unAdyacenteConPesoDest);
        if (posAristaAeliminar >= 0) {
            adyacentesDelVerticeOrigen.remove(posAristaAeliminar);
        }
        
        List<AdyacenteConPeso> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(posVerticeDestino);   
        AdyacenteConPeso unAdyacenteConPesoOrig = new AdyacenteConPeso(posVerticeOrigen);
        int posAristaAeliminarDelOrigen = adyacentesDelVerticeDestino.indexOf(unAdyacenteConPesoOrig);
        if (posAristaAeliminarDelOrigen >= 0) {
            adyacentesDelVerticeDestino.remove(posAristaAeliminarDelOrigen);
        }
    }
    
    public double peso(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste { 
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }
        List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso unAdyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino);
        int posicionDeLaAdyacencia = adyacentesDelOrigen.indexOf(unAdyacenteDelOrigen);
        AdyacenteConPeso adyacenteDelOrigenReal = adyacentesDelOrigen.get(posicionDeLaAdyacencia);
        return adyacenteDelOrigenReal.getPeso();
    }
    
    public boolean hayCiclo() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste, ExcepcionAristaNoExiste {
        DFSGrafoPesadoNoDirigido recorridoDFS = new DFSGrafoPesadoNoDirigido(this);
        return recorridoDFS.hayCicloGrafoNoDirigido();
    }
    
    public boolean esConexo() {
       DFSGrafoPesadoNoDirigido recorridoDFS = new DFSGrafoPesadoNoDirigido(this);
        return recorridoDFS.esConexoGrafoNoDirigido();
        
    }
    
    public int cantidadDeIslas() {
        DFSGrafoPesadoNoDirigido recorridoDFS = new DFSGrafoPesadoNoDirigido(this);
        return recorridoDFS.cantIslasGrafoNoDirigido();
    }

    public List<Integer> recorridoDFS(int posVerticePartida) {
        DFSGrafoPesadoNoDirigido recorridoDFS = new DFSGrafoPesadoNoDirigido(this,posVerticePartida);
        return (List<Integer>) recorridoDFS.obtenerRecorrido();
    }
}
