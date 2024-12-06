
package icc.excepciones;

/**
 * Indica tamaños no adecuados para una estructura.
 */
public class IllegalSizeException extends Exception {

    /**
     * Constructor.
     */
    public IllegalSizeException() {
        super();
    }

    /**
     * Constructor.
     *
     * @param message Informacion detallada sobre el contexto en que ocurre
     * la excepcion.
     */
    public IllegalSizeException(String message){
        super(message);
    }
}
