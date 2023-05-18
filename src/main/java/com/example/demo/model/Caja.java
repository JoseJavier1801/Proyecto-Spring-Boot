package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "caja")
public class Caja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "nombre", nullable = false)
    public String nombre;

    @ManyToMany(mappedBy = "cajas")
    private List<Productos> productos = new ArrayList<>();

    public List<Productos> getProductos() {
        return productos;
    }

    public void addProducto(Productos producto) {
        productos.add(producto);
        producto.getCajas().add(this);
    }
}
