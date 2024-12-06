package icc.multimedia;

import icc.Prueba;
import icc.colors.Colors;
import icc.files.ReaderWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Peliculas {
    protected String titulo;
    protected String genero;
    protected String actor;
    protected int año;

    public Peliculas(String titulo, String genero, String actor, int año) {
        this.titulo = titulo;
        this.genero = genero;
        this.actor = actor;
        this.año = año;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + ", Genero: " + genero + ", Actor: " + actor + ", año: " + año;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getActor() {
        return actor;
    }

    public int getAño() {
        return año;
    }




    /**
     * Método que lee un archivo de texto y lo convierte en una lista de objetos Peliculas.
     * @param archivo
     * @return
     */
    public static List<Peliculas> leerPeliculas(String archivo) {
        List<Peliculas> peliculas = new ArrayList<>();
        String[] lineas = ReaderWriter.read(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(",");
            Peliculas pelicula = new Peliculas(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
            peliculas.add(pelicula);
        }
        return peliculas;
    }




    public static List<Peliculas> buscarPorTitulo(String titulo, List<Peliculas> peliculas) {
        List<Peliculas> resultado = new ArrayList<>();
        for (Peliculas pelicula : peliculas) {
            if (pelicula.getTitulo().equalsIgnoreCase(titulo)) {
                resultado.add(pelicula);
            }
        }
        return resultado;
    }




    public static List<Peliculas> buscarPorGenero(String genero, List<Peliculas> peliculas) {
        List<Peliculas> resultado = new ArrayList<>();
        for (Peliculas pelicula : peliculas) {
            if (pelicula.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(pelicula);
            }
        }
        return resultado;
    }




    public static List<Peliculas> buscarPorActor(String actor, List<Peliculas> peliculas) {
        List<Peliculas> resultado = new ArrayList<>();
        for (Peliculas pelicula : peliculas) {
            if (pelicula.getActor().equalsIgnoreCase(actor)) {
                resultado.add(pelicula);
            }
        }
        return resultado;
    }




    public static List<Peliculas> buscarPorAño(int año, List<Peliculas> peliculas) {
        List<Peliculas> resultado = new ArrayList<>();
        for (Peliculas pelicula : peliculas) {
            if (pelicula.getAño() == año) {
                resultado.add(pelicula);
            }
        }
        return resultado;
    }




    public static void consultarColeccionCompleta(){
        String[] lineas = ReaderWriter.read("peliculas.csv");

        for(String linea : lineas){
            String[] datos = linea.split(",");
            Peliculas pelicula = new Peliculas(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
            Colors.println(pelicula.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
        }
    }




    public static void agregarPelicula(String archivo, Peliculas pelicula) {
        String nuevaPelicula = pelicula.getTitulo() + "," + pelicula.getGenero() + "," + pelicula.getActor() + "," + pelicula.getAño();
        ReaderWriter.write(nuevaPelicula, archivo);
    }




    public static void gestorPeliculas() {
        Scanner sc = new Scanner(System.in);
        int menuPeliculas;
        do{
            StringBuilder sb = new StringBuilder();
            sb.append("1. Busqueda por año.\n");
            sb.append("2. Busqueda por actores y actrices.\n");
            sb.append("3. Busqueda por genero.\n");
            sb.append("4. Consultar la colección completa.\n");
            sb.append("5. Agrega una pelicula a tu colección\n");
            sb.append("0. Volver al menú principal");
            String menu1 = sb.toString();

            menuPeliculas = Prueba.getInt(menu1, "Por el momento no podemos realizar esa acción, pero puedes seleccionar las siguientes:\n", 0, 5);
            List<Peliculas> peliculas = leerPeliculas("peliculas.csv");

            switch (menuPeliculas) {
                case 1:
                    Colors.println("Introduce el año de la pelicula:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    int año = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea pendiente
                    List<Peliculas> resultadoAño = buscarPorAño(año, peliculas);
                    for (Peliculas pelicula : resultadoAño) {
                        Colors.println(pelicula.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;
                
                case 2:
                    Colors.println("Introduce el actor o actriz de la pelicula:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String actor = sc.nextLine();
                    List<Peliculas> resultadoActor = buscarPorActor(actor, peliculas);
                    for (Peliculas pelicula : resultadoActor) {
                        Colors.println(pelicula.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 3:
                    Colors.println("Introduce el genero:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String genero = sc.nextLine();
                    List<Peliculas> resultadoGenero = buscarPorGenero(genero, peliculas);
                    for (Peliculas pelicula : resultadoGenero) {
                        Colors.println(pelicula.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 4:
                    List<Peliculas> coleccion = leerPeliculas("peliculas.csv");
                    for (Peliculas pelicula : coleccion) {
                        Colors.println(pelicula.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 5:
                    Colors.println("Introduce el título de la pelicula:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoTitulo = sc.nextLine();
                    Colors.println("Introduce el género de la pelicula:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoGenero = sc.nextLine();
                    Colors.println("Introduce el actor de la pelicula:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoActor = sc.nextLine();
                    Colors.println("Introduce el año de la pelicula:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    int nuevoAño = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea pendiente
                    Peliculas nuevaPelicula = new Peliculas(nuevoTitulo, nuevoGenero, nuevoActor, nuevoAño);
                    agregarPelicula("peliculas.csv", nuevaPelicula);
                    Colors.println("Pelicula agregada exitosamente.", Colors.GREEN + Colors.HIGH_INTENSITY);
                    break;

            }
        }while(menuPeliculas != 0);
    }// Cierre de gestorLibros



}//cierre de clase Peliculas
