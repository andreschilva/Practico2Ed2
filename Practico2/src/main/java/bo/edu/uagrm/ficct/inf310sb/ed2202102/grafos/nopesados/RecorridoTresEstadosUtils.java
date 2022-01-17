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
public class RecorridoTresEstadosUtils {
    private static final int PRIMER_ESTADO = 0;
    private static final int SEGUNDO_ESTADO = 1;
    private static final int TERCER_ESTADO = -1;
    
    private List<Integer> marcados;
    
    public RecorridoTresEstadosUtils(int nroDeVertices) {
        marcados = new LinkedList<>();
        for (int i = 0; i < nroDeVertices; i++) {
            marcados.add(PRIMER_ESTADO);
        }
    } 
    
    public void desmarcarTodos() {
        for (int i = 0; i < marcados.size(); i++) {
            marcados.set(i,PRIMER_ESTADO);
        }
    }
    
    
    public boolean estaVerticeMarcadoPrimerEstado(int posDeVertice) {
        return this.marcados.get(posDeVertice) == 0;
    }
    
    public boolean estaVerticeMarcadoSegundoEstado(int posDeVertice) {
        return this.marcados.get(posDeVertice) == 1;
    }    
    
    
    public boolean estaVerticeMarcadoTercerEstado(int posDeVertice) {
        return this.marcados.get(posDeVertice) == -1;
    }    
    public boolean  estanTodosMarcados() {
        for (int marcado : marcados) {
            if ( marcado != -1) {
                return false;
            }
        }
        return true;
    }
    
    public int getMenorVerticeNoMarcado() {
        for (int i = 0; i < marcados.size(); i++) {
            if (this.estaVerticeMarcadoPrimerEstado(i)) {
                return i;
            }
        }
        return -1;
    } 
    
    public void marcarVerticeSegundoEstado(int posDeVertice) {
        this.marcados.set(posDeVertice,SEGUNDO_ESTADO);
    }
    
    public void marcarVerticeTercerEstado(int posDeVertice) {
        this.marcados.set(posDeVertice,TERCER_ESTADO);
    }
    
}
