package com.sergio_arboleda.ciclo4.Services;
import com.sergio_arboleda.ciclo4.Entities.Cliente;
import com.sergio_arboleda.ciclo4.Entities.Usuario;
import com.sergio_arboleda.ciclo4.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository service;
    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/
    /*Este sería el Get y nos retorna una lista de clientes*/
    public List<Cliente> getAll(){
        return service.getAll();
    }


    /*Este sería el Get con Id*/

    public Optional<Cliente> getById(int ClienteId){
        return service.getById(ClienteId);
    }

    /* *************************************************post/Create****************************************/

    /*sería el Post
    Comprobamos si el ususario envia o no el id y evitamos que nos genere un error
    si lo envía se verifica que no exista, si no lo envia se verifica el último
    existente y se le incrementa en 1 para darle el id, y ademas se comprueba
    que el mail no exista*/
    public Cliente save(Cliente cliente) {

        //obtiene el maximo id existente en la coleccion
        Optional<Cliente> clienteIdMaximo = service.lastClienteId();

        //si el id del Usaurio que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (cliente.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (!clienteIdMaximo.isPresent())
                cliente.setId(1);
                //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo del usuario
            else
                cliente.setId(clienteIdMaximo.get().getId() + 1);
        }

        Optional<Cliente> e = service.getById(cliente.getId());
        if (!e.isPresent()) {
            if (getByEmail(cliente.getEmail())==false){
                return service.save(cliente);
            }else{
                return cliente;
            }
        }else{
            return cliente;
        }
    }


    /* *************************************************Put/Update****************************************/
    public Cliente update(Cliente cliente){
        //si el Id no es null, es decir existe
        if(cliente.getId()!=null){
            //obtener el cliente por id,creamos un objeto de la clase Optional de java.util y obtenemos el usuario
            Optional<Cliente> existCliente= service.getById(cliente.getId());
            //comprobamos que los campos no sean null y los modifica por los nuevos
            if(existCliente.isPresent()){
                if(cliente.getName()!=null){
                    existCliente.get().setName(cliente.getName());
                }
                if(cliente.getEmail()!=null){
                    existCliente.get().setEmail(cliente.getEmail());
                }
                if(cliente.getPassword()!=null){
                    existCliente.get().setPassword(cliente.getPassword());
                }
                if(cliente.getIdentification()!=null){
                    existCliente.get().setIdentification(cliente.getIdentification());
                }

                if(cliente.getAddress()!=null){
                    existCliente.get().setAddress(cliente.getAddress());
                }
                if(cliente.getCellPhone()!=null){
                    existCliente.get().setCellPhone(cliente.getCellPhone());
                }
                if(cliente.getZone()!=null){
                    existCliente.get().setZone(cliente.getZone());
                }
                if(cliente.getType()!=null){
                    existCliente.get().setType(cliente.getType());
                }

                //retorne los datos con el update implementado
                return service.save(existCliente.get());

            }else{//si hay datos null retorna los datos recibidos
                return cliente;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return cliente;
        }
    }
    /* *************************************************Delete****************************************/


    /*este sería el Delete*/
    public void delete(Integer ClienteId){
        //si obtiene el id, lo borramos y retornamos true
        Optional<Cliente> ov= service.getById(ClienteId);
        if(ov.isPresent()){
            service.delete(ov.get());
        }


    }
    public Cliente getByEmailAndPassword(String email, String password){
        Optional<Cliente> cliente= service.getByEmailAndPassword(email,password);
        if(cliente.isPresent()){
            return cliente.get();
        }else{//si la combinación no existe
            Cliente c=new Cliente();
            c.setPassword(password);
            c.setEmail(email);
            c.setName("NO DEFINIDO");
            return c;
        }
    }
    public boolean getByEmail(String email){
        Optional<Cliente> cliente= service.getByEmail(email);
        if(cliente.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
