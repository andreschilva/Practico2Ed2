/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package bo.edu.uagrm.ficct.inf310sb.ed2202102.grafos.excepciones;

/**
 *
 * @author Andres
 */
public class ExcepcionAristaNoExiste extends Exception{

    /**
     * Creates a new instance of <code>ExcepcionNumVerticesInvalido</code>
     * without detail message.
     */
    public ExcepcionAristaNoExiste() {
        super("Arista No existe en su grafo");
    }

    /**
     * Constructs an instance of <code>ExcepcionNumVerticesInvalido</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExcepcionAristaNoExiste(String msg) {
        super(msg);
    }
}
