package com.sergio_arboleda.ciclo4.Repositories;
import com.sergio_arboleda.ciclo4.Entities.Gadget;
import com.sergio_arboleda.ciclo4.Entities.Orden;
import com.sergio_arboleda.ciclo4.Entities.Usuario;
import com.sergio_arboleda.ciclo4.Repositories.Crud.GadgetCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GadgetRepository {

    @Autowired//injectamos el crud para usar los métodos
    private GadgetCrudRepository repository;

    //Listar productos
    public List<Gadget> getAll() {
        return (List<Gadget>) repository.findAll();//busca todos los registros de la tabla Laptop
    }

    //obtener productos por id
    public Optional<Gadget> getById(int id) {
        return repository.findById(id);
    }

    //Guardar producto o actualizar
    public Gadget save(Gadget gadget) {
        return repository.save(gadget);
    }

    //borrar producto
    public void delete(Gadget gadget) {
        repository.delete(gadget);
    }

    public Optional<Gadget> lastUserId() {
        return repository.findTopByOrderByIdDesc();
    }
    /* **********************Reto 5******************************************************************/
    public List<Gadget> getGadgetByDescription(String description){
        return  repository.getGadgetByDescription(description);
    }

    public List<Gadget> getGadgetByPrice(double price){
        return repository.getGadgetByPrice(price);
    }
}