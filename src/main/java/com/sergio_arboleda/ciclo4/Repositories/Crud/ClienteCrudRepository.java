package com.sergio_arboleda.ciclo4.Repositories.Crud;
import com.sergio_arboleda.ciclo4.Entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ClienteCrudRepository extends MongoRepository<Cliente,Integer> {
   // @Query("select u from Cliente u where u.email=?1 and u.password=?2")

    @Query("{email:?0}/{password:?1}")
     Optional<Cliente> findByEmailAndPassword(String email, String password);

    //@Query("select u from Cliente u where u.email=?1")
    @Query("{email:?0}")
     Optional<Cliente>findByEmail(String email);

      //para seleccionar el cliente con id maximo o mayor y así poder enviar el id siguiente
      Optional<Cliente> findTopByOrderByIdDesc();
}
