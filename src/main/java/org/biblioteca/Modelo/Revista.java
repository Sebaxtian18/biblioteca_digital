package org.biblioteca.Modelo;

import org.biblioteca.Items_Bibliograficos;

/**Representa un item, hijo de {@code Items_Bibliograficos}*/
public class Revista extends Items_Bibliograficos {
    private int numEdicion;
    private String issn;

    public Revista() {
        this.numEdicion = 0;
        this.issn = "";
    }

    public Revista(String titulo, String autor, int anoPublic, boolean estado, String id, int numEdicion, String issn) {
        super(titulo, autor, anoPublic, estado, id);
        this.numEdicion = numEdicion;
        this.issn = issn;
    }

    public int getNumEdicion() {
        return numEdicion;
    }

    public void setNumEdicion(int numEdicion) {
        this.numEdicion = numEdicion;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    /**Sobe escritura e implementación del metodo mostrar detalle para el Revista*/
    @Override
    public void mostrarDetalle(Items_Bibliograficos item) throws IllegalArgumentException {
        if (item == null){
            throw new IllegalArgumentException("El item no puede ser nulo.");
        }
        System.out.println("------Detalles------");
        System.out.println("- Titulo: '" + getTitulo() + "'");
        System.out.println("- ID: '" + getId() + "'");
        System.out.println("- Autor: '" + getAutor() + "'");
        System.out.println("- ISSN: '" + getIssn() + "'");
        System.out.println("- Número de edición: '" + getNumEdicion() + "'");
        System.out.println("- Año Publicación: '" + getAnoPublic() + "'");
        System.out.println(isEstado() ? "- La revista se encuentra prestada" : "- La revista se encuentra disponible.");
    }
}
