package org.biblioteca.Repositorio;

import org.biblioteca.Items_Bibliograficos;

public interface IPrestables {
    public void prestarItem(Items_Bibliograficos item);
    public void devolverItem(Items_Bibliograficos item);
}
