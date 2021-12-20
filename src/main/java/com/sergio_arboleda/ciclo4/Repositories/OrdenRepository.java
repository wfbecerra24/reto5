package com.sergio_arboleda.ciclo4.Repositories;
import com.sergio_arboleda.ciclo4.Entities.Orden;
import com.sergio_arboleda.ciclo4.Repositories.Crud.OrdenCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class OrdenRepository  {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    OrdenCrudRepository repository;
/* *********************Reto3********************************************************/
    //obtener lista de todas las órdenes existentes Get
    public List<Orden> getAllOrders(){
        return (List<Orden>) repository.findAll();
    }

    //obtener orden por id
    public Optional<Orden> getOrdersById(int id){
        return repository.findById(id);
    }
    //obtener orden por zona
    public List<Orden> getOrderByZone(String zona){
        return  repository.findByZone(zona);
    }
    /* *****************************************************************************/

    //guardar orden post y put
    public Orden save(Orden orden){
        return repository.save(orden);
    }
    //borrar orden delete
    public void delete(Orden orden) {
        repository.delete(orden);
    }

    public Optional<Orden> lastUserId() {
        return repository.findTopByOrderByIdDesc();
    }
    /* ********************Reto 4**********************/
      public List<Orden> getOrderSalesManById(Integer id){
          Query query = new Query();
          Criteria criterio = Criteria.where("salesMan.id").is(id);
          query.addCriteria(criterio);
          List <Orden> ordenes=mongoTemplate.find(query,Orden.class);
          return ordenes;
      }

    public List<Orden> getOrderSalesManByState(String state, Integer id){
        Query query = new Query();
        Criteria criterio = Criteria.where("salesMan.id").is(id).and("status").is(state);
        query.addCriteria(criterio);
        List <Orden> ordenes=mongoTemplate.find(query,Orden.class);
        return ordenes;
    }
    public List<Orden> getOrdersSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Query query = new Query();

        Criteria criterio = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
                .and("salesMan.id").is(id);
        query.addCriteria(criterio);
        List<Orden> orders = mongoTemplate.find(query,Orden.class);

        return orders;
    }



}
