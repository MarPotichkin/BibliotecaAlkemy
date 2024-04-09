package com.miapp.biblioteca.ui;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.service.LibroServicio;
import com.miapp.biblioteca.service.UsuarioServicio;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {

        ArrayList<Libro> biblioteca = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        LibroServicio libroServicio = new LibroServicio(biblioteca);
        UsuarioServicio usuarioServicio = new UsuarioServicio(usuarios);
        Scanner scanner = new Scanner(System.in);

        int accion;

        do {
            System.out.println("--- Biblioteca ALKEMY ---");
            System.out.println("1. Gestión de Libros");
            System.out.println("2. Gestión de Usuarios");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción");
            accion = scanner.nextInt();

            switch (accion){
                case 1:
                    gestionDeLibros(biblioteca, usuarios, libroServicio, usuarioServicio);
                    break;
                case 2:
                    gestionDeUsuarios(biblioteca, usuarios, libroServicio, usuarioServicio);
                    break;
                case 3:
                    System.out.println("Que tenga un buen día");
                    break;
                default:
                    System.out.println("Opción ingresada no válida");
            }
        } while (accion != 3);

    }

    public static void gestionDeLibros(ArrayList<Libro> biblioteca, ArrayList<Usuario> usuarios,LibroServicio libroServicio,UsuarioServicio usuarioServicio){
        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("--- MENÚ: GESTIÓN DE LIBROS ---");
            System.out.println("1. Crear Libro");
            System.out.println("2. Actualizar libro");
            System.out.println("3. Buscar libro por ISBN");
            System.out.println("4. Buscar libro por título");
            System.out.println("5. Listar libros");
            System.out.println("6. Eliminar libro");
            System.out.println("7. Préstamos");
            System.out.println("8. Devoluciones");
            System.out.println("9. Volver al menú principal");

            System.out.println("Seleccione una opción");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                // Nuevo Libro
                case 1:
                    System.out.println("Ingrese el título: ");
                    String titulo = scanner.nextLine();

                    System.out.println("Ingrese autor: ");
                    String autor = scanner.nextLine();

                    System.out.println("Ingrese ISBN: ");
                    String isbn = scanner.nextLine();

                    System.out.println("Ingrese el género");
                    String genero = scanner.nextLine();

                    libroServicio.crearLibro(titulo, autor, isbn, genero);
                    break;

                // Actualizar Libro
                case 2:
                    System.out.println("Ingrese el ISBN del libro: ");
                    String isbnActualizado = scanner.nextLine();

                    System.out.println("Ingrese el título nuevo: ");
                    String tituloActualizado = scanner.nextLine();

                    System.out.println("Ingrese el autor: ");
                    String autorActualizado =  scanner.nextLine();

                    System.out.println("Ingrese el género: ");
                    String generoActualizado = scanner.nextLine();

                    libroServicio.actualizarLibro(isbnActualizado, tituloActualizado, autorActualizado, generoActualizado);
                    break;

                // Buscar por ISBN
                case 3:
                    System.out.println("Ingrese el ISBN");
                    String isbnBusqueda = scanner.nextLine();

                    Libro libroPorISBN = libroServicio.buscarLibroPorISBN(isbnBusqueda);

                    if (libroPorISBN != null){
                        System.out.println("Libro: " + libroPorISBN.getTitulo());
                    } else {
                        System.out.println("No hay ningún libro registrado con ese ISBN");
                    }
                    break;

                // Buscar por título
                case 4:
                    System.out.println("Ingrese el título: ");
                    String tituloBusqueda = scanner.nextLine();

                    ArrayList<Libro> librosPorTitulo = libroServicio.buscarLibrosPorTitulo(tituloBusqueda);

                    if (!librosPorTitulo.isEmpty()){
                        System.out.println("Libros encontrados: ");
                        for (Libro libro: librosPorTitulo){
                            System.out.println(libro.getTitulo());
                        }
                    } else {
                        System.out.println("No se encontró ningún libro con ese título");
                    }
                    break;

                // Listar Libros
                case 5:
                    ArrayList<Libro> listarLibros = libroServicio.obtenerLibros();

                    for (Libro  libro: listarLibros) {
                        System.out.println(libro.getTitulo() + " - " + libro.getISBN());
                    }
                    break;

                // Eliminar Libros
                case 6:
                    System.out.println("Ingresar el ISBN del libro: ");
                    String isbnEliminar = scanner.nextLine();

                    libroServicio.eliminarLibro(isbnEliminar);
                    break;

                // Préstamos
                case 7:
                    System.out.println("Ingresar el número de usuario: ");
                    String idUsuario = scanner.nextLine();
                    Usuario usuarioPrestamo = usuarioServicio.buscarUsuarioPorId(idUsuario);
                    if (usuarioPrestamo != null) {
                        System.out.println("Ingresar el ISBN del libro: ");
                        String isbnPrestamo = scanner.nextLine();
                        Libro libroPrestamo = libroServicio.buscarLibroPorISBN(isbnPrestamo);

                        if (libroPrestamo != null){
                            if (libroServicio.verificarDisponibilidad(libroPrestamo)){
                                usuarioServicio.prestarLibro(usuarioPrestamo, libroPrestamo);
                                System.out.println("Préstamo realizado a: " + usuarioPrestamo);
                            } else {
                                System.out.println("El libro no se encuentra disponible");
                            }
                        } else {
                            System.out.println("Libro no encontrado");
                        }
                    } else {
                        System.out.println("Usuario no encontrado");
                    }
                    break;

                // Devoluciones
                case 8:
                    System.out.println("Ingrese el número de usuario");
                    String idUsuarioDevolucion = scanner.nextLine();
                    Usuario usuarioDevolucion = usuarioServicio.buscarUsuarioPorId(idUsuarioDevolucion);

                    if (usuarioDevolucion != null){
                        System.out.println("Ingrese el ISBN del libro: ");
                        String isbnDevolucion = scanner.nextLine();
                        Libro libroDevolucion = libroServicio.buscarLibroPorISBN(isbnDevolucion);
                        if (libroDevolucion != null) {
                            usuarioServicio.devolverLibro(usuarioDevolucion, libroDevolucion);
                            System.out.println("Devolución realizada por: " + usuarioDevolucion.getNombre());
                        } else {
                            System.out.println("Libro no encontrado");
                        }
                    } else {
                        System.out.println("Usuario no encontrado");
                    }
                    break;

                // Salir
                case 9:
                    System.out.println("Redireccionando al menú principal");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo");
            }

        } while (opcion != 9) ;
    }

    public static void gestionDeUsuarios(ArrayList<Libro> biblioteca, ArrayList<Usuario> usuarios,LibroServicio libroServicio,UsuarioServicio usuarioServicio){
        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("--- MENÚ: GESTIÓN DE USUARIOS ---");
            System.out.println("1. Dar de alta a un usuario");
            System.out.println("2. Actualizar usuario");
            System.out.println("3. Buscar usuario por id");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Eliminar usuario");
            System.out.println("6. Volver al menú principal");

            System.out.println("Seleccione una opción");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                // Nuevo Usuario
                case 1:
                    System.out.println("Ingrese el nombre del usuario: ");
                    String nombre = scanner.nextLine();

                    System.out.println("Ingrese el id");
                    String id = scanner.nextLine();

                    usuarioServicio.crearUsuario(nombre, id);
                    break;

                // Actualizar Usuario
                case 2:
                    System.out.println("Ingrese el usuario: ");
                    String nombreActualizado = scanner.nextLine();

                    System.out.println("Ingrese el id: ");
                    String idUsuario = scanner.nextLine();

                    usuarioServicio.actualizarUsuario(idUsuario, nombreActualizado);
                    break;

                // Buscar por ID
                case 3:
                    System.out.println("Ingrese el ID");
                    String idUsuarioBusqueda = scanner.nextLine();

                    Usuario usuarioPorID = usuarioServicio.buscarUsuarioPorId(idUsuarioBusqueda);

                    if (usuarioPorID != null){
                        System.out.println("Usuario: " + usuarioPorID.getNombre());
                    } else {
                        System.out.println("No hay ningún usuario registrado con ese ID");
                    }
                    break;

                // Listar Usuarios
                case 4:
                    ArrayList<Usuario> listarUsuarios = usuarioServicio.obtenerUsuarios();

                    for (Usuario usuario: listarUsuarios) {
                        System.out.println(usuario.getNombre() + " - " + usuario.getId());
                    }
                    break;

                // Eliminar Usuarios
                case 5:
                    System.out.println("Ingresar el ID del usuario: ");
                    String idUsuarioEliminar = scanner.nextLine();

                    usuarioServicio.eliminarUsuario(idUsuarioEliminar);
                    break;

                // Salir
                case 6:
                    System.out.println("Redireccionando al menú principal");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo");
            }

        } while (opcion != 6) ;
    }

}

