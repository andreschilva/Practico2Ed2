/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones;

/**
 *
 * @author Andres
 */
public class ExcepcionHayCiclo extends Exception{

    /**
     * Creates a new instance of <code>ExcepcionNumVerticesInvalido</code>
     * without detail message.
     */
    public ExcepcionHayCiclo() {
        super("Existee ciclo en el grafo");
    }

    /**
     * Constructs an instance of <code>ExcepcionNumVerticesInvalido</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionHayCiclo(String msg) {
        super(msg);
    }
}
