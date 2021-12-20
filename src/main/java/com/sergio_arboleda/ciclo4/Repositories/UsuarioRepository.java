package com.sergio_arboleda.ciclo4.Repositories;

import com.sergio_arboleda.ciclo4.Entities.Usuario;
import com.sergio_arboleda.ciclo4.Repositories.Crud.UsuarioCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {

    @Autowired
    private UsuarioCrudRepository repository;
//Listar o mostrar lista de Usuarios
    public List<Usuario> getAll(){
        return (List<Usuario>) repository.findAll();//busca todos los registros de la tabla admin
    }

    /*select * from table where ID=id
     * buscar usuario por id*/
    //devuelve algo opcional devuelve nulo o e valor

    public Optional<Usuario> getById(int id){
        return repository.findById(id);
    }

  //retorna el usuario si el mail y el password coinciden
    public Optional<Usuario> getByEmailAndPassword(String email, String password){
        return repository.findByEmailAndPassword(email,password);
    }
    //retorna el usuario si el email existe
    public Optional<Usuario> getByEmail(String  email){
        return repository.findByEmail(email);
    }



    /*Insert y Update
     * actualiza Usuario o crea nuevo registro*/
    public  Usuario save(Usuario usuario){
        return repository.save(usuario);
    }

    /*delete from table
    * Borrar Usuario*/
    public void delete(Usuario usuario){
        repository.delete(usuario);
    }

    public Optional<Usuario> lastUserId(){
        return repository.findTopByOrderByIdDesc();
    }
    /* *******************Reto5 ********************************/
    public List<Usuario>findByMonth(String month){
       return repository.findByMonth(month);
    }

}
