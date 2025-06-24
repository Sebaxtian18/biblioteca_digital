package org.biblioteca.Modelo;

import org.biblioteca.Repositorio.IBuscar;
import org.biblioteca.Items_Bibliograficos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Representa el sistema central de gestion de la biblioteca virtual.
 * Administra las colecciones de los items_bibliograficos y los usuarios,
 * permitiendo ejecutar un CRUD básico con el cual gestionar los prestamos, devoluciones, registros y,
 * eliminar elementos y usuarios de la biblioteca.
 *
 * @author zseba
 * @version 1.0
 * @since 2025-06-23*/

public class Biblioteca implements IBuscar {
    private List<Items_Bibliograficos> coleccionItems;
    private List<Usuario> listaUsuarios;

    public Biblioteca() {
        this.coleccionItems = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
    }

    /**
     * Metodo para registrar un nuevo usurio.
     * El metodo resibe un objeto de la clase {@Usuario}.
     * Este metodo evalua que el usuario no sea null, que el ID no este vacío y que el ID no este registrado.
     *
     * @param usuario El {@link org.biblioteca.Modelo.Usuario} que será agregado.
     * Debe de ser una instancia valida de {@code Usuario}.
     * @throws IllegalArgumentException Es la excepción que arrojara si el {@code usuario}
     * es null, su ID es null o vacío ó el ID ya esta registrado en la biblioteca.*/
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

    /**
     * Metodo para registrar un nuevo item.
     * El metodo resibe un objeto de la clase {@code items_bibliograficos}.
     * Este metodo valida que item no sea null, que el ID no este vacío y que el ID no este registrado.
     *
     * @param item El {@link org.biblioteca.Items_Bibliograficos} debe de ser una instancia valida de
     * {@link Libro}, {@link Revista}, {@link AudioLibro}.
     * @throws IllegalArgumentException Excepción que arrojara el sistema si el item es null,
     * el ID es null o esta vacío ó el ID ya esta registrado*/
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

    /**
     * Metodo para buscar un item por ID.
     * El metodo resibe el parametro {@code id} del item.
     * Se valida que el usuario haya inf¿gresado el parametro, de lo contrario lanza un error.
     * Este metodo gestiona una busqueda por ID de un item.
     *
     * @param id El ID del {@link org.biblioteca.Items_Bibliograficos}.
     * Este debe de ser de una instacia valida de {@code Libro}, {@code Revista}, {@code AudioLibro}.
     * @return Optional para manejo de valores nulos en caso de no encontrar el item.*/
    public Optional<Items_Bibliograficos> buscarItemPorID(String id){
        return coleccionItems.stream()
                .filter(i -> i.getId().equals(id)).findFirst();
    }

    /**
     * Metodo para buscar un usuario por ID.
     * El metodo resibe el parametro {@code id} del usuario.
     * Se valida que el usuario haya ingresado el parametro, de lo contrario se lanza un error.
     * Este metodo gestiona una busqueda por ID de un usuario.
     *
     * @param id El ID del {@link org.biblioteca.Modelo.Usuario}.
     * @return Optional para manejo de valores nulos en caso de no encontrar el item.*/
    public Optional<Usuario> buscarUsuarioPorID(int id){
        return listaUsuarios.stream()
                .filter(i -> i.getId() == id).findFirst();
    }

    /**
     * Metodo para buscar item por ID.
     * El metodo resibe el parametro {@code titulo} del item que desea buscar.
     * Se valida que el usuario haya ingresado el parametro, de lo contrario, lanza un error.
     * Este metodo gestiona una busqueda por ID de un item.
     *
     * @param titulo El titulo del {@link org.biblioteca.Items_Bibliograficos}.
     * Este metodo no distingue de clases.
     * La busqueda se ejecuta en {@code coleccionItems}.
     * @return Optional para manejo de valores nulos en caso de no encontrar el item.*/
    public Optional<Items_Bibliograficos> buscarItemPorTitulo(String titulo){
        return coleccionItems.stream()
                .filter(i -> i.getTitulo().equals(titulo)).findFirst();
    }

    /**
     * Metodo para prestar un item.
     * El metodo resibe dos parametros: el ID del item y el ID del usuario.
     * Este metodo valida que el usuario haya ingresado ambos parametros.
     * El metodo implementa dos funciones externas para generar la busqueda del item y el usuario por su ID
     * El metodo valida que ambos IDs esten registrados.
     * Si ambos estan registrados procede a hacer el prestamo,
     * referenciando el prestamo en la lista de items prestados del usuario.
     * Si alguno de los dos no esta registrado el sistema lanza una exception.
     *
     * @param idItem El ID del {@code Libro}, {@code Revista}, o {@code AudioLibro}.
     * @param idUsuario El ID del {@code Usuario}.
     * @throws IllegalArgumentException Excepción para gestionar errores.
     * @see Biblioteca#buscarItemPorID(String) Metodo para buscar el item por ID.
     * @see Biblioteca#buscarUsuarioPorID(int) Metodo para buscar el usuario por ID. */
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

    /**
     * Metodo para devolver in item.
     * El metodo resibe dos parametros: el ID del item y el ID del usuario.
     * Se valida que el usuario haya ingresado ambos parametros, de lo contrario lanza un error.
     * El metodo utiliza dos funciones externas para buscar el usuario y el item.
     * Se valida que ambos esten registrados.
     * Si es así se genera la devolución, de lo contrario se genera un error.
     *
     * @param idItem El ID del {@code Libro}, {@code Revista}, {@AudioLibro}.
     * @param idUsuario El ID del usuario.
     * @throws IllegalArgumentException excepción para gestionar errores.
     * @see Biblioteca#buscarUsuarioPorID(int) Metodo para buscar el usuario.
     * @see Biblioteca#buscarItemPorID(String) Metodo para buscar el item*/
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

    /**
     * Metodo para buscar por titulo.
     * El metodo resibe un parametro {@code titulo}.
     * Se evalua que el usuario haya ingresado el parametro.
     * El metodo utiliza una función externa para buscar el titulo.
     * Se evalua de que el {@code titulo} este registrado.
     * Si esta registrado se mostrara el titulo y su ID de lo contrario se lanzara un error.
     *
     * @param titulo El titulo del item.
     * @throws IllegalArgumentException exception para manejar errores.
     * @see Biblioteca#buscarPorTitulo(String) Metodo para buscar por titulo. */
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

    /**Metodo para buscar por autor.
     * El metodo resibe un parametro {@code autor}.
     * Se evalua que el usuario haya ingresado los parametros, de lo contrario se lanza un error.
     * Si el autor esta registrado se genera una lista con los items disponibles.
     *
     * @param autor El nombre del autor.
     * @throws IllegalArgumentException exception para manejar los errores.*/
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

    /**Metodo para mostrar los items disponibles.
     * El metodo busca el la lista {@code coleccionItems}.
     * Si se encuentran conincidencias se muestra en pantalla una lista de los items disponibles.
     *
     * @throws IllegalArgumentException exception para manejar los errores.*/
    @Override
    public void mostrarItemsDisponibles() {
        List<Items_Bibliograficos> coleccion = coleccionItems.stream().filter(i -> !i.isEstado()).collect(Collectors.toList());
        if (coleccion.isEmpty()){
            throw new IllegalArgumentException("No se encontraron items dispoblibles.");
        }
        coleccion.forEach(i -> System.out.println(i.getTitulo() + " ( ID: " + i.getId() + ")."));
    }

    /**
     * Metodo para mostrar los intems prestados a un usuario.
     * EL metodo resibe el parametro {@code id} del usuario.
     * Se evalua que el usuario haya ingresado el parametro.
     * El metodo ejecuta una función externa para buscar el usuario.
     * Si este esta registrado se ejecuta el metodo de la clase usuario.getItemsprestados.
     *
     * @param id El ID del usuario.
     * @throws IllegalArgumentException exception para manejar los errores.
     * @see Biblioteca#buscarUsuarioPorID(int) metodo para buscar el usuario. */
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

    /**
     * Metodo para mostrar los detalles de un item.
     * El metodo resibe un objeto de la clase {@code items_bibliograficos}.
     * Se evalua que el usuario haya ingresado el objeto.
     * Se imprime en pantalla los detalles del item seleccionado.
     *
     * @throws IllegalArgumentException exception para manejar los errores.*/
    public void mostrarDetalle(Items_Bibliograficos item) throws IllegalArgumentException {
        if (item == null){
            throw new IllegalArgumentException("El item no puede ser nulo.");
        }
        System.out.println(item);
    }

    /**
     * Metodo para eliminar un item por ID.
     * El metodo resibe un parametro {@code idItem} el ID del item.
     * Se evalua que el usuario haya ingresado el parametro.
     * Si no es así el sistema lanzara un error.
     * El metodo utiliza una función externa para asegurarse de que el item existe.
     * Si es así el sistema lo elimianar de la coleccion {@code coleccionItems}.
     * De lo contrario lanzara un error.
     *
     * @param idItem El id del item.
     * @throws IllegalArgumentException exception para manejar los errores.
     * @see Biblioteca#buscarItemPorID(String) funcion para buscar el item por id.*/
    public void eliminarElementoPorID(String idItem) throws IllegalArgumentException {
        if (idItem == null || idItem.trim().isEmpty()){
            throw new IllegalArgumentException("El ID del item no puede ser un elemento vacío.");
        }

        Optional<Items_Bibliograficos> itemOptional = buscarItemPorID(idItem);


        if (!itemOptional.isPresent()){
            throw new IllegalArgumentException("EL item no esta registrado.");
        }

        Items_Bibliograficos item = itemOptional.get();

        if (item.isEstado()){
            throw new IllegalArgumentException("El libro: '" + item.getTitulo() + "' se encuentra en estado de pretamo. No se puede eliminar hasta que este de nuevo en la biblioteca.");
        }

        coleccionItems.remove(item);
        System.out.println("El item '" + item.getTitulo() + "' ( ID: " + item.getId() + " ) ha sido eliminado.");
    }

    /**
     * Metodo para eliminar un usuario por id.
     * El metodo resibe el parametro {@code idUsuario}.
     * Se verifica que el usuario haya ingresado el parametro.
     * Si no es así el sistema lanzara un error.
     * El sistema utiliza una función externa para buscar el usuario.
     * Se verifica que el usuario este registrado, si lo esta se procede a evaluar si este tiene items en su poder.
     * Si es así no se permite eliminar al usuario.
     * De lo contrario se procede a elimina el usuario
     *
     * @param idUsuario El id del usuario.
     * @throws IllegalArgumentException exception para manejar los errores.
     * @see Biblioteca#buscarUsuarioPorID(int) metodo para buscar el usuario.*/
    public void eliminarUsuarioPorID(int idUsuario){
        if (idUsuario <= 0){
            throw new IllegalArgumentException("EL ID del usuario no puede ser 0 o un número negativo.");
        }

        Optional<Usuario> usuarioOptional = buscarUsuarioPorID(idUsuario);

        if (!usuarioOptional.isPresent()){
            throw new IllegalArgumentException("El usuario no esta registrado.");
        }

        Usuario usuario = usuarioOptional.get();

        if (!usuario.ItemsPrestados.isEmpty()){
            System.out.println("El usuario '" + usuario.getNombre() + "' no se puede eliminar porque aun tiene los siguientes items prestados:");
            usuario.ItemsPrestados.stream().forEach(i -> System.out.println("- '" + i.getTitulo() + "'  ( " + i.getId() + ")"));
            throw new IllegalArgumentException("Por favor devolverlos antes de eliminar este usuario.");
        }

        listaUsuarios.remove(usuario);
        System.out.println("El usuario: '" + usuario.getNombre() + "' ( ID: " + usuario.getId() + " ) ha sido eliminado.");
    }
}
