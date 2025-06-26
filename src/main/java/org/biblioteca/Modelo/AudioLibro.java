package org.biblioteca.Modelo;

import org.biblioteca.Items_Bibliograficos;

/**Representa un item, hijo de {@code Items_Bibliograficos}*/
public class AudioLibro extends Items_Bibliograficos {
    private String duracion;
    private String narrador;

    public AudioLibro() {
        this.duracion = "";
        this.narrador = "";
    }

    public AudioLibro(String titulo, String autor, int anoPublic, boolean estado, String id, String duracion, String narrador) {
        super(titulo, autor, anoPublic, estado, id);
        this.duracion = duracion;
        this.narrador = narrador;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getNarrador() {
        return narrador;
    }

    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    /**Sobe escritura e implementación del metodo mostrar detalle para el AudioLibro*/
    @Override
    public void mostrarDetalle(Items_Bibliograficos item) throws IllegalArgumentException {
        if (item == null){
            throw new IllegalArgumentException("El item no puede ser nulo.");
        }
        System.out.println("------Detalles------");
        System.out.println("- Titulo: '" + getTitulo() + "'");
        System.out.println("- ID: '" + getId() + "'");
        System.out.println("- Autor: '" + getAutor() + "'");
        System.out.println("- Duración: '" + getDuracion() + "'");
        System.out.println("- Narrado por: '" + getNarrador() + "'");
        System.out.println("- Año Publicación: '" + getAnoPublic() + "'");
        System.out.println(isEstado() ? "- El Audio libro se encuentra prestado" : "- El Audio libro se encuentra disponible.");
    }
}
