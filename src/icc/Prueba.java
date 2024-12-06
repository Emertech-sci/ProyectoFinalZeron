package icc;
import icc.colors.Colors;
import icc.multimedia.Libros;
import icc.multimedia.Peliculas;
import icc.multimedia.Discos;
import java.util.Scanner;

/**
 * Clase principal para ejecutar el programa.
 * Gestiona la interacción con del usuario con las opciones disponibles del menú principal y los manus secundarios.
 * Permire la gestión de libros, discos y películas.
 */
public class Prueba {
    /**
     * getInt(): Método que solicita un número entero dentro de un rango definido.
     * Si el valor ingresado no es válido, solicita la entrada nuevamente hasta que sea correcta.
     * 
     * @param mensaje Mensaje que se muestra al usuario para indicar lo que debe ingresar.
     * @param error Mensaje de error que se muestra si la entrada es inválida.
     * @param min Valor mínimo permitido para la entrada.
     * @param max Valor máximo permitido para la entrada.
     * @return Un número entero dentro del rango especificado.
     */
    public static int getInt(String mensaje, String error, int min, int max){ 
        Scanner scan = new Scanner(System.in); 

        while (true){
            Colors.println(mensaje, Colors.HIGH_INTENSITY);

            if(scan.hasNextInt()){
                int valor = scan.nextInt();
                if(valor < min || max < valor){
                    Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
                }else{
                    return valor;
                }
            }else{ 
                scan.next();
                Colors.println(error, Colors.RED + Colors.HIGH_INTENSITY);
            }  
        } // Ciere de while
    } // Cierre de getInteger
  
    /**
     * Método principal que ejecuta el programa de administración de archivos multimedia.
     * Gestiona la interacción con el usuario para administrar libros, discos y películas
     * y poder realizar busquedas y manipulación de archivos nsado en distintos parametros.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int selectMenu;

        /**
         * MENSAJE DE BINEVENIDA Y MENÚ PRICNICPAL
         * Se realiza la impresión de un menú de bienvenida mediante la clase Colors y su método println.
         * Se emplea un StringBuilder para la creación del menú principal de opciones para la interacción del usuario.
         */
        Colors.println("Bienvenido\nEste programa te ayuda a mantener en orden tus colecciones.\n", Colors.CYAN + Colors.HIGH_INTENSITY); // Mensaje de bienvenida.
        System.out.println("Selecciona la colección que te gustaría ver:\n"); // Solicitud de selección de acción al usuario.

        StringBuilder sb = new StringBuilder();
        //sb = new StringBuilder();
        sb.append("1. Ver tus libros\n");
        sb.append("2. Ver tus discos\n");
        sb.append("3. Ver tus peliculas\n");
        sb.append("0. Salir del programa");
        String menuPrincipal = sb.toString();

        Prueba colecciones = new Prueba();

            /**
            * EJECUCIÓN DEL MENÚ
            * Aqui usamos un métdo que ejecuta el menu 1 mientras el torneo toma lugar.
            * Una vez se decide temrinar el torneo arroja el menu final 
            * Consultamos puntajes, campeon y enfrentameintos.
            **/
            do{
                Colors.println("Menú principal.\n", Colors.CYAN + Colors.HIGH_INTENSITY);
                selectMenu = getInt(menuPrincipal, "Por favor ingresa una opción que sea valida:", 0, 3);

                switch(selectMenu){
                    case 1:
                        Colors.println("Colección de Libros.\n", Colors.CYAN + Colors.HIGH_INTENSITY);
                        Libros.gestorLibros();
                        break;

                    case 2:
                        Colors.println("Colección de Discos.\n", Colors.CYAN + Colors.HIGH_INTENSITY);
                        Discos.gestorDiscos();
                        break;

                    case 3: 
                        Colors.println("Colección de Peliculas.\n", Colors.CYAN + Colors.HIGH_INTENSITY);
                        Peliculas.gestorPeliculas();
                        break;

                    case 0:
                        Colors.println("Gracias por usar el programa.", Colors.GREEN + Colors.HIGH_INTENSITY);
                        break;
                }
            }while(selectMenu != 0);
        //sc.close();
    }//Cierre del método main
}// CIERRE DE LA CLASE PRUEBA. 


//El arreglo qu contenga a los valores sea con polimorfismo
// Que devuelva un arreglo
// Que las nbusquedas reciban arreglos y devuelvan arreglos