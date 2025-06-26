package org.biblioteca;

import org.biblioteca.Modelo.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        /*
        * Se instancia un objeto biblioteca que es el sistema central encargado de gestionar
        * las operaciónes del CRUD*/
        Biblioteca biblioteca = new Biblioteca();

        // Se instancian tres libros
        Libro libro1 = new Libro("Cien años de soledad", "Grabriel Garcia Marquez", 1982, false, "LB001", "111-111-111", "230");
        Libro libro2 = new Libro("La Cadena", "Milan Kundera", 2022, false, "LB002", "111-111-222", "302");
        Libro libro3 = new Libro("1984", "George Orwell", 1980, false, "LB003", "111-111-333", "193");

        // Se instancian tres revistas
        Revista revista1 = new Revista("Rollings Tone", "Rollings Tone", 2025, false, "RV001", 1, "1111-1111");
        Revista revista2 = new Revista("Vea", "Vea", 2025, false, "RV002", 1, "1111-2222");
        Revista revista3 = new Revista("Top Gun", "Francis Grew", 2023, false, "RV003", 25, "1111-3333");

        // Se instancian tres audio libros
        AudioLibro audioLibro1 = new AudioLibro("Tom Soyer", "Mark Twain", 1990, false, "AL001", "2 hrs", "Marcos Polo");
        AudioLibro audioLibro2 = new AudioLibro("Cuentos de Memo Angel", "Memo Angel", 2023, false, "AL002", "3 hrs y 35 min", "Antonio Alvarez Posada");
        AudioLibro audioLibro3 = new AudioLibro("El Lobo Estepario", "Hermann Hesse", 1990, false, "AL003", "2 hrs 20 min", "Marcos Polo");

        // Se instancia un libro, una revista y un audio libro con el id ya registrado.
        Libro libro4 = new Libro("Las Aventuras de Arturo", "Auturo Pendragon", 1980, false, "LB003", "111-111-444", "193");
        Revista revista4 = new Revista("SOHO", "Martina Hernandes", 2025, false, "RV002", 1, "1111-4444");
        AudioLibro audioLibro4 = new AudioLibro("Maroco Polo", "Desconocido", 1990, false, "AL003", "2 hrs 20 min", "Marcos Polo");

        // Se instancian cuatro usuarios
        Usuario usuario1 = new Usuario("David Martinez Sossa", 001);
        Usuario usuario2 = new Usuario("Ana Karina Torres", 002);
        Usuario usuario3 = new Usuario("Juliana Martinez Ateortua", 003);
        Usuario usuario4 = new Usuario("Joe Smith Borges", 004);

        // Se instancian dos usuarios con id ya registrado.
        Usuario usuario5 = new Usuario("Marcos Prieto Ortiz", 003);
        Usuario usuario6 = new Usuario("Marta Helizondo Martinez", 001);
        // Se agregan tres libros a la bibliodeca.

        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Libros Registrados");
        try {
            biblioteca.registrarItem(libro1);
            biblioteca.registrarItem(libro2);
            biblioteca.registrarItem(libro3);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha prodicido un error al agregar el item: " + e.getMessage());
        }

        //Se agregan tres revistas a la biblioteca.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Revistas Registradas");
        try {
            biblioteca.registrarItem(revista1);
            biblioteca.registrarItem(revista2);
            biblioteca.registrarItem(revista3);
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error al agregar el item: " + e.getMessage());;
        }

        // Se agregan tres audio libros a la biblioteca.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Audio Libros Registrados");
        try{
            biblioteca.registrarItem(audioLibro1);
            biblioteca.registrarItem(audioLibro2);
            biblioteca.registrarItem(audioLibro3);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al agregar el item: " + e.getMessage());
        }

        // Se intenta registrar un libro con el mismo id en la biblioteca para evaluar si el sistema funciona correctamente.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Intento de registro para un libro con un ID ya registrado.");
        try{
            biblioteca.registrarItem(libro4);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al agregar el item: " + e.getMessage());
        }

        // Se intenta registrar una revista con el mismo id en la biblioteca para evaluar si el sistema funciona correctamente.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Intento de registro para una revista con un ID ya registrado.");
        try{
            biblioteca.registrarItem(revista4);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al agregar el item: " + e.getMessage());
        }

        // Se intenta registrar un audio libro con el mismo id en la biblioteca para evaluar si el sistema funciona correctamente.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Intento de registro para un audio libro con un id ya registrado.");
        try{
            biblioteca.registrarItem(audioLibro4);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al agregar el item: " + e.getMessage());
        }

        // Se registra cuatro usuarios nuevos en la biblioteca.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Usuarios registrados");
        try{
            biblioteca.registrarUsuario(usuario1);
            biblioteca.registrarUsuario(usuario2);
            biblioteca.registrarUsuario(usuario3);
            biblioteca.registrarUsuario(usuario4);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un rror al registrar al usuario: " + e.getMessage());
        }

        // Se agregan dos usuarios con id ya registrado para evaluar que el sistema este funcionando correctamente.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Intento de registro para dos usuarios con IDs ya registrados.");
        try{
            biblioteca.registrarUsuario(usuario5);
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error al registrar el usuario: " + e.getMessage());
        }

        try{
            biblioteca.registrarUsuario(usuario6);
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error al registrar el usuario: " + e.getMessage());
        }


        // Mostrar items disponibles
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Mostrar items disponibles.");
        try {
            biblioteca.mostrarItemsDisponibles();
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al mostrar los items: " + e.getMessage());
        }

        // Mostrar usuarios registrados
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Mostrar usuarios registrados.");
        try {
            biblioteca.mostrarUsuariosRegistrados();
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al mostrar los items: " + e.getMessage());
        }
        // Mostrar libros disponibles
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Mostrar Libros disponibles.");
        try {
            biblioteca.mostrarLibrosDisponibles();
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al mostrar los libros: " + e.getMessage());
        }
        // Mostrar revistas disponibles
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Mostrar revistas disponibles.");
        try {
            biblioteca.mostrarRevistasDisponibles();
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al mostrar los revistas: " + e.getMessage());
        }
        // Mostrar audio libros disponibles
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Mostrar audio libros disponibles.");
        try {
            biblioteca.mostrarAudioLibroDisponibles();
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al mostrar los audio libros: " + e.getMessage());
        }
        // Prestar el libro1 y libro3 al usuario1
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Prestar Libros 1 y 3 disponibles al usuario 1.");
        try {
            biblioteca.prestarItem("LB001", 001);
            biblioteca.prestarItem("LB003", 001);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al intentar prestar los items: " + e.getMessage());
        }
        // Prestar el audio libro1 y la revista3 al usuario2
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Prestar audio libro 1 y la revista 3 al usuario 2.");
        try {
            biblioteca.prestarItem("AL001", 002);
            biblioteca.prestarItem("RV003", 002);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al intentar prestar los items: " + e.getMessage());
        }
        // Prestar la revista 1 al usuario 3
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Prestar la revista 1 al usuario 3.");
        try {
            biblioteca.prestarItem("RV001", 003);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al intentar prestar los items: " + e.getMessage());
        }
        // Prestar el libro 4 la revista 2 y el audio libro 3 al usuario 4
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Prestar el libro 2, la revista 2 y el audio libro 3 al usuario 4.");
        try {
            biblioteca.prestarItem("LB002", 004);
            biblioteca.prestarItem("RV002", 004);
            biblioteca.prestarItem("AL003", 004);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al intentar prestar los items: " + e.getMessage());
        }
        // Se intenta prestar un item ya prestado
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Intento de prestar un item ya prestado para verificar el correcto funcionamiento del sistema.");
        try{
            biblioteca.prestarItem("RV002", 002);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al intentar prestar el item: " + e.getMessage());
        }

        // Intento de eliminar un item que esta prestado.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Intento de eliminar un item que esta prestado.");
        try{
            biblioteca.eliminarElementoPorID("LB001");
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al intentar eliminar el item: " + e.getMessage());
        }

        // Mostrar los elementos prestados por los usuarios..
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Mostrar los items prestados por los usuarios.");
        try{
            biblioteca.mostrarItemsPrestadosPorUsuario(001);
            biblioteca.mostrarItemsPrestadosPorUsuario(002);
            biblioteca.mostrarItemsPrestadosPorUsuario(003);
            biblioteca.mostrarItemsPrestadosPorUsuario(004);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al mostrar los items: " + e.getMessage());
        }

        // Devolver los elementos prestados por los usuarios.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Mostrar los items prestados por los usuarios.");
        try {
            biblioteca.devolverItem("RV002", 004);
            biblioteca.devolverItem("LB001", 001);
            biblioteca.devolverItem("RV003", 002);
        }catch (IllegalArgumentException e){
            System.out.println("Se ha producido un error al intentar devolver el item: " + e.getMessage());
        }

        // Busqueda por autor.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Busqueda por autor.");
        try{
            biblioteca.buscarPorAutor("Grabriel Garcia Marquez");
            biblioteca.buscarPorAutor("Hermann Hesse");
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error en la busqueda: " + e.getMessage());
        }


        // Busqueda por titulo.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Busqueda por titulo.");
        try{
            biblioteca.buscarPorTitulo("1994");
            biblioteca.buscarPorTitulo("El Lobo Estepario");
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error en la busqueda: " + e.getMessage());
        }

        // Busqueda de item por ID.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Busqueda de item por ID.");
        try{
            biblioteca.bItemPorID("LB003");
            biblioteca.bItemPorID("RV002");
            biblioteca.bItemPorID("AL003");
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error en la busqueda: " + e.getMessage());
        }

        // Devolver items prestados.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Devolver items prestados.");
        try{
            biblioteca.devolverItem("LB003", 001);
            biblioteca.devolverItem("RV001", 003);
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error al intentar devolver el libro: " + e.getMessage());
        }

        // Eliminar un item por ID.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Eliminar un item por ID que esta en prestamo para serciorarse del buen funcionamiento del sistema.");
        try{
            biblioteca.eliminarElementoPorID("AL003");
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error al intentar eliminar el elemento: " + e.getMessage());
        }

        // Eliminar un item por ID.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Eliminar un item por ID.");
        try{
            biblioteca.eliminarElementoPorID("AL002");
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error al intentar eliminar el elemento: " + e.getMessage());
        }

        // Eliminar un usario por ID que tiene items prestados para serciorarse del buen funcionamiento del sistema.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Eliminar un usario por ID que tiene items prestados para serciorarse del buen funcionamiento del sistema.");
        try{
            biblioteca.eliminarUsuarioPorID(002);
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error al intentar eliminar el usuario: " + e.getMessage());
        }

        // Eliminar un usario por ID.
        System.out.println("---------------------------------||---------------------------------");
        System.out.println("Eliminar un usario por ID.");
        try{
            biblioteca.eliminarUsuarioPorID(001);
        } catch (IllegalArgumentException e) {
            System.out.println("Se ha producido un error al intentar eliminar el usuario: " + e.getMessage());
        }
    }
}