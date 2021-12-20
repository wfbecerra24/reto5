package com.sergio_arboleda.ciclo4.Services;

import com.sergio_arboleda.ciclo4.Entities.Gadget;
import com.sergio_arboleda.ciclo4.Entities.Orden;
import com.sergio_arboleda.ciclo4.Entities.Usuario;
import com.sergio_arboleda.ciclo4.Repositories.GadgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GadgetService {

    @Autowired
    private GadgetRepository repository;

    //Listar productos
    public List<Gadget> getAll() {
        return repository.getAll();
    }

    //Obtener producto por id
    public Optional<Gadget> getById(int Id) {
        return repository.getById(Id);
    }

    //crear producto
    public Gadget save(Gadget gadget) {

        //obtiene el maximo id existente en la coleccion
        Optional<Gadget> gadgetIdMaximo = repository.lastUserId();

        //si el id del Gadget que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (gadget.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (!gadgetIdMaximo.isPresent())
                gadget.setId(1);
                //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo del Gadget
            else
                gadget.setId(gadgetIdMaximo.get().getId() + 1);
        }
            return repository.save(gadget);
    }

    //actualizar producto
    public Gadget update(Gadget gadget) {
        //si el Id no es null, es decir existe
        if (gadget.getId() != null) {
            //obtener el producto por id,creamos un objeto de la clase Optional de java.util y obtenemos el usuario
            Optional<Gadget> ExistLaptop = repository.getById(gadget.getId());
            //comprobamos que los campos no sean null y los modifica por los nuevos
            if (ExistLaptop.isPresent()) {
                if (gadget.getBrand() != null) {
                    ExistLaptop.get().setBrand(gadget.getBrand());
                }
                if (gadget.getCategory() != null) {
                    ExistLaptop.get().setCategory(gadget.getCategory());
                }
                if (gadget.getName() != null) {
                    ExistLaptop.get().setName(gadget.getName());
                }
                if (gadget.getDescription() != null) {
                    ExistLaptop.get().setDescription(gadget.getDescription());
                }
                if (gadget.getPrice() > 0) {
                    ExistLaptop.get().setPrice(gadget.getPrice());
                }
                if (gadget.getQuantity()>0) {
                    ExistLaptop.get().setQuantity(gadget.getQuantity());
                }
                if (gadget.getPhotography() != null) {
                    ExistLaptop.get().setPhotography(gadget.getPhotography());
                }



                //retorne los datos con el update implementado
                return repository.save(ExistLaptop.get());

            } else {//si hay datos null retorna los datos recibidos
                return gadget;
            }

        } else {//si no se envío el Id retorne los datos enviados
            return gadget;
        }
    }

    //borrar producto
    public void delete(Integer Id) {
        //si obtiene el id, lo borramos y retornamos true
        Optional<Gadget> ov = repository.getById(Id);
        if (ov.isPresent()) {
            repository.delete(ov.get());
        }
    }
    /* ********************Reto 5 ********************************************/
    public List<Gadget> getGadgetByDescription(String description){
        return repository.getGadgetByDescription("(?i)"+description);
    }
    public List<Gadget> getGadgetByPrice (double price){
        return repository.getGadgetByPrice(price);
    }
}
