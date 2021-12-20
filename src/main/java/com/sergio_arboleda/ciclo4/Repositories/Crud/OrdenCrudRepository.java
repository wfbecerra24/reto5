package com.sergio_arboleda.ciclo4.Repositories.Crud;

import com.sergio_arboleda.ciclo4.Entities.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrdenCrudRepository extends MongoRepository<Orden,Integer> {
    //buscar orden por zona, pasándole como parámetro la zona
    //retorna las órdenes de pedido que coinciden con la zona recibida como parametro
   @Query("{'salesMan.zone':?0}")
     List<Orden> findByZone(String zona);

   //retorna las órdenes por estado
   @Query("{status: ?0}")
   List<Orden> findByStatus(final String status);

    //para seleccionar la orden con id maximo o mayor y así poder enviar el id siguiente
    Optional<Orden> findTopByOrderByIdDesc();


}
