package com.sergio_arboleda.ciclo4.Controllers;
import com.sergio_arboleda.ciclo4.Entities.Usuario;
import com.sergio_arboleda.ciclo4.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")//le damos una URL base, no importa si lleva o no el / antes
@CrossOrigin(origins = "*",methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UsuarioController {
    //GET 200
    //POST y PUT 201
    //DELETE 204

    @Autowired
    private UsuarioService service;//creamos objeto de tipo AdminService
    /* ******************************************Creamos el CRUD************************************************/
    /* *************************************************Get/Read****************************************/
    //get
    @GetMapping("/all")
    public List<Usuario> getUsuario(){
        return service.getAll();
    }

    //obtenemos el get con Id, le indicamos que le vamos a pasar un parámetro
    @GetMapping("/{id}")            //pasamos el Id por parámetro a la variable adminId
    public Optional<Usuario> getById(@PathVariable("id") int id){
        return service.getById(id);
    }



    @GetMapping("/{email}/{password}")
    public Usuario getByEmailAndPassword(@PathVariable("email") String email,@PathVariable("password") String password){
        return service.getByEmailAndPassword(email,password);
    }

    @GetMapping("/emailexist/{email}")
    public boolean getByEmail(@PathVariable("email") String email){
        return  service.getByEmail(email);
    }
    /* *************************************************Post/Create****************************************/


    //este es el Post
    @PostMapping("/new")
    public ResponseEntity save(@RequestBody Usuario usuario){//requiéralo del cuerpo de la consulta
        return new ResponseEntity (service.save(usuario), HttpStatus.CREATED);
    }
    /* *************************************************Put/Update****************************************/

    //este es el put
    @PutMapping("/update")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){//requiéralo del cuerpo de la consulta
        return new ResponseEntity(service.update(usuario),HttpStatus.CREATED);
    }
    /* *************************************************Delete****************************************/
/*  estos dos métodos son lo mismo, se puede usar el @ResponseStatus(HttpStatus....) o el
ResponseEntity(HttpStatus....);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//Retorna Status 204
    public void delete(@PathVariable("id") Integer UsuarioId){//con @PathVariable le indicamos que le vamos a enviar una variable a través del parámetro

         service.delete(UsuarioId);
    }
    */

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
/* **********************Reto5*****************************************/
    @GetMapping("/birthday/{month}")
public List<Usuario>findByMonth(@PathVariable("month") String month){
    return service.findByMonth(month);
}
}
