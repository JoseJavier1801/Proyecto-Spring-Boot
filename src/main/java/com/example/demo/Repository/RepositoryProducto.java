package com.example.demo.Repository;

import com.example.demo.model.Productos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositoryProducto extends JpaRepository<Productos, Long> {

    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    public List<Productos> findAll();

    @Query(value = "SELECT * FROM productos WHERE ID = ?1", nativeQuery = true)
    public Optional<Productos> findById(int id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM productos where id=?1", nativeQuery = true)
    void deleteProducts(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre=:Name WHERE id=:id", nativeQuery = true)
    void modifyProduct(@Param("id") int id, @Param("Name") String nombre);

    @Query(value = "SELECT * FROM productos WHERE id IN :ids", nativeQuery = true)
    List<Productos> findAllById(@Param("ids") List<Integer> ids);
}


