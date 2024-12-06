package icc.multimedia;

import icc.Prueba;
import icc.colors.Colors;
import icc.files.ReaderWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libros {    
    protected String titulo;
    protected String genero;
    protected String autor;
    protected int id;





    public Libros(String titulo, String genero, String autor, int id) {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.id = id;
    }





    @Override
    public String toString() {
        return "Titulo: " + titulo + ", Genero: " + genero + ", Autor: " + autor + ", ID: " + id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getAutor() {
        return autor;
    }

    public int getId() {
        return id;
    }





    /**
     * Método que lee un archivo de texto y lo convierte en una lista de objetos Libros.
     * @param archivo
     * @return
     */
    public static List<Libros> leerLibros(String archivo) {
        List<Libros> libros = new ArrayList<>();
        String[] lineas = ReaderWriter.read(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(",");
            Libros libro = new Libros(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
            libros.add(libro);
        }
        return libros;
    }




    /**
     * Método que busca un libro por su título.
     * @param titulo
     * @param libros
     * @return
     */
    public static List<Libros> buscarPorTitulo(String titulo, List<Libros> libros) {
        List<Libros> resultado = new ArrayList<>();
        for (Libros libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }




    /**
     * Método que busca un libro por su género.
     * @param genero
     * @param libros
     * @return
     */
    public static List<Libros> buscarPorGenero(String genero, List<Libros> libros) {
        List<Libros> resultado = new ArrayList<>();
        for (Libros libro : libros) {
            if (libro.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }
    
    


    /**
     * Método que busca un libro por su autor.
     * @param autor
     * @param libros
     * @return
     */
    public static List<Libros> buscarPorAutor(String autor, List<Libros> libros) {
        List<Libros> resultado = new ArrayList<>();
        for (Libros libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }




    /**
     * Método que consulta la colección completa de libros.
     */
    public static void consultarColeccionCompleta(){
        String[] lineas = ReaderWriter.read("libros.txt");

        for(String linea : lineas){
            String[] datos = linea.split(",");
            Libros libro = new Libros(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
            Colors.println(libro.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
        }
    }




    /**
     * Método para agregar un libro nuevo a la colección.
     */
    public static void agregarLibro(String archivo, Libros libro) {
        String nuevoLibro = libro.getTitulo() + "," + libro.getGenero() + "," + libro.getAutor() + "," + libro.getId();
        ReaderWriter.write(nuevoLibro, archivo);
    }




    /**
     * Método que permite crear un menú para la gestión de libros
     * que interactua con el usuario para permitir ya sea consulta o modificación
     * de la colección de libros.
     */
    public static void gestorLibros() {
        Scanner sc = new Scanner(System.in);
        int menuLibros;
        do{
            StringBuilder sb = new StringBuilder();
            sb.append("1. Busqueda por autor.\n");
            sb.append("2. Busqueda por genero.\n");
            sb.append("3. Busqueda por título de libro.\n");
            sb.append("4. Consultar la colección completa.\n");
            sb.append("5. Agrega un libro a tu colección\n");
            sb.append("0. Volver al menú principal");
            String menu1 = sb.toString();

            menuLibros = Prueba.getInt(menu1, "Por el momento no podemos realizar esa acción, pero puedes seleccionar las siguientes:\n", 0, 5);
            List<Libros> libros = leerLibros("libros.csv");

            switch (menuLibros) {
                case 1:
                    Colors.println("Introduce el nombre del autor:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String autor = sc.nextLine();
                    List<Libros> resultadoAutor = buscarPorAutor(autor, libros);
                    for (Libros libro : resultadoAutor) {
                        Colors.println(libro.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;
                
                case 2:
                    Colors.println("Introduce el género:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String genero = sc.nextLine();
                    List<Libros> resultadoGenero = buscarPorGenero(genero, libros);
                    for (Libros libro : resultadoGenero) {
                        Colors.println(libro.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 3:
                    Colors.println("Introduce el título del libro:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String titulo = sc.nextLine();
                    List<Libros> resultadoTitulo = buscarPorTitulo(titulo, libros);
                    for (Libros libro : resultadoTitulo) {
                        Colors.println(libro.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 4:
                    List<Libros> coleccion = leerLibros("libros.csv");
                    for (Libros libro : coleccion) {
                        Colors.println(libro.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 5:
                    Colors.println("Introduce el título del libro:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoTitulo = sc.nextLine();
                    Colors.println("Introduce el género del libro:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoGenero = sc.nextLine();
                    Colors.println("Introduce el autor del libro:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoAutor = sc.nextLine();
                    Colors.println("Introduce el ID del libro:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    int nuevoId = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea pendiente
                    Libros nuevoLibro = new Libros(nuevoTitulo, nuevoGenero, nuevoAutor, nuevoId);
                    agregarLibro("libros.csv", nuevoLibro);
                    Colors.println("Libro agregado exitosamente.", Colors.GREEN + Colors.HIGH_INTENSITY);
                    break;

            }
        }while(menuLibros != 0);
    }// Cierre de gestorLibros
}// Cierre de clase Libros