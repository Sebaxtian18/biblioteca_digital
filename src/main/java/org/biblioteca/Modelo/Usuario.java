package org.biblioteca.Modelo;

import org.biblioteca.Repositorio.IPrestables;
import org.biblioteca.Items_Bibliograficos;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void prestarItem(Items_Bibliograficos item) {
        this.ItemsPrestados.add(item);
    }

    @Override
    public void devolverItem(Items_Bibliograficos item) {
        this.ItemsPrestados.remove(item);
    }
}
