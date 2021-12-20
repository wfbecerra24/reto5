package com.sergio_arboleda.ciclo4.Controllers;
import com.sergio_arboleda.ciclo4.Entities.Orden;
import com.sergio_arboleda.ciclo4.Services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")//le damos una URL base, no importa si lleva o no el / antes
@CrossOrigin(origins = "*",methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class OrdenController {
    @Autowired
    OrdenService service;

    //get obtener todas las ordenes
    @GetMapping("/all")
    public List<Orden> getAllOrders(){
        return service.getAllOrders();
    }

    //obtener orden por id
    @GetMapping("/{id}")
    public Optional<Orden> getOrderById(@PathVariable("id") int id){
        return service.getOrderById(id);
    }
    /* *********************Reto3********************************************************/

    //obtener orden por zona
    @GetMapping("/zona/{zona}")
    public List<Orden> getOrdenByZone(@PathVariable("zona") String zona){
        return  service.getOrderByZone(zona);
    }
    /* *****************************************************************************/

    //crear nueva orden
    @PostMapping("/new")
   public ResponseEntity save(@RequestBody Orden orden){//requiéralo del cuerpo de la consulta
    return new ResponseEntity (service.save(orden), HttpStatus.CREATED);
}

    //actualizar orden
   @PutMapping("/update")
   public ResponseEntity<Orden> update(@RequestBody Orden orden){//requiéralo del cuerpo de la consulta
    return new ResponseEntity(service.update(orden),HttpStatus.CREATED);
    }

    //borrar orden
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
    service.delete(id);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
   }
   /* ****************************Reto4****************/
   @GetMapping("/salesman/{id}")
   public List<Orden> getOrderSalesManById(@PathVariable("id") Integer id){
       return  service.getOrderSalesManById(id);
   }

   @GetMapping("/state/{state}/{id}")
    public List<Orden> getOrderSalesManByState(@PathVariable("state") String state, @PathVariable("id") Integer id){
        return service.getOrderSalesManByState(state,id);
    }
    @GetMapping("/date/{date}/{id}")
    public List<Orden> getOrdersSalesManByDate(@PathVariable("date") String dateStr, @PathVariable("id") Integer id) {
        return service.getOrdersSalesManByDate(dateStr,id);
    }



}
