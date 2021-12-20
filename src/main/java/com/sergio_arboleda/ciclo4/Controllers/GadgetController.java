package com.sergio_arboleda.ciclo4.Controllers;

import com.sergio_arboleda.ciclo4.Entities.Gadget;
import com.sergio_arboleda.ciclo4.Entities.Orden;
import com.sergio_arboleda.ciclo4.Services.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gadget")
@CrossOrigin(origins = "*",methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})

public class GadgetController {

    @Autowired
    GadgetService service;
    //listar productos
    @GetMapping("/all")
    public List<Gadget> getLaptop(){
        return service.getAll();
    }

    //obtener producto por id
    @GetMapping("/{id}")            //pasamos el Id por parámetro a la variable adminId
    public Optional<Gadget> getById(@PathVariable("id") int id){
        return service.getById(id);
    }

    //crear producto
    @PostMapping("/new")
    public ResponseEntity save(@RequestBody Gadget gadget){//requiéralo del cuerpo de la consulta
        return new ResponseEntity (service.save(gadget), HttpStatus.CREATED);
    }
    /* *************************************************Put/Update****************************************/

    //actualizar producto
    @PutMapping("/update")
    public ResponseEntity<Gadget> update(@RequestBody Gadget gadget){//requiéralo del cuerpo de la consulta
        return new ResponseEntity(service.update(gadget),HttpStatus.CREATED);
    }
    //borrar producto
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    /* **************************Reto 5*********************************************/
    @GetMapping("/description/{description}")
    public List<Gadget> getGadgetByDescription(@PathVariable("description") String description){
        return  service.getGadgetByDescription(description);
    }
    @GetMapping("/price/{price}")
    public List<Gadget> getGadgetByPrice(@PathVariable("price") double price){
        return  service.getGadgetByPrice(price);
    }

}
