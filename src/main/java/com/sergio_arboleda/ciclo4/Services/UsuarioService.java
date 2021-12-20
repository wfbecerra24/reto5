package com.sergio_arboleda.ciclo4.Services;
import com.sergio_arboleda.ciclo4.Entities.Usuario;
import com.sergio_arboleda.ciclo4.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository service;
    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/
    /*Este sería el Get y nos retorna una lista de administradores*/
    public List<Usuario> getAll(){
        return service.getAll();
    }


    /*Este sería el Get con Id*/

    public Optional<Usuario> getById(int UsuarioId){
        return service.getById(UsuarioId);
    }

    /* *************************************************post/Create****************************************/

    /*sería el Post
    Comprobamos si el ususario envia o no el id y evitamos que nos genere un error
    si lo envía se verifica que no exista, si no lo envia se verifica el último
    existente y se le incrementa en 1 para darle el id, y ademas se comprueba
    que el mail no exista*/
    public Usuario save(Usuario usuario) {

        //obtiene el maximo id existente en la coleccion
        Optional<Usuario> userIdMaximo = service.lastUserId();

        //si el id del Usaurio que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
        if (usuario.getId() == null) {
            //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
            if (!userIdMaximo.isPresent())
                usuario.setId(1);
                //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo del usuario
            else
                usuario.setId(userIdMaximo.get().getId() + 1);
        }

        Optional<Usuario> e = service.getById(usuario.getId());
        if (!e.isPresent()) {
            if (getByEmail(usuario.getEmail())==false){
                return service.save(usuario);
            }else{
                return usuario;
            }
        }else{
            return usuario;
        }
    }


    /* *************************************************Put/Update****************************************/
    public Usuario update(Usuario usuario){
        //si el Id no es null, es decir existe
        if(usuario.getId()!=null){
            //obtener el usuario por id,creamos un objeto de la clase Optional de java.util y obtenemos el usuario
            Optional<Usuario> existUsuario= service.getById(usuario.getId());
            //comprobamos que los campos no sean null y los modifica por los nuevos
            if(existUsuario.isPresent()){
                if(usuario.getName()!=null){
                    existUsuario.get().setName(usuario.getName());
                }
                if(usuario.getEmail()!=null){
                    existUsuario.get().setEmail(usuario.getEmail());
                }
                if(usuario.getPassword()!=null){
                    existUsuario.get().setPassword(usuario.getPassword());
                }
                if(usuario.getIdentification()!=null){
                    existUsuario.get().setIdentification(usuario.getIdentification());
                }
                if(usuario.getBirthtDay()!=null){
                    existUsuario.get().setBirthtDay(usuario.getBirthtDay());
                }
                if(usuario.getMonthBirthtDay()!=null){
                    existUsuario.get().setMonthBirthtDay(usuario.getMonthBirthtDay());
                }
                if(usuario.getAddress()!=null){
                    existUsuario.get().setAddress(usuario.getAddress());
                }
                if(usuario.getCellPhone()!=null){
                    existUsuario.get().setCellPhone(usuario.getCellPhone());
                }
                if(usuario.getZone()!=null){
                    existUsuario.get().setZone(usuario.getZone());
                }
                if(usuario.getType()!=null){
                    existUsuario.get().setType(usuario.getType());
                }

                //retorne los datos con el update implementado
                return service.save(existUsuario.get());

            }else{//si hay datos null retorna los datos recibidos
                return usuario;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return usuario;
        }
    }
    /* *************************************************Delete****************************************/


    /*este sería el Delete*/
    public void delete(Integer UsuarioId){
        //si obtiene el id, lo borramos y retornamos true
        Optional<Usuario> ov= service.getById(UsuarioId);
        if(ov.isPresent()){
            service.delete(ov.get());
        }


    }
    public Usuario getByEmailAndPassword(String email, String password){
        Optional<Usuario> usuario= service.getByEmailAndPassword(email,password);
        if(usuario.isPresent()){
            return usuario.get();
        }else{//si la combinación no existe
            Usuario u=new Usuario();
            u.setPassword(password);
            u.setEmail(email);
            u.setName("NO DEFINIDO");
            return u;
        }
    }
    public boolean getByEmail(String email){
        Optional<Usuario> usuario= service.getByEmail(email);
        if(usuario.isPresent()){
            return true;
        }else{
            return false;
        }
    }
    /* ************************Reto5*************************************/
    public List<Usuario>findByMonth(String month){
        return service.findByMonth(month);
    }















}
