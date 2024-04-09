package com.miapp.biblioteca.service;

import com.miapp.biblioteca.Libro;

import java.util.ArrayList;
import java.util.Iterator;

public class LibroServicio {
    private ArrayList<Libro> biblioteca;

    public LibroServicio(ArrayList<Libro> biblioteca){
        this.biblioteca = biblioteca;
    }

    //Crear
    public void crearLibro(String titulo, String autor, String ISBN, String genero){
        Libro nuevoLibro = new Libro(titulo, autor, ISBN, genero);
        biblioteca.add(nuevoLibro);
    }

    //Listado de libros
    public ArrayList<Libro> obtenerLibros(){
        return biblioteca;
    }

    //Actualizar
    public void actualizarLibro(String ISBN, String nuevoTitulo, String nuevoAutor, String nuevoGenero){
        for (Libro libro: biblioteca){
            if (libro.getISBN().equals(ISBN)){
                libro.setTitulo(nuevoTitulo);
                libro.setAutor(nuevoAutor);
                libro.setGenero(nuevoGenero);
            }
        }
    }

    public void eliminarLibro(String ISBN){
        Iterator<Libro> it= biblioteca.iterator();

        while (it.hasNext()){
            if (it.next().getISBN().equals(ISBN)){
                it.remove();
            }
        }
    }

    public Libro buscarLibroPorISBN(String ISBN) {
        for (Libro libro: biblioteca){
            if (libro.getISBN().equals(ISBN)){
                return libro;
            }
        }
        return null;
    }

    public ArrayList<Libro> buscarLibrosPorTitulo(String titulo){
        ArrayList<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : biblioteca) {
            if(libro.getTitulo().equalsIgnoreCase(titulo)){
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    public boolean verificarDisponibilidad(Libro libro){
        return libro.isDisponible();
    }


}
