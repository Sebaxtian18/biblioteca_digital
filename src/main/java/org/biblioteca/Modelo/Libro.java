package org.biblioteca.Modelo;

import org.biblioteca.Items_Bibliograficos;

import java.util.List;

/**Representa un item, hijo de {@code Items_Bibliograficos}*/
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

    /**Sobe escritura e implementación del metodo mostrar detalle para el Libro*/
    @Override
    public void mostrarDetalle(Items_Bibliograficos item) throws IllegalArgumentException {
        if (item == null){
            throw new IllegalArgumentException("El item no puede ser nulo.");
        }
        System.out.println("------Detalles------");
        System.out.println("- Titulo: '" + getTitulo() + "'");
        System.out.println("- ID: '" + getId() + "'");
        System.out.println("- Autor: '" + getAutor() + "'");
        System.out.println("- ISBN: '" + getIsbn() + "'");
        System.out.println("- Número de paginas: '" + getNumPaginas() + "'");
        System.out.println("- Año Publicación: '" + getAnoPublic() + "'");
        System.out.println(isEstado() ? "- El libro se encuentra prestado" : "- El libro se encuentra disponible.");
    }
}
