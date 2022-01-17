/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;


import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Andres
 */
public class DiGrafoPesado extends GrafoPesado{
    public DiGrafoPesado() {
       super();
   } 
   
   public DiGrafoPesado(int nroDeVertice) throws ExcepcionNumVerticesInvalido {
       super(nroDeVertice);
   }
   
    @Override
   public int cantidadDeAristas() {
        int contarAdyacencias = 0;
        for (int i = 0; i < this.cantidaDeVertices(); i++) {
            List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(i);
            contarAdyacencias += adyacentesDelVertice.size();
        }
        return contarAdyacencias;       
   }
   
    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double  peso) throws ExcepcionAristaYaExiste{
        if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw  new  ExcepcionAristaYaExiste();
        }
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
        AdyacenteConPeso adyacenteDelOrigen = new AdyacenteConPeso(posVerticeDestino,peso);
        adyacentesDelVerticeOrigen.add(adyacenteDelOrigen);
        Collections.sort(adyacentesDelVerticeOrigen);
        
    }
    
    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);   
        AdyacenteConPeso posAdyacenteDelDestino = new AdyacenteConPeso(posVerticeDestino);
        int posAristaAeliminar = adyacentesDelVerticeOrigen.indexOf(posAdyacenteDelDestino);
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
            List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(i);
             AdyacenteConPeso posAdyacenteConPeso = new AdyacenteConPeso(posDeVertice);
            int posDeVerTiceEnAdyacencia = adyacentesDelVertice.indexOf(posAdyacenteConPeso);
            if (posDeVerTiceEnAdyacencia >= 0) {
                grado++;
            }
        }
        
        return grado;
    }
    
    public int gradoDeSalidaDeVertice(int posDeVertice) {
        return super.gradoDeVertice(posDeVertice); 
    }    
    
    public boolean esFuertementeConexoConRecorridoDFS() {
        DFSDigrafoPesado recorridoDFS = new DFSDigrafoPesado(this);
        return recorridoDFS.esFuertementeConexoDiGrafo();
    }    
    
     public boolean hayCicloConDFS() throws ExcepcionNumVerticesInvalido, ExcepcionAristaYaExiste {
        DFSDigrafoPesado recorridoDFS = new DFSDigrafoPesado(this);
        return recorridoDFS.hayCicloDiGrafo();
    }     
    
    
    public int cantDeIslasConRecorridoDFS() {
        DFSDigrafoPesado recorridoDFS = new DFSDigrafoPesado(this);
        return recorridoDFS.cantIslasDiGrafo();
    } 
    
    public boolean esDebilmenteConexoConRecorridoDFS() {
        DFSDigrafoPesado recorridoDFS = new DFSDigrafoPesado(this);
        return recorridoDFS.esDebilmenteConexoDiGrafo();
    }    
    
    @Override
    public List<Integer> recorridoDFS(int posVerticePartida) {
        DFSDigrafoPesado recorridoDFS = new DFSDigrafoPesado(this,posVerticePartida);
        return (List<Integer>) recorridoDFS.obtenerRecorrido();
    }    
}
