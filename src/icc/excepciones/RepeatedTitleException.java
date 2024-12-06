
package icc.excepciones;

/**
 * Indica titulo repetido.
 */
public class RepeatedTitleException extends Exception {

    /**
     * Constructor.
     */
    public RepeatedTitleException() {
        super();
    }

    /**
     * Constructor.
     *
     * @param message Informacion detallada sobre el contexto en que ocurre
     * la excepcion.
     */
    public RepeatedTitleException(String message){
        super(message);
    }
}
