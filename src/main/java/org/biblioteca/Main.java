package org.biblioteca;

import org.biblioteca.Modelo.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Biblioteca biblioteca = new Biblioteca();

        Libro libro1 = new Libro("Cien años de soledad", "Grabriel Garcia Marquez", 1982, false, "LB001", "111-222-333", "230");
        Libro libro2 = new Libro("La Cadena", "Milan Kundera", 2022, false, "LB002", "111-222-334", "302");
        Revista revista1 = new Revista("Rollings Tone", "Rollings Tone", 2025, false, "RV001", 1, "1111-1111");
        Revista revista2 = new Revista("Vea", "Vea", 2025, false, "RV002", 1, "1111-1112");
        AudioLibro audioLibro1 = new AudioLibro("Tom Soyer", "Mark Twain", 1990, false, "AL001", "2Hrs", "Marcos Polo");
        Libro libro3 = new Libro("Tom Soyer", "Mark Twain", 1990, false, "AL001", "2Hrs", "Marcos Polo");
        try{
            biblioteca.registrarItem(libro1);
            biblioteca.registrarItem(libro2);
            biblioteca.registrarItem(revista1);
            biblioteca.registrarItem(revista2);
            biblioteca.registrarItem(audioLibro1);
            biblioteca.registrarItem(libro3);
        }catch (IllegalArgumentException e){
            System.out.println("Error al crear el item" + e.getMessage());
        }

        Usuario usuario1 = new Usuario("Carlos Amador Venganza", 001);

        try{
            biblioteca.registrarUsuario(usuario1);
        }catch (IllegalArgumentException e){
            System.out.println("Error al crear el usuario" + e.getMessage());
        }

        biblioteca.mostrarItemsDisponibles();
        biblioteca.buscarUsuarioPorID(001);
        biblioteca.buscarItemPorID("LB001");
        biblioteca.buscarPorTitulo("Cien años de soledad");
        biblioteca.buscarPorAutor("Grabriel Garcia Marquez");
        biblioteca.mostrarDetalle(libro1);
        biblioteca.prestarItem("LB002", 1);
        biblioteca.prestarItem("RV001", 1);
        biblioteca.mostrarItemsPrestadosPorUsuario(1);
        biblioteca.mostrarItemsDisponibles();
        biblioteca.devolverItem("LB002", 1);
    }
}