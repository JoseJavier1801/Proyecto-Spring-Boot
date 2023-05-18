package com.example.demo.Services;

import com.example.demo.model.Productos;
import java.util.List;
import java.util.Optional;

public interface interfaceProductos {

    public List<Productos> getAllProductos();
    void modifyProduct(int id, String nombre, int cantidad);

    Optional<Productos> findById(int id);
}
