/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Andres
 */
public class DiGrafo extends Grafo{

    public DiGrafo() {
       super();
   } 
   
   public DiGrafo(int nroDeVertice) throws ExcepcionNumVerticesInvalido {
       super(nroDeVertice);
   }
   
    @Override
   public int cantidadDeAristas() {
        int contarAdyacencias = 0;
        for (int i = 0; i < this.cantidaDeVertices(); i++) {
            List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(i);
            contarAdyacencias += adyacentesDelVertice.size();
        }
        return contarAdyacencias;       
   }
   
    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste{
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw  new  ExcepcionAristaYaExiste();
        }
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        adyacentesDelVerticeOrigen.add(posVerticeDestino);
        Collections.sort(adyacentesDelVerticeOrigen);
        
    }
    
    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);   
        int posAristaAeliminar = adyacentesDelVerticeOrigen.indexOf(posVerticeDestino);
        if (posAristaAeliminar >= 0) {
            adyacentesDelVerticeOrigen.remove(posAristaAeliminar);
        }    
    }  
    
   @Override
    public int gradoDeVertice(int posDeVertice) {
        //return super.gradoDeVertice(posDeVertice);
        throw new UnsupportedOperationException("Metodo no soportado en grafos dirigidos");
    }    
    
    public int gradoDeEntradaDeVertice(int posDeVertice) {
        int grado = 0;
        for (int i = 0; i < this.cantidaDeVertices(); i++) {
            List<Integer> adyacentesDelVertice = this.listaDeAdyacencias.get(i);
            int posDeVerTiceEnAdyacencia = adyacentesDelVertice.indexOf(posDeVertice);
            if (posDeVerTiceEnAdyacencia >= 0) {
                grado++;
            }
        }
        
        return grado;
    }
    
    public int gradoDeSalidaDeVertice(int posDeVertice) {
        return super.gradoDeVertice(posDeVertice); 
    }
    
    public void eliminarAdyacentesDelVertice(int posVertice) {
        List<Integer> listaDeAdyacentes = this.listaDeAdyacencias.get(posVertice);
        listaDeAdyacentes.clear();
    }
    
    public boolean esFuertementeConexoConRecorridoDFS() {
        DFSDigrafo recorridoDFS = new DFSDigrafo(this);
        return recorridoDFS.esFuertementeConexoDiGrafo();
    }
    
    public boolean esFuertementeConexoConMatriz() {
        MatrizDeCaminos matriz = new MatrizDeCaminos(this);
        return matriz.esFuertementeConexo();
    }    
    
    public boolean hayCicloConMatriz() {
        MatrizDeCaminos matriz = new MatrizDeCaminos(this);
        return matriz.hayCiclo();
    }     
    
   @Override
    public boolean hayCicloConDFS() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        DFSDigrafo recorridoDFS = new DFSDigrafo(this);
        return recorridoDFS.hayCicloDiGrafo();
    }     
    
    
    public int cantDeIslasConRecorridoDFS() {
        DFSDigrafo recorridoDFS = new DFSDigrafo(this);
        return recorridoDFS.cantIslasDiGrafo();
    } 
    
    public boolean esDebilmenteConexoConRecorridoDFS() {
        DFSDigrafo recorridoDFS = new DFSDigrafo(this);
        return recorridoDFS.esDebilmenteConexoDiGrafo();
    }    
    
   @Override
    public List<List<Integer>> componentesDeIslas() {
        DFSDigrafo recorridoDFS = new DFSDigrafo(this);
        return recorridoDFS.componentesDeIslasDiGrafo();
    }

    @Override
    public List<Integer> recorridoDFS(int posVerticePartida) {
        DFSDigrafo recorridoDFS = new DFSDigrafo(this,posVerticePartida);
        return (List<Integer>) recorridoDFS.obtenerRecorrido();
    }    
}
