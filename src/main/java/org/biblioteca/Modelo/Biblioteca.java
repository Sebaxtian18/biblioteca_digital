package org.biblioteca.Modelo;

import org.biblioteca.Repositorio.IBuscar;
import org.biblioteca.Items_Bibliograficos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Biblioteca implements IBuscar {
    private List<Items_Bibliograficos> coleccionItems;
    private List<Usuario> listaUsuarios;

    public Biblioteca() {
        this.coleccionItems = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
    }

    public Biblioteca(List<Items_Bibliograficos> coleccionItems, List<Usuario> listaUsuarios) {
        this.coleccionItems = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario) throws IllegalArgumentException {
        if (usuario == null){
            throw new IllegalArgumentException("No se puede registrar un usuario vacío.");
        }
        if (usuario.getId() <=0){
            throw new IllegalArgumentException("El ID del usuario no puede ser 0 o un número negativo. ");
        }
        boolean idYaRegistrado = listaUsuarios.stream()
                .anyMatch(i -> i.getId() == usuario.getId());
        if (idYaRegistrado){
            throw new IllegalArgumentException("El ID del usuario ya esta registrado.");
        }
        listaUsuarios.add(usuario);
        System.out.println("El usuario '" + usuario.getNombre() + "' ( ID: " + usuario.getId() + " ) ha sido creado con exito");
    }

    public void registrarItem(Items_Bibliograficos item) throws IllegalArgumentException {
        if (item == null){
            throw new IllegalArgumentException("No se puede registrar un libro vacío.");
        }
        if (item.getId() == null || item.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del libro no puede ser nulo.");
        }
        boolean idYaRegistrado = coleccionItems.stream()
                .anyMatch(i -> i.getId().equals(item.getId()));
        if (idYaRegistrado){
            throw new IllegalArgumentException("El ID del libro ya esta registrado.");
        }
        coleccionItems.add(item);
        System.out.println("El libro '" + item.getTitulo() + "' ( ID: " + item.getId() + " ) ha sido registrado con exito.");
    }

    public Optional<Items_Bibliograficos> buscarItemPorID(String id){
        return coleccionItems.stream()
                .filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Usuario> buscarUsuarioPorID(int id){
        return listaUsuarios.stream()
                .filter(i -> i.getId() == id).findFirst();
    }

    public Optional<Items_Bibliograficos> buscarItemPorTitulo(String titulo){
        return coleccionItems.stream()
                .filter(i -> i.getTitulo().equals(titulo)).findFirst();
    }

    public void prestarItem(String idItem, int idUsuario) throws IllegalArgumentException {
        if (idItem == null || idItem.trim().isEmpty()){
            throw new IllegalArgumentException("El ID del item no puede ser un valor nulo o estar vacío para hacer el prestamo.");
        }
        if (idUsuario <= 0){
            throw new IllegalArgumentException("El ID del usuario no puede se 0 o un número negativo.");
        }

        Optional<Items_Bibliograficos> itemOptional = buscarItemPorID(idItem);
        Optional<Usuario> usuarioOptional = buscarUsuarioPorID(idUsuario);

        if (!itemOptional.isPresent()){
            throw new IllegalArgumentException("Error: El item con ( ID: " + idItem + " ) no se encuentra registrado en la biblioteca.");
        }
        if (!usuarioOptional.isPresent()){
            throw new IllegalArgumentException("Error: El usuario con ( ID: " + idUsuario + " ) no se encuentra registrado.");
        }
        Items_Bibliograficos itemAPrestar = itemOptional.get();
        Usuario usuarioPrestamista = usuarioOptional.get();

        if(itemAPrestar.isEstado()){
            throw new IllegalArgumentException("El item: '" + itemAPrestar.getTitulo() + "' con ( ID: " + idItem + ") no se encuentra disponible.");
        }

        itemAPrestar.setEstado(true);
        usuarioPrestamista.prestarItem(itemAPrestar);
        System.out.println("El item: '" + itemAPrestar.getTitulo() + "' con ( ID: " + idItem + ") ha sido prestado a: '" + usuarioPrestamista.getNombre() + "' con ( ID: " + idUsuario + ").");
    }

    public void devolverItem(String idItem, int idUsuario) throws IllegalArgumentException {
        if (idItem == null || idItem.trim().isEmpty()){
            throw new IllegalArgumentException("El ID del item no puede ser nulo o vacío.");
        }
        if (idUsuario <= 0){
            throw new IllegalArgumentException("El ID del usuario no puede ser 0 o un número negativo.");
        }

        Optional<Items_Bibliograficos> itemOptional =  buscarItemPorID(idItem);
        Optional<Usuario> usuarioOptional = buscarUsuarioPorID(idUsuario);

        if (!itemOptional.isPresent()){
            throw new IllegalArgumentException("El ID del item no esta registrado.");
        }
        if (!usuarioOptional.isPresent()){
            throw new IllegalArgumentException("EL ID del usuario no esta registrado.");
        }

        Items_Bibliograficos itemADevolver = itemOptional.get();
        Usuario usuarioPrestamista = usuarioOptional.get();

        if (!itemADevolver.isEstado()){
            throw new IllegalArgumentException("El item: '" + itemADevolver.getTitulo() + "' con ( ID: " + idItem + ") no esta en prestamo.");
        }

        boolean itemPrestadoPorUuario = usuarioPrestamista.ItemsPrestados.contains(itemADevolver);

        if (!itemPrestadoPorUuario){
            throw new IllegalArgumentException("Este usuario no ha prestado el item que desea devolver.");
        }

        itemADevolver.setEstado(false);
        usuarioPrestamista.devolverItem(itemADevolver);
        System.out.println("El item: '" + itemADevolver.getTitulo() + "' con ( ID: " + idItem + ") ha sido devuelto a la biblioteca. Gracias!");
    }


    @Override
    public void buscarPorTitulo(String titulo) throws IllegalArgumentException {
        if (titulo == null || titulo.trim().isEmpty()){
            throw new IllegalArgumentException("Por favor ingrese el titulo. Este no puede ser nulo, ni estar vacío.");
        }

        Optional<Items_Bibliograficos> itemOptional = buscarItemPorTitulo(titulo);

        if (!itemOptional.isPresent()){
            throw new IllegalArgumentException("El titulo buscado no se encuentra.");
        }

        Items_Bibliograficos item = itemOptional.get();

        if (item.isEstado()){
            throw new IllegalArgumentException("El titulo que busca esta prestado.");
        }

        System.out.println("El titulo: '" + item.getTitulo() + "' con ( ID: " + item.getId() + ") esta disponible.");
    }

    @Override
    public void buscarPorAutor(String autor) throws IllegalArgumentException {
        if (autor == null || autor.trim().isEmpty()){
            throw new IllegalArgumentException("Ingrese el nombre del autor. El campo no puede ser nulo o estar vacío.");
        }
        String autorBuscadoLower = autor.toLowerCase();
        List<Items_Bibliograficos> resultado = coleccionItems.stream()
                .filter(i -> i.getAutor() != null && i.getAutor().toLowerCase().contains(autorBuscadoLower))
                .collect(Collectors.toList());

        if (resultado.isEmpty()){
            throw new IllegalArgumentException("La biblioteca no tiene libros registrados del autor.'" + autor + "'");
        }else{
            System.out.println("Resultados de busqueda por el autor '" + autor +"'");
            for (Items_Bibliograficos i : resultado){
                System.out.println("-" + i.getTitulo() + " ( ID: " + i.getId() + ") ");
            }
        }
    }

    @Override
    public void mostrarItemsDisponibles() {
        List<Items_Bibliograficos> coleccion = coleccionItems.stream().filter(i -> !i.isEstado()).collect(Collectors.toList());
        if (coleccion.isEmpty()){
            throw new IllegalArgumentException("No se encontraron items dispoblibles.");
        }
        coleccion.forEach(i -> System.out.println(i.getTitulo() + " ( ID: " + i.getId() + ")."));
    }

    public void mostrarItemsPrestadosPorUsuario(int id) throws IllegalArgumentException {
        if (id <= 0){
            throw new IllegalArgumentException("EL ID no puede ser un valor nulo o vacío.");
        }

        Optional<Usuario> usuarioOptional = buscarUsuarioPorID(id);

        if (!usuarioOptional.isPresent()){
            throw new IllegalArgumentException("El usuario con el ID ingresado no esta registrado.");
        }

        Usuario usuario  = usuarioOptional.get();

        usuario.getItemsPrestados();
    }

    public void mostrarDetalle(Items_Bibliograficos item) throws IllegalArgumentException {
        if (item == null){
            throw new IllegalArgumentException("El item no puede ser nulo.");
        }
        System.out.println(item);
    }
}
