package com.sergio_arboleda.ciclo4.Repositories.Crud;

import com.sergio_arboleda.ciclo4.Entities.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioCrudRepository extends MongoRepository<Usuario,Integer> {
   // @Query("select u from Usuario u where u.email=?1 and u.password=?2")

    @Query("{email:?0}/{password:?1}")
     Optional<Usuario> findByEmailAndPassword(String email,String password);

    //@Query("select u from Usuario u where u.email=?1")
    @Query("{email:?0}")
     Optional<Usuario>findByEmail(String email);

      //para seleccionar el usuario con id maximo o mayor y así poder enviar el id siguiente
      Optional<Usuario> findTopByOrderByIdDesc();
    /* ******************Reto 5*************************************************/
    @Query("{monthBirthtDay:?0}")
     List<Usuario> findByMonth(String month);
}
























