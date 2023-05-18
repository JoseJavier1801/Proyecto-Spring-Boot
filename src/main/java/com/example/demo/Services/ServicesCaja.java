package com.example.demo.Services;
import com.example.demo.Repository.RepositoryCaja;
import com.example.demo.model.Caja;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesCaja {

    private RepositoryCaja repoBox;

    public ServicesCaja(RepositoryCaja repoBox) {
        this.repoBox= repoBox;
    }

    public List<Caja> getAllBoxes() {
        return repoBox.findAll();
    }

    public void save(Caja caja) {
        repoBox.save(caja);
    }

    public void delete(int id) {
        repoBox.deleteBox(id);
    }

    public void modifyBox(int id, String nombre) {
        repoBox.modifyBox(id,nombre);
    }
    public Optional<Caja> findById(int id){
        return repoBox.findById(id);
    }


}
