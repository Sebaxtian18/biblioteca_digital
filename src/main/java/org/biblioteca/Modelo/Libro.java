package org.biblioteca.Modelo;

import org.biblioteca.Items_Bibliograficos;

public class Libro extends Items_Bibliograficos {
    private String isbn;
    private String numPaginas;

    public Libro() {
        this.isbn = "";
        this.numPaginas = "";
    }

    public Libro(String titulo, String autor, int anoPublic, boolean estado, String id, String isbn, String numPaginas) {
        super(titulo, autor, anoPublic, estado, id);
        this.isbn = isbn;
        this.numPaginas = numPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(String numPaginas) {
        this.numPaginas = numPaginas;
    }

    @Override
    public void mostrarDetalle(Items_Bibliograficos item) throws IllegalArgumentException {
        if (item == null){
            throw new IllegalArgumentException("El item no puede ser nulo.");
        }
        System.out.println(item);
    }


}
