package com.example.demo.Repository;

import com.example.demo.model.Caja;
import com.example.demo.model.Productos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositoryCaja extends JpaRepository<Caja, Long> {
    @Query(value = "SELECT * FROM caja", nativeQuery = true)
    public List<Caja> findAll();

    @Query(value = "SELECT * FROM caja WHERE ID = ?1", nativeQuery = true)
    public Optional<Caja> findById(int id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM caja where id=?1", nativeQuery = true)
    void deleteBox(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE caja SET nombre=:Name, WHERE id=:id", nativeQuery = true)
    void modifyBox(@Param("id") int id, @Param("Name") String nombre);
}
