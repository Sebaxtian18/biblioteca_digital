package org.biblioteca.Repositorio;

import org.biblioteca.Items_Bibliograficos;

import java.util.List;

public interface IBuscar {
    public void buscarPorTitulo(String titulo);
    public void buscarPorAutor(String autor);
    public void mostrarItemsDisponibles();
}
