package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data//lombok
@Entity//Es una entidad
@Table(name="productos")//Como quiereas que se llame la tabla

public class Productos {
    @Id//Establece que es un ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Hace que se genere de manera automática
    public int id;
    @Column(name = "nombre", nullable=false)
    public String nombre;

    @ManyToMany
    @JoinTable(name = "productos_cajas",
            joinColumns = @JoinColumn(name = "productos_id"),
            inverseJoinColumns = @JoinColumn(name = "cajas_id"))
    private List<Caja> cajas = new ArrayList<>();

}