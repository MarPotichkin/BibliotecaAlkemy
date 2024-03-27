package com.miapp.biblioteca.service;

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
}
