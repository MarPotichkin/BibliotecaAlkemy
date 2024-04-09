package com.miapp.biblioteca.service;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;

import java.util.ArrayList;
import java.util.Iterator;

public class UsuarioServicio {
    private ArrayList<Usuario> usuarios;

    public  UsuarioServicio(ArrayList<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    //Crear
    public void crearUsuario(String nombre, String id){
        Usuario nuevoUsuario = new Usuario(nombre, id);
        usuarios.add(nuevoUsuario);
    }

    //Listar
    public ArrayList<Usuario> obtenerUsuarios(){
        return usuarios;
    }

    //Actualizar
    public void actualizarUsuario(String id, String nuevoNombre){
        for(Usuario usuario : usuarios){
            if(usuario.getId().equals(id)){
                usuario.setNombre(nuevoNombre);
                return;
            }
        }
    }

    public void eliminarUsuario(String id){
        Iterator<Usuario> it = usuarios.iterator();

        while (it.hasNext()){
            if(it.next().getId().equals(id)){
                it.remove();
            }
        }
    }

    public void prestarLibro(Usuario usuario, Libro libro) {
        if (libro.isDisponible()){
            usuario.getLibrosPrestados().add(libro);
            libro.setDisponible(false);
        } else {
            System.out.println("El libro está disponible");
        }
    }

    public Usuario buscarUsuarioPorId(String id){
        for (Usuario usuario: usuarios) {
            if (usuario.getId().equalsIgnoreCase(id)){
                return usuario;
            }
        }
        return null;
    }

    public void devolverLibro(Usuario usuario, Libro libro){
        if (usuario.getLibrosPrestados().contains(libro)) {
            usuario.getLibrosPrestados().remove(libro);
            libro.setDisponible(true);
        } else {
            System.out.println("No se prestó el libro a este usuario");
        }
    }

    public ArrayList<Libro> obtenerLibrosPrestados(Usuario usuario){
        return usuario.getLibrosPrestados();
    }
}
