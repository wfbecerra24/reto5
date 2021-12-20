package com.sergio_arboleda.ciclo4.Services;
import com.sergio_arboleda.ciclo4.Entities.Orden;
import com.sergio_arboleda.ciclo4.Repositories.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {

    @Autowired
    OrdenRepository service;

//obtener todas las órdenes
public List<Orden> getAllOrders(){
    return service.getAllOrders();
}

//obtener órdenes por id
public Optional<Orden> getOrderById(int id){
    return service.getOrdersById(id);
}
    /* *********************Reto3********************************************************/

//obtener orden por zona
    public List<Orden> getOrderByZone(String zona){
            return service.getOrderByZone(zona);
        }
    /* *****************************************************************************/

//guardar orden post
public Orden save(Orden orden) {

    //obtiene el maximo id existente en la coleccion
    Optional<Orden> orderIdMaximo = service.lastUserId();

    //si el id del Usaurio que se recibe como parametro es nulo, entonces valida el maximo id existente en base de datos
    if (orden.getId() == null) {
        //valida el maximo id generado, si no hay ninguno aun el primer id sera 1
        if (!orderIdMaximo.isPresent()){
            orden.setId(1);

        }
            //si retorna informacion suma 1 al maximo id existente y lo asigna como el codigo del usuario
        else {
            orden.setId(orderIdMaximo.get().getId() + 1);
        }


    }
    return service.save(orden);
}
//update, actualizar orden
    public Orden update(Orden orden){
        //si el Id no es null, es decir existe
        if(orden.getId()!=null){
            //obtener el orden por id,creamos un objeto de la clase Optional de java.util y obtenemos el usuario
            Optional<Orden> existOrder= service.getOrdersById(orden.getId());
            //comprobamos que los campos no sean null y los modifica por los nuevos
            if(existOrder.isPresent()){
                if(orden.getRegisterDay()!=null){
                    existOrder.get().setRegisterDay(orden.getRegisterDay());
                }

                if(orden.getStatus()!=null){
                    existOrder.get().setStatus(orden.getStatus());
                }
                if(orden.getSalesMan()!=null){
                    existOrder.get().setSalesMan(orden.getSalesMan());
                }


                //retorne los datos con el update implementado
                return service.save(existOrder.get());

            }else{//si hay datos null retorna los datos recibidos
                return orden;
            }

        }else{//si no se envío el Id retorne los datos enviados
            return orden;
        }
    }
    //borrar orden delete
    public void delete(Integer id){
    Optional<Orden> orden=service.getOrdersById(id);
    //si existe el id de la orden la borramos
    if(orden.isPresent()){
        service.delete(orden.get());
    }
    }
    /* *********************Reto 4******************** */
      public List<Orden> getOrderSalesManById(Integer id){
          return service.getOrderSalesManById(id);
      }

    public List<Orden> getOrderSalesManByState(String state,Integer id){
        return service.getOrderSalesManByState(state,id);
    }
    public List<Orden> getOrdersSalesManByDate(String dateStr, Integer id) {
        return service.getOrdersSalesManByDate(dateStr,id);
    }
}
