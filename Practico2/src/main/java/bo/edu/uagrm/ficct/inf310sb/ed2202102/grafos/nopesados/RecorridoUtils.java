/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.nopesados;

import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Andres
 */
public class RecorridoUtils {
    private List<Boolean> marcados;
    
    public RecorridoUtils(int nroDeVertices) {
        marcados = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            marcados.add(Boolean.FALSE);
        }
    } 
    
    public void desmarcarTodos() {
        for (int i = 0; i < marcados.size(); i++) {
            marcados.set(i,Boolean.FALSE);
        }
    }
    
    
    /**
     * Retorna verdadero para indicar que un vertice esta marcado. Falso en caso 
     * contrario.
     * PRE: se asume que la posicion de vertice es una posicion valida 
     * @param posDeVertice
     * @return 
     */
    public boolean estaVerticeMarcado(int posDeVertice) {
        return this.marcados.get(posDeVertice);
    }
    
    public boolean  estanTodosMarcados() {
        for (Boolean marcado : marcados) {
            if (!marcado) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * PRE-Condicion: La posicion de vertice, es una posicion valida 
     * @param posDeVertice 
     */
    public void marcarVertice(int posDeVertice) {
        this.marcados.set(posDeVertice, Boolean.TRUE);
    }
    
    //falta implementar
    public boolean tieneAdyacenteMarcado(int posDeVertice) {
        return true;
        
    }
    
    public int getMenorVerticeNoMarcado() {
        for (int i = 0; i < marcados.size(); i++) {
            if (!this.estaVerticeMarcado(i)) {
                return i;
            }
        }
        return -1;
    }    
}
