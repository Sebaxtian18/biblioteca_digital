package org.biblioteca;

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

    public abstract void mostrarDetalle(Items_Bibliograficos item);

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
