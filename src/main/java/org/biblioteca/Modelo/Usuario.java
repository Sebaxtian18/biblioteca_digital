package org.biblioteca.Modelo;

import org.biblioteca.Repositorio.IPrestables;
import org.biblioteca.Items_Bibliograficos;

import java.util.ArrayList;
import java.util.List;

/**Representa la plantilla con la cual se registraran los usuarios.
 * Sobre escribe e implementa dos metodos de la interfas {@code IPrestable},
 * para el manejo de los items prestados o a prestar.*/
public class Usuario implements IPrestables {
    private String nombre;
    private int id;
    public List<Items_Bibliograficos> ItemsPrestados;

    public Usuario() {
        this.nombre = "";
        this.id = 0;
        this.ItemsPrestados = new ArrayList<>();
    }

    public Usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
        this.ItemsPrestados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void getItemsPrestados() throws IllegalArgumentException {
        if (ItemsPrestados.isEmpty()){
            throw new IllegalArgumentException("El usuario no tiene items prestados.");
        }
        ItemsPrestados.stream()
                .forEach(i -> System.out.println("El usuario con ID: '" + id + "' tiene el libro: '" + i.getTitulo() + "' ( ID: " + i.getId() + ")."));
    }

    public void setItemsPrestados(List<Items_Bibliograficos> itemsPrestados) {
        ItemsPrestados = itemsPrestados;
    }

    /**Sobre escritura e implementación del metodo {@code prestaItem}
     * @param item El {@code Item_Bibliografico} a prestar.*/
    @Override
    public void prestarItem(Items_Bibliograficos item) {
        this.ItemsPrestados.add(item);
    }

    /**Sobre escritura e implementación del metodo {@code devolverItem}
     * @param item El {@code Item_Bibliografico} a devolver.*/
    @Override
    public void devolverItem(Items_Bibliograficos item) {
        this.ItemsPrestados.remove(item);
    }

    /**Metodo para mostrar la información del usuario.
     * El metodo evalua el ingreso del objeto, de no ser así el sistema lanzara un error.
     * Si el objeto es proporcionado se imprimen su informacion en pantalla.
     *
     * @param usuario El usuario sobre el cual se quiere obtener la información.*/
    public void informacionUsuario(Usuario usuario) throws IllegalArgumentException {
        if (usuario == null){
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        System.out.println("-----Informacion usuario-----");
        System.out.println("- Nombre: '" + getNombre() + "' ");
        System.out.println("- ID: '" + getId() + "' ");
        System.out.println("El usuario tiene prestados los siguientes items: ");
        ItemsPrestados.stream().forEach(i -> System.out.println("- '" +i.getTitulo() + "' ( ID: "+ i.getId() + " )"));
    }
}
