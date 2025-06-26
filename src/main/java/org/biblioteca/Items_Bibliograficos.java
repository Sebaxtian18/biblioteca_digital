package org.biblioteca;

/**
 * Representa la plantilla de la cual se crearan los diferentes items.
 * Abstrae cinco atributos que compartiran los diferentes items e implementa un metodo abstracto para
 * que cada item muestre sus detalles.
 * Se sobre escriben los metodos {@code equals} y {@code hashCode} para lograr comparar los items
 * por su id y no solo su espacio en memoria.*/
public abstract class Items_Bibliograficos {
    protected String titulo;
    protected String autor;
    protected int anoPublic;
    protected boolean estado;
    protected String id;

    public Items_Bibliograficos() {
    }

    public Items_Bibliograficos(String titulo, String autor, int anoPublic, boolean estado, String id) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublic = anoPublic;
        this.estado = false;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublic() {
        return anoPublic;
    }

    public void setAnoPublic(int anoPublic) {
        this.anoPublic = anoPublic;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**Metodo para mostrar los detalles de cada {@code item}*/
    public abstract void mostrarDetalle(Items_Bibliograficos item);

    /**Sobre escritura del metodo {@code equals} para comparar el objeto por su {@id} y no solo por su espacio en memoria.*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si es el mismo objeto en memoria
        if (o == null || getClass() != o.getClass()) return false; // Si el otro objeto es nulo o de diferente clase

        Items_Bibliograficos that = (Items_Bibliograficos) o; // Casteo
        return id.equals(that.id); // Compara por el ID único (asumiendo String)
        // Si idUnico es int: return idUnico == that.idUnico;
    }

    @Override
    public int hashCode() {
        return id.hashCode(); // Si idUnico es String
        // Si idUnico es int: return Objects.hash(idUnico); o simplemente return idUnico;
    }
}
