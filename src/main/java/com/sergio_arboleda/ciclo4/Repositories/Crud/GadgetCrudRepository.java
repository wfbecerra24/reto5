package com.sergio_arboleda.ciclo4.Repositories.Crud;


import com.sergio_arboleda.ciclo4.Entities.Gadget;
import com.sergio_arboleda.ciclo4.Entities.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GadgetCrudRepository extends MongoRepository<Gadget,Integer> {
    //para seleccionar el usuario con id maximo o mayor y así poder enviar el id siguiente
    Optional<Gadget> findTopByOrderByIdDesc();
    /* ***********************Reto 5************************************************/
    //filtra los productos por una cadena de caracteres
    @Query("{ description : { $regex : ?0 } }")
    List<Gadget> getGadgetByDescription(final String description);

     //filtra los productos por un precio menor que, para menor que es lt, para mayor que es gte
    @Query("{ price : { $lt: ?0 } }")
    List<Gadget> getGadgetByPrice(final double price);

}
