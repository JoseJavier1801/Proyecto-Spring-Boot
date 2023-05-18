package com.example.demo.Services;

import com.example.demo.Repository.RepositoryProducto;
import com.example.demo.model.Productos;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesProductos implements interfaceProductos {

    private RepositoryProducto repoProducts;

    public ServicesProductos(RepositoryProducto repoProducts) {
        this.repoProducts = repoProducts;
    }

    @Override
    public List<Productos> getAllProductos() {
        return repoProducts.findAll();
    }

    @Override
    public void modifyProduct(int id, String nombre, int cantidad) {
        repoProducts.modifyProduct(id,nombre);
    }


    public void save(Productos producto) {
        repoProducts.save(producto);
    }

    public void delete(int id) {
        repoProducts.deleteProducts(id);
    }

    @Override
    public Optional<Productos> findById(int id){
        return repoProducts.findById(id);
    }

    public void saveAll(List<Productos> productos) {
        repoProducts.saveAll(productos);
    }

    public List<Productos> getProductosByID(List<Integer> ids) {
        return repoProducts.findAllById(ids);
    }


}

