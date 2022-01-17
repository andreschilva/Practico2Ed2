/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.pesados;

import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaNoExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionAristaYaExiste;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones.ExcepcionNumVerticesInvalido;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.Grafo;
import bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados.RecorridoUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class AlgoritmoDeKruskal {
    private GrafoPesado grafoOriginal;
    private GrafoPesado grafoAux;
    private List<AristaConPeso> listaDeAristaConPeso;
    private RecorridoUtils controlDeMarcados;
    
    public AlgoritmoDeKruskal(GrafoPesado grafoOriginal) throws ExcepcionNumVerticesInvalido, ExcepcionAristaNoExiste, ExcepcionAristaYaExiste {
        this.grafoOriginal = grafoOriginal;
        this.grafoAux = new GrafoPesado(grafoOriginal.cantidaDeVertices());
        controlDeMarcados = new RecorridoUtils(grafoOriginal.cantidaDeVertices());
        listaDeAristaConPeso = new LinkedList<>();
        cargarYOrdenarListaDeAristaConPeso();
        insertarAristasEnGrafoAuxiliar();
    }

    private void cargarYOrdenarListaDeAristaConPeso() throws ExcepcionAristaNoExiste {
        for (int i = 0; i < grafoOriginal.cantidaDeVertices(); i++) {
            Iterable<Integer> adyacentesDelVertice = grafoOriginal.adyacenteDeVertice(i);
            for (Integer posDeverticeAdy : adyacentesDelVertice) {
                AristaConPeso unaAristaConPeso = new AristaConPeso(posDeverticeAdy,i);
                int posDeArista = this.listaDeAristaConPeso.indexOf(unaAristaConPeso);
                if (posDeArista == -1) {
                    Double costoDeArista = this.grafoOriginal.peso(i, posDeverticeAdy);
                    AristaConPeso otraAristaConPeso = new AristaConPeso(i,posDeverticeAdy,costoDeArista);
                    this.listaDeAristaConPeso.add(otraAristaConPeso);
                }
            }
        }
        Collections.sort(listaDeAristaConPeso);
    }
    
    private void insertarAristasEnGrafoAuxiliar() throws ExcepcionAristaYaExiste, ExcepcionNumVerticesInvalido, ExcepcionAristaNoExiste {
        for (int i = 0; i < listaDeAristaConPeso.size(); i++) {
            AristaConPeso unaAritaConPeso = listaDeAristaConPeso.get(i);
            this.grafoAux.insertarArista(unaAritaConPeso.getOrigen(),unaAritaConPeso.getDestino(),unaAritaConPeso.getCosto());
            if (this.grafoAux.hayCiclo()) {
                this.grafoAux.eliminarArista(unaAritaConPeso.getOrigen(),unaAritaConPeso.getDestino());
            }
        }
    }
      

    public GrafoPesado getGrafoAux() {
        return grafoAux;
    }
    
    
}
