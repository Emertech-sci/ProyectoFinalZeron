package icc.multimedia;

import icc.Prueba;
import icc.colors.Colors;
import icc.files.ReaderWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Discos {
    protected String titulo;
    protected String genero;
    protected String interprete;
    protected int pistas;

    public Discos(String titulo, String genero, String interprete, int pistas) {
        this.titulo = titulo;
        this.genero = genero;
        this.interprete = interprete;
        this.pistas = pistas;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + ", Genero: " + genero + ", Interprete: " + interprete + ", pistas: " + pistas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getInterprete() {
        return interprete;
    }

    public int getPistas() {
        return pistas;
    }




    public static List<Discos> leerDiscos(String archivo){
        List<Discos> discos = new ArrayList<>();
        String[] lineas = ReaderWriter.read(archivo);

        for (String linea : lineas) {
            String[] datos = linea.split(",");
            Discos disco = new Discos(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
            discos.add(disco);
        }
        return discos;
    }




    public static List<Discos> buscarPorTitulo(String titulo, List<Discos> discos) {
        List<Discos> resultado = new ArrayList<>();
        for (Discos disco : discos) {
            if (disco.getTitulo().equalsIgnoreCase(titulo)) {
                resultado.add(disco);
            }
        }
        return resultado;
    }




    public static List<Discos> buscarPorGenero(String genero, List<Discos> discos) {
        List<Discos> resultado = new ArrayList<>();
        for (Discos disco : discos) {
            if (disco.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(disco);
            }
        }
        return resultado;
    }




    public static List<Discos> buscarPorInterprete(String interprete, List<Discos> discos) {
        List<Discos> resultado = new ArrayList<>();
        for (Discos disco : discos) {
            if (disco.getInterprete().equalsIgnoreCase(interprete)) {
                resultado.add(disco);
            }
        }
        return resultado;
    }




    public static List<Discos> buscarPorPistas(int pistas, List<Discos> discos, boolean mayorQue) {
        List<Discos> resultado = new ArrayList<>();
        for (Discos disco : discos) {
            if (mayorQue) {
                if (disco.getPistas() > pistas) {
                    resultado.add(disco);
                }
            } else {
                if (disco.getPistas() == pistas) {
                    resultado.add(disco);
                }
            }
        }
        return resultado;
    }




    public static void consultarColeccionCompleta(){
        String[] lineas = ReaderWriter.read("discos.csv");

        for(String linea : lineas){
            String[] datos = linea.split(",");
            Discos disco = new Discos(datos[0], datos[1], datos[2], Integer.parseInt(datos[3]));
            Colors.println(disco.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
        }
    }




    public static void agregarDisco(String archivo, Discos disco) {
        String nuevoDisco = disco.getTitulo() + "," + disco.getGenero() + "," + disco.getInterprete() + "," + disco.getPistas();
        ReaderWriter.write(nuevoDisco, archivo);
    }




    public static void gestorDiscos() {
        Scanner sc = new Scanner(System.in);
        int menuDiscos;
        do{
            StringBuilder sb = new StringBuilder();
            sb.append("1. Busqueda por interpretes.\n");
            sb.append("2. Busqueda por genero.\n");
            sb.append("3. Busqueda por discos de mas de 10 pistas.\n");
            sb.append("4. Consultar la colección completa.\n");
            sb.append("5. Agrega un disco a tu colección\n");
            sb.append("0. Volver al menú principal");
            String menu1 = sb.toString();

            menuDiscos = Prueba.getInt(menu1, "Por el momento no podemos realizar esa acción, pero puedes seleccionar las siguientes:\n", 0, 5);
            List<Discos> discos = leerDiscos("discos.csv");

            switch (menuDiscos) {
                case 1:
                    Colors.println("Introduce el interprete del disco:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String interprete = sc.nextLine();
                    List<Discos> resultadoInterprete = buscarPorInterprete(interprete, discos);
                    for (Discos disco : resultadoInterprete) {
                        Colors.println(disco.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;
                
                case 2:
                    Colors.println("Introduce el genero del disco:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String genero = sc.nextLine();
                    List<Discos> resultadoGenero = buscarPorGenero(genero, discos);
                    for (Discos disco : resultadoGenero) {
                        Colors.println(disco.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 3:
                    List<Discos> resultadoPistas = buscarPorPistas(10, discos, true);
                    for (Discos disco : resultadoPistas) {
                        Colors.println(disco.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 4:
                    List<Discos> coleccion = leerDiscos("discos.csv");
                    for (Discos disco : coleccion) {
                        Colors.println(disco.toString(), Colors.GREEN + Colors.HIGH_INTENSITY);
                    }
                    break;

                case 5:
                    Colors.println("Introduce el título del disco:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoTitulo = sc.nextLine();
                    Colors.println("Introduce el género del disco:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoGenero = sc.nextLine();
                    Colors.println("Introduce el interprete del disco:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    String nuevoInterprete = sc.nextLine();
                    Colors.println("Introduce el numero de pistas:", Colors.CYAN + Colors.HIGH_INTENSITY);
                    int nuevasPistas = sc.nextInt();
                    sc.nextLine(); // Consumir el salto de línea pendiente
                    Discos nuevoDisco = new Discos(nuevoTitulo, nuevoGenero, nuevoInterprete, nuevasPistas);
                    agregarDisco("peliculas.csv", nuevoDisco);
                    Colors.println("Pelicula agregada exitosamente.", Colors.GREEN + Colors.HIGH_INTENSITY);
                    break;

            }
        }while(menuDiscos != 0);
    }// Cierre de gestorLibros



}//cierre de clase Peliculas






    





    

