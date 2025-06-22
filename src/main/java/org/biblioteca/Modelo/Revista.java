package org.biblioteca.Modelo;

import org.biblioteca.Items_Bibliograficos;

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

    @Override
    public void mostrarDetalle(Items_Bibliograficos item) throws IllegalArgumentException {
        if (item == null){
            throw new IllegalArgumentException("El item no puede ser nulo.");
        }
        System.out.println(item);
    }
}
