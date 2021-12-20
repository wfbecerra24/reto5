package com.sergio_arboleda.ciclo4.Repositories;
import com.sergio_arboleda.ciclo4.Entities.Cliente;
import com.sergio_arboleda.ciclo4.Repositories.Crud.ClienteCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {

    @Autowired
    private ClienteCrudRepository repository;
//Listar o mostrar lista de clientes
    public List<Cliente> getAll(){
        return (List<Cliente>) repository.findAll();//busca todos los registros de la tabla admin
    }

    /*select * from table where ID=id
     * buscar cliente por id*/
    //devuelve algo opcional devuelve nulo o e valor

    public Optional<Cliente> getById(int id){
        return repository.findById(id);
    }

  //retorna el cliente si el mail y el password coinciden
    public Optional<Cliente> getByEmailAndPassword(String email, String password){
        return repository.findByEmailAndPassword(email,password);
    }
    //retorna el cliente si el email existe
    public Optional<Cliente> getByEmail(String  email){
        return repository.findByEmail(email);
    }



    /*Insert y Update
     * actualiza cliente o crea nuevo registro*/
    public  Cliente save(Cliente cliente){
        return repository.save(cliente);
    }

    /*delete from table
    * Borrar cliente*/
    public void delete(Cliente cliente){
        repository.delete(cliente);
    }

    public Optional<Cliente> lastClienteId(){
        return repository.findTopByOrderByIdDesc();
    }
}
